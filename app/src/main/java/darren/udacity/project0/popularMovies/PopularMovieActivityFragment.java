package darren.udacity.project0.popularMovies;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import darren.udacity.project0.R;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteColumns;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteCursor;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteSelection;
import darren.udacity.project0.popularMovies.data.trailer.TrailerColumns;
import darren.udacity.project0.popularMovies.data.trailer.TrailerCursor;
import darren.udacity.project0.popularMovies.data.trailer.TrailerSelection;
import darren.udacity.project0.util.JSONFetcher;
import darren.udacity.project0.util.JSONFetcherReceiver;


/**
 * A placeholder fragment containing a simple view.
 */
public class PopularMovieActivityFragment
        extends Fragment
        implements JSONFetcherReceiver {

    JSONFetcher fetcher = null;
    Uri configUri = null;
    Uri moviesUri = null;
    String sortBy = "popular";

    GridView grid = null;

    ArrayList<Movie> movies = new ArrayList<>();
    MovieGridAdapter adapter = null;

    public PopularMovieActivityFragment() {
        Uri.Builder config = new Uri.Builder();
        config.scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("configuration")
                .appendQueryParameter("api_key", Config.API_KEY);

        configUri = config.build();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.releaseContext();
        outState.putSerializable("Adapter", adapter);
    }

    public void updateMoviesUri(String sortBy) {
        Uri.Builder movieUriBuilder = new Uri.Builder();
        movieUriBuilder.scheme("http")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("movie")
                .appendPath(sortBy)
                .appendQueryParameter("page", "1")
                .appendQueryParameter("api_key", Config.API_KEY);

        moviesUri = movieUriBuilder.build();

        MovieGridAdapter _adapter = new MovieGridAdapter(getActivity(), new ArrayList<Movie>());
        adapter = _adapter;
        grid.setAdapter(_adapter);

        fetchConfig();

        this.sortBy = sortBy;
    }

    public void showFavorites() {
        FavoriteSelection favoriteSelection = new FavoriteSelection();
        favoriteSelection.orderById();
        Cursor cursor = getActivity().getContentResolver().query(
                FavoriteColumns.CONTENT_URI,
                null,
                favoriteSelection.sel(),
                favoriteSelection.args(),
                null
        );

        FavoriteCursor favoriteCursor = new FavoriteCursor(cursor);

        adapter = new MovieGridAdapter(getActivity(), favoriteCursor);
        grid.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MovieGridAdapter _adapter = null;
        if (savedInstanceState != null) {
            // Doing an unchecked cast
            // we know movies is an array list of movies so suppressing warning
            _adapter = (MovieGridAdapter) savedInstanceState.getSerializable("Adapter");
            _adapter.attachContext(getActivity());
        }
        if (_adapter == null) {
            adapter = new MovieGridAdapter(getActivity(), movies);
        } else {
            adapter = _adapter;
        }

        View view = inflater.inflate(R.layout.fragment_main_popular_movies, container, false);
        grid = (GridView) view.findViewById(R.id.poster_grid);
        grid.setAdapter(adapter);

        if (adapter.getCount() == 0) {
            updateMoviesUri(sortBy);
        }

        grid.setOnItemClickListener(new PosterClickListener());

        return view;
    }

    private void fetchConfig() {
        fetcher = new JSONFetcher(this);
        fetcher.execute(configUri.toString(), "CONFIG");
    }

    private void fetchMovies() {
        fetcher = new JSONFetcher(this);
        fetcher.execute(moviesUri.toString(), "MOVIES");
    }

    private void processConfig(JSONObject object) {
        if (object != null) {
            try {
                JSONObject imagesData = object.getJSONObject("images");
                Config.BASE_URL = imagesData.getString("base_url");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            fetchMovies();
        } else {
            Toast toast = Toast.makeText(
                    getActivity(),
                    "Could not connect to The Movie Database",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void processMovies(JSONObject object) {
        JSONArray results;
        try {
            results = object.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                adapter.addMovie(new Movie(results.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processJSON(JSONObject object, String key) {
        switch (key) {
            case "CONFIG":
                processConfig(object);
                break;
            case "MOVIES":
                processMovies(object);
                break;
        }
    }


    private class PosterClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MovieGridAdapter adapter = (MovieGridAdapter) parent.getAdapter();
            Movie movie = (Movie) adapter.getItem(position);

            MainActivity activity = (MainActivity) getActivity();

            if (adapter.getMode() == MovieGridAdapter.Mode.CURSOR) {
                FavoriteSelection favoriteSelection = new FavoriteSelection();
                favoriteSelection.moviedbId(movie.id);
                Cursor cursor = getActivity().getContentResolver().query(
                        FavoriteColumns.CONTENT_URI,
                        null,
                        favoriteSelection.sel(),
                        favoriteSelection.args(),
                        null
                );

                FavoriteCursor favoriteCursor = new FavoriteCursor(cursor);
                favoriteCursor.moveToFirst();

                TrailerSelection trailerSelection = new TrailerSelection();
                trailerSelection.movieId(favoriteCursor.getId());
                cursor.close();
                cursor = getActivity().getContentResolver().query(
                        TrailerColumns.CONTENT_URI,
                        null,
                        trailerSelection.sel(),
                        trailerSelection.args(),
                        null
                );

                TrailerCursor trailerCursor = new TrailerCursor(cursor);
                ArrayList<YoutubeVideo> trailers = new ArrayList<>();
                while (trailerCursor.moveToNext()) {
                    YoutubeVideo newVid = new YoutubeVideo(trailerCursor);
                    trailers.add(newVid);
                }

                activity.showMovieDetail(movie, trailers);
                return;
            }

            activity.showMovieDetail(movie);
        }
    }
}
