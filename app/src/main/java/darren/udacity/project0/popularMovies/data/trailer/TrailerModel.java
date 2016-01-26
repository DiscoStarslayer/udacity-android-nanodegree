package darren.udacity.project0.popularMovies.data.trailer;

import darren.udacity.project0.popularMovies.data.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Table of all the trailers
 */
public interface TrailerModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();

    /**
     * Name of the trailer
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * URL of the trailer
     * Cannot be {@code null}.
     */
    @NonNull
    String getUrl();
}
