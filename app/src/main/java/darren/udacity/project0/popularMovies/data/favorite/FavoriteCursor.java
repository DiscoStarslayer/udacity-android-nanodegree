package darren.udacity.project0.popularMovies.data.favorite;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import darren.udacity.project0.popularMovies.data.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code favorite} table.
 */
public class FavoriteCursor extends AbstractCursor implements FavoriteModel {
    public FavoriteCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(FavoriteColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The moviedb id
     * Can be {@code null}.
     */
    @Nullable
    public String getMoviedbId() {
        String res = getStringOrNull(FavoriteColumns.MOVIEDB_ID);
        return res;
    }

    /**
     * The title of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(FavoriteColumns.TITLE);
        return res;
    }

    /**
     * The release date of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getReleaseDate() {
        String res = getStringOrNull(FavoriteColumns.RELEASE_DATE);
        return res;
    }

    /**
     * The voted rating of the movie
     * Can be {@code null}.
     */
    @Nullable
    public Double getRating() {
        Double res = getDoubleOrNull(FavoriteColumns.RATING);
        return res;
    }

    /**
     * The URL pointing to the poster
     * Can be {@code null}.
     */
    @Nullable
    public String getPosterImage() {
        String res = getStringOrNull(FavoriteColumns.POSTER_IMAGE);
        return res;
    }

    /**
     * The synopsis of the movie
     * Can be {@code null}.
     */
    @Nullable
    public String getSynopsis() {
        String res = getStringOrNull(FavoriteColumns.SYNOPSIS);
        return res;
    }
}
