package darren.udacity.project0.popularMovies;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Darren on 7/12/2015.
 * <p/>
 * Object to represent a movie
 */
public class Movie implements Serializable {
    String posterPath = null;
    String id = null;
    String title = null;
    String overview = null;
    Double ratingDouble = null;
    String rating = null;
    String releaseDate = null;

    Movie(JSONObject movieData) {
        try {
            posterPath = Config.BASE_URL + Config.POSTER_SIZE + movieData.getString("poster_path");
            id = movieData.getString("id");
            title = movieData.getString("original_title");
            overview = movieData.getString("overview");
            ratingDouble = movieData.getDouble("vote_average");
            rating = ratingDouble.toString();
            releaseDate = movieData.getString("release_date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    Movie() {};

    public String getPosterPath() {
        return posterPath;
    }

    public double getRating() {
        return ratingDouble;
    }

    public String getTitle() {
        return title;
    }
}
