package darren.udacity.project0.popularMovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import darren.udacity.project0.R;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteCursor;

/**
 * Created by Darren on 7/12/2015.
 *
 * An adapter for displaying movies
 */
public class MovieGridAdapter extends BaseAdapter implements Serializable {
    ArrayList<Movie> movies = null;
    Context context = null;
    FavoriteCursor cursor = null;
    public enum Mode {
        ARRAY,
        CURSOR
    }

    Mode currentMode = null;

    MovieGridAdapter(Context context, ArrayList<Movie> movies) {
        super();
        this.movies = movies;
        this.context = context;
        this.currentMode = Mode.ARRAY;
    }

    MovieGridAdapter(Context context, FavoriteCursor favorites) {
        super();
        this.cursor = favorites;
        this.context = context;
        this.currentMode = Mode.CURSOR;
    }

    @Override
    public int getCount() {
        int count = 0;
        switch (this.currentMode) {
            case ARRAY:
                count =  movies.size();
                break;
            case CURSOR:
                count = cursor.getCount();
                break;
        }

        return count;
    }

    @Override
    public Object getItem(int position) {
        Object object = null;
        switch (this.currentMode) {
            case ARRAY:
                object = movies.get(position);
                break;
            case CURSOR:
                cursor.moveToPosition(position);
                Movie returnMovie = new Movie();
                returnMovie.id = cursor.getMoviedbId();
                returnMovie.overview = cursor.getSynopsis();
                returnMovie.ratingDouble = cursor.getRating();
                returnMovie.rating = returnMovie.ratingDouble.toString();
                returnMovie.title = cursor.getTitle();
                returnMovie.posterPath = cursor.getPosterImage();
                returnMovie.releaseDate = cursor.getReleaseDate();

                object = returnMovie;
                break;
        }
        return object;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.grid_elment_poster, null);
        } else {
            view = convertView;
        }

        String imageUrl = null;
        Double rating = null;
        String title = null;

        switch (this.currentMode) {
            case ARRAY:
                Movie movie = movies.get(position);

                imageUrl = movie.getPosterPath();
                rating = movie.getRating();
                title = movie.getTitle();
                break;
            case CURSOR:
                cursor.moveToPosition(position);

                imageUrl = cursor.getPosterImage();
                rating = cursor.getRating();
                title = cursor.getTitle();
                break;
        }

        ImageView posterImage = (ImageView) view.findViewById(R.id.poster_image);
        TextView movieTitle = (TextView) view.findViewById(R.id.title);
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating);

        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(posterImage);

        movieTitle.setText(title);
        Double percent = (rating / 10.0);
        Double adjustedRating = percent * ratingBar.getNumStars();
        ratingBar.setRating(adjustedRating.floatValue());

        return view;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        notifyDataSetChanged();
    }


    public void releaseContext() {
        context = null;
    }

    public void attachContext(Context context) {
        this.context = context;
    }

    public Mode getMode() {
        return currentMode;
    }
}
