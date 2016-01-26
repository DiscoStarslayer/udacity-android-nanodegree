package darren.udacity.project0.popularMovies.data.favorite;

import darren.udacity.project0.popularMovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A favorite movie
 */
public interface FavoriteModel extends BaseModel {

    /**
     * The moviedb id
     * Can be {@code null}.
     */
    @Nullable
    String getMoviedbId();

    /**
     * The title of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * The release date of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getReleaseDate();

    /**
     * The voted rating of the movie
     * Can be {@code null}.
     */
    @Nullable
    Double getRating();

    /**
     * The URL pointing to the poster
     * Can be {@code null}.
     */
    @Nullable
    String getPosterImage();

    /**
     * The synopsis of the movie
     * Can be {@code null}.
     */
    @Nullable
    String getSynopsis();
}
