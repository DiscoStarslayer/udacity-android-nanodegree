package darren.udacity.project0.popularMovies.data.trailer;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import darren.udacity.project0.popularMovies.data.base.AbstractCursor;
import darren.udacity.project0.popularMovies.data.favorite.*;

/**
 * Cursor wrapper for the {@code trailer} table.
 */
public class TrailerCursor extends AbstractCursor implements TrailerModel {
    public TrailerCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(TrailerColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_id} value.
     */
    public long getMovieId() {
        Long res = getLongOrNull(TrailerColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The moviedb id
     * Can be {@code null}.
     */
    @Nullable
    public String getFavoriteMoviedbId() {
        String res = getStringOrNull(FavoriteColumns.MOVIEDB_ID);
        return res;
    }

    /**
     * The title of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getFavoriteTitle() {
        String res = getStringOrNull(FavoriteColumns.TITLE);
        return res;
    }

    /**
     * The release date of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getFavoriteReleaseDate() {
        String res = getStringOrNull(FavoriteColumns.RELEASE_DATE);
        return res;
    }

    /**
     * The voted rating of the movie
     * Can be {@code null}.
     */
    @Nullable
    public Double getFavoriteRating() {
        Double res = getDoubleOrNull(FavoriteColumns.RATING);
        return res;
    }

    /**
     * The URL pointing to the poster
     * Can be {@code null}.
     */
    @Nullable
    public String getFavoritePosterImage() {
        String res = getStringOrNull(FavoriteColumns.POSTER_IMAGE);
        return res;
    }

    /**
     * The synopsis of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getFavoriteSynopsis() {
        String res = getStringOrNull(FavoriteColumns.SYNOPSIS);
        return res;
    }

    /**
     * Name of the trailer
     * Cannot be {@code null}.
     */
    @NonNull
    public String getName() {
        String res = getStringOrNull(TrailerColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * URL of the trailer
     * Cannot be {@code null}.
     */
    @NonNull
    public String getUrl() {
        String res = getStringOrNull(TrailerColumns.URL);
        if (res == null)
            throw new NullPointerException("The value of 'url' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
