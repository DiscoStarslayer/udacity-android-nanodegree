package darren.udacity.project0.popularMovies.data.favorite;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import darren.udacity.project0.popularMovies.data.base.AbstractSelection;

/**
 * Selection for the {@code favorite} table.
 */
public class FavoriteSelection extends AbstractSelection<FavoriteSelection> {
    @Override
    protected Uri baseUri() {
        return FavoriteColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public FavoriteCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public FavoriteCursor query(Context context) {
        return query(context, null);
    }


    public FavoriteSelection id(long... value) {
        addEquals("favorite." + FavoriteColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection idNot(long... value) {
        addNotEquals("favorite." + FavoriteColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteSelection orderById(boolean desc) {
        orderBy("favorite." + FavoriteColumns._ID, desc);
        return this;
    }

    public FavoriteSelection orderById() {
        return orderById(false);
    }

    public FavoriteSelection moviedbId(String... value) {
        addEquals(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection moviedbIdNot(String... value) {
        addNotEquals(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection moviedbIdLike(String... value) {
        addLike(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection moviedbIdContains(String... value) {
        addContains(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection moviedbIdStartsWith(String... value) {
        addStartsWith(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection moviedbIdEndsWith(String... value) {
        addEndsWith(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public FavoriteSelection orderByMoviedbId(boolean desc) {
        orderBy(FavoriteColumns.MOVIEDB_ID, desc);
        return this;
    }

    public FavoriteSelection orderByMoviedbId() {
        orderBy(FavoriteColumns.MOVIEDB_ID, false);
        return this;
    }

    public FavoriteSelection title(String... value) {
        addEquals(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleNot(String... value) {
        addNotEquals(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleLike(String... value) {
        addLike(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleContains(String... value) {
        addContains(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleStartsWith(String... value) {
        addStartsWith(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection titleEndsWith(String... value) {
        addEndsWith(FavoriteColumns.TITLE, value);
        return this;
    }

    public FavoriteSelection orderByTitle(boolean desc) {
        orderBy(FavoriteColumns.TITLE, desc);
        return this;
    }

    public FavoriteSelection orderByTitle() {
        orderBy(FavoriteColumns.TITLE, false);
        return this;
    }

    public FavoriteSelection releaseDate(String... value) {
        addEquals(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateNot(String... value) {
        addNotEquals(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateLike(String... value) {
        addLike(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateContains(String... value) {
        addContains(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateStartsWith(String... value) {
        addStartsWith(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection releaseDateEndsWith(String... value) {
        addEndsWith(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteSelection orderByReleaseDate(boolean desc) {
        orderBy(FavoriteColumns.RELEASE_DATE, desc);
        return this;
    }

    public FavoriteSelection orderByReleaseDate() {
        orderBy(FavoriteColumns.RELEASE_DATE, false);
        return this;
    }

    public FavoriteSelection rating(Double... value) {
        addEquals(FavoriteColumns.RATING, value);
        return this;
    }

    public FavoriteSelection ratingNot(Double... value) {
        addNotEquals(FavoriteColumns.RATING, value);
        return this;
    }

    public FavoriteSelection ratingGt(double value) {
        addGreaterThan(FavoriteColumns.RATING, value);
        return this;
    }

    public FavoriteSelection ratingGtEq(double value) {
        addGreaterThanOrEquals(FavoriteColumns.RATING, value);
        return this;
    }

    public FavoriteSelection ratingLt(double value) {
        addLessThan(FavoriteColumns.RATING, value);
        return this;
    }

    public FavoriteSelection ratingLtEq(double value) {
        addLessThanOrEquals(FavoriteColumns.RATING, value);
        return this;
    }

    public FavoriteSelection orderByRating(boolean desc) {
        orderBy(FavoriteColumns.RATING, desc);
        return this;
    }

    public FavoriteSelection orderByRating() {
        orderBy(FavoriteColumns.RATING, false);
        return this;
    }

    public FavoriteSelection posterImage(String... value) {
        addEquals(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public FavoriteSelection posterImageNot(String... value) {
        addNotEquals(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public FavoriteSelection posterImageLike(String... value) {
        addLike(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public FavoriteSelection posterImageContains(String... value) {
        addContains(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public FavoriteSelection posterImageStartsWith(String... value) {
        addStartsWith(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public FavoriteSelection posterImageEndsWith(String... value) {
        addEndsWith(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public FavoriteSelection orderByPosterImage(boolean desc) {
        orderBy(FavoriteColumns.POSTER_IMAGE, desc);
        return this;
    }

    public FavoriteSelection orderByPosterImage() {
        orderBy(FavoriteColumns.POSTER_IMAGE, false);
        return this;
    }

    public FavoriteSelection synopsis(String... value) {
        addEquals(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public FavoriteSelection synopsisNot(String... value) {
        addNotEquals(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public FavoriteSelection synopsisLike(String... value) {
        addLike(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public FavoriteSelection synopsisContains(String... value) {
        addContains(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public FavoriteSelection synopsisStartsWith(String... value) {
        addStartsWith(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public FavoriteSelection synopsisEndsWith(String... value) {
        addEndsWith(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public FavoriteSelection orderBySynopsis(boolean desc) {
        orderBy(FavoriteColumns.SYNOPSIS, desc);
        return this;
    }

    public FavoriteSelection orderBySynopsis() {
        orderBy(FavoriteColumns.SYNOPSIS, false);
        return this;
    }
}
