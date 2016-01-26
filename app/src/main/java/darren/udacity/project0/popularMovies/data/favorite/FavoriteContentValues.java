package darren.udacity.project0.popularMovies.data.favorite;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import darren.udacity.project0.popularMovies.data.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code favorite} table.
 */
public class FavoriteContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return FavoriteColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable FavoriteSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable FavoriteSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * The moviedb id
     */
    public FavoriteContentValues putMoviedbId(@Nullable String value) {
        mContentValues.put(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public FavoriteContentValues putMoviedbIdNull() {
        mContentValues.putNull(FavoriteColumns.MOVIEDB_ID);
        return this;
    }

    /**
     * The title of the movie
     */
    public FavoriteContentValues putTitle(@Nullable String value) {
        mContentValues.put(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteContentValues putTitleNull() {
        mContentValues.putNull(FavoriteColumns.TITLE);
        return this;
    }

    /**
     * The release date of the movie
     */
    public FavoriteContentValues putReleaseDate(@Nullable String value) {
        mContentValues.put(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteContentValues putReleaseDateNull() {
        mContentValues.putNull(FavoriteColumns.RELEASE_DATE);
        return this;
    }

    /**
     * The voted rating of the movie
     */
    public FavoriteContentValues putRating(@Nullable Double value) {
        mContentValues.put(FavoriteColumns.RATING, value);
        return this;
    }

    public FavoriteContentValues putRatingNull() {
        mContentValues.putNull(FavoriteColumns.RATING);
        return this;
    }

    /**
     * The URL pointing to the poster
     */
    public FavoriteContentValues putPosterImage(@Nullable String value) {
        mContentValues.put(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public FavoriteContentValues putPosterImageNull() {
        mContentValues.putNull(FavoriteColumns.POSTER_IMAGE);
        return this;
    }

    /**
     * The synopsis of the movie
     */
    public FavoriteContentValues putSynopsis(@Nullable String value) {
        mContentValues.put(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public FavoriteContentValues putSynopsisNull() {
        mContentValues.putNull(FavoriteColumns.SYNOPSIS);
        return this;
    }
}
