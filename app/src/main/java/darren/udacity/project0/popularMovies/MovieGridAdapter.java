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

/**
 * Created by Darren on 7/12/2015.
 * <p/>
 * An adapter for displaying movies
 */
public class MovieGridAdapter extends BaseAdapter implements Serializable {
    ArrayList<Movie> movies = null;
    Context context = null;

    MovieGridAdapter(Context context, ArrayList<Movie> movies) {
        super();
        this.movies = movies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
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

        Movie movie = movies.get(position);

        String imageUrl = movie.getPosterPath();
        Double rating = movie.getRating();
        String title = movie.getTitle();

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

    public void clearMovies() {
        movies = new ArrayList<>();
        notifyDataSetChanged();
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}
