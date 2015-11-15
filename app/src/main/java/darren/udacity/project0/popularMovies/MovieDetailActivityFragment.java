package darren.udacity.project0.popularMovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import darren.udacity.project0.R;
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

    public MovieDetailActivityFragment() {

    }

    @Override
    public void processJSON(JSONObject object, String key) {
        switch (key) {
            case "TRAILERS":
                Log.v(TAG, object.toString());
                adapter = new TrailerAdapter(getActivity(), processTrailers(object));
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail_activty, container, false);

        movie = (Movie) getActivity().getIntent().getSerializableExtra("Movie");

        TextView movieTitle = (TextView) view.findViewById(R.id.movie_title);
        ImageView moviePoster = (ImageView) view.findViewById(R.id.movie_poster);
        TextView releaseDate = (TextView) view.findViewById(R.id.release_date);
        RatingBar score = (RatingBar) view.findViewById(R.id.review_score);
        TextView synopsis = (TextView) view.findViewById(R.id.movie_synopsis);

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
