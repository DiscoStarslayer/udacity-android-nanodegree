package darren.udacity.project0.popularMovies;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import darren.udacity.project0.R;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteColumns;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteContentValues;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteCursor;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteSelection;
import darren.udacity.project0.popularMovies.data.trailer.TrailerContentValues;
import darren.udacity.project0.util.JSONFetcher;
import darren.udacity.project0.util.JSONFetcherReceiver;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment
        implements JSONFetcherReceiver {

    private static final String TAG = "MovieDetailFragment";
    TrailerAdapter adapter = null;
    private ListView trailerList = null;
    private Movie movie = null;
    private ArrayList<YoutubeVideo> trailers = null;

    public MovieDetailActivityFragment() {

    }

    @Override
    public void processJSON(JSONObject object, String key) {
        switch (key) {
            case "TRAILERS":
                Log.v(TAG, object.toString());
                trailers = processTrailers(object);
                adapter = new TrailerAdapter(getActivity(), trailers);
                trailerList.setAdapter(adapter);
        }
    }

    private ArrayList<YoutubeVideo> processTrailers(JSONObject response) {
        final String type = "Trailer";
        ArrayList<YoutubeVideo> trailers = new ArrayList<>();
        try {
            JSONArray videos = response.getJSONArray("results");

            for (int i = 0; i < videos.length(); i++) {
                JSONObject video = videos.getJSONObject(i);

                if (video.getString("type").equals(type)) {
                    trailers.add(new YoutubeVideo(video));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailers;
    }

    public void show(Movie movie) {
        this.show(movie, null);
    }

    public void show(Movie movie, ArrayList<YoutubeVideo> trailers) {
        View view = getView();
        this.movie = movie;
        if (trailers != null) {
            adapter = new TrailerAdapter(getActivity(), trailers);
        }

        TextView movieTitle = (TextView) view.findViewById(R.id.movie_title);
        ImageView moviePoster = (ImageView) view.findViewById(R.id.movie_poster);
        TextView releaseDate = (TextView) view.findViewById(R.id.release_date);
        RatingBar score = (RatingBar) view.findViewById(R.id.review_score);
        TextView synopsis = (TextView) view.findViewById(R.id.movie_synopsis);
        Button addToFavoritesButton = (Button) view.findViewById(R.id.add_to_favorites);

        if (movie != null) {
            movieTitle.setText(movie.title);
            Picasso.with(getActivity())
                    .load(movie.posterPath)
                    .placeholder(R.drawable.placeholder)
                    .into(moviePoster);
            releaseDate.setText(movie.releaseDate);

            Double percent = (movie.ratingDouble / 10.0);
            Double adjustedRating = percent * score.getNumStars();
            score.setRating(adjustedRating.floatValue());

            synopsis.setText(movie.overview);

            trailerList = (ListView) view.findViewById(R.id.trailers_list);

            if (trailers == null) {
                loadTrailers();
            } else {
                trailerList.setAdapter(adapter);
            }

            trailerList.setOnItemClickListener(new TrailerClickListener());
            trailerList.setFocusable(false);

            addToFavoritesButton.setOnClickListener(new AddToFavoriteClickListener());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail_activty, container, false);

        movie = (Movie) getActivity().getIntent().getSerializableExtra("Movie");
        ArrayList<YoutubeVideo> trailers = (ArrayList<YoutubeVideo>) getActivity().getIntent().getSerializableExtra("Trailers");

        if (trailers != null) {
            adapter = new TrailerAdapter(getActivity(), trailers);
        }


        TextView movieTitle = (TextView) view.findViewById(R.id.movie_title);
        ImageView moviePoster = (ImageView) view.findViewById(R.id.movie_poster);
        TextView releaseDate = (TextView) view.findViewById(R.id.release_date);
        RatingBar score = (RatingBar) view.findViewById(R.id.review_score);
        TextView synopsis = (TextView) view.findViewById(R.id.movie_synopsis);
        Button addToFavoritesButton = (Button) view.findViewById(R.id.add_to_favorites);

        if (movie != null) {
            movieTitle.setText(movie.title);
            Picasso.with(getActivity())
                    .load(movie.posterPath)
                    .placeholder(R.drawable.placeholder)
                    .into(moviePoster);
            releaseDate.setText(movie.releaseDate);

            Double percent = (movie.ratingDouble / 10.0);
            Double adjustedRating = percent * score.getNumStars();
            score.setRating(adjustedRating.floatValue());

            synopsis.setText(movie.overview);

            trailerList = (ListView) view.findViewById(R.id.trailers_list);

            if (adapter == null) {
                loadTrailers();
            } else {
                trailerList.setAdapter(adapter);
            }

            trailerList.setOnItemClickListener(new TrailerClickListener());
            trailerList.setFocusable(false);

            addToFavoritesButton.setOnClickListener(new AddToFavoriteClickListener());
        }
        return view;
    }

    private void loadTrailers() {
        Uri.Builder trailersUriBuilder = new Uri.Builder();
        trailersUriBuilder.scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(movie.id)
                .appendPath("videos")
                .appendQueryParameter("api_key", Config.API_KEY);

        Uri trailersUri = trailersUriBuilder.build();

        JSONFetcher fetcher = new JSONFetcher(this);
        fetcher.execute(trailersUri.toString(), "TRAILERS");
    }

    private class AddToFavoriteClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            FavoriteContentValues favoritesValues = new FavoriteContentValues();
            ContentResolver contentResolver = getActivity().getContentResolver();

            FavoriteSelection favoriteSelector = new FavoriteSelection();

            favoritesValues.putMoviedbId(movie.id)
                    .putTitle(movie.getTitle())
                    .putRating(movie.getRating())
                    .putReleaseDate(movie.releaseDate)
                    .putSynopsis(movie.overview)
                    .putPosterImage(movie.posterPath);

            favoriteSelector.moviedbId(movie.id);

            Cursor checkIfExistsCursor = contentResolver.query(
                    FavoriteColumns.CONTENT_URI,
                    null,
                    favoriteSelector.sel(),
                    favoriteSelector.args(),
                    null
            );

            int results = checkIfExistsCursor.getCount();

            checkIfExistsCursor.close();

            if (results == 0) {
                Uri uri = contentResolver.insert(favoritesValues.uri(), favoritesValues.values());

                Cursor cursor = contentResolver.query(
                        uri,
                        null,
                        null,
                        null,
                        null
                );
                cursor.moveToFirst();
                FavoriteCursor favoriteCursor = new FavoriteCursor(cursor);

                for (int i = 0; i < trailers.size(); i++) {
                    YoutubeVideo trailer = trailers.get(i);

                    TrailerContentValues trailerValues = new TrailerContentValues();
                    trailerValues.putMovieId(favoriteCursor.getId())
                            .putName(trailer.getTitle())
                            .putUrl(trailer.getVideoUrl());

                    contentResolver.insert(trailerValues.uri(), trailerValues.values());
                }
                cursor.close();

                Toast toast = Toast.makeText(getContext(), "Added " + movie.getTitle() + " to your favorites!", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getContext(), "This movie is already in your favorites!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private class TrailerClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TrailerAdapter adapter = (TrailerAdapter) parent.getAdapter();

            YoutubeVideo trailer = (YoutubeVideo) adapter.getItem(position);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailer.getVideoUrl()));

            startActivity(intent);
        }
    }
}
