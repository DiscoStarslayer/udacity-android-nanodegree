package darren.udacity.project0.popularMovies.data.trailer;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import darren.udacity.project0.popularMovies.data.base.AbstractSelection;
import darren.udacity.project0.popularMovies.data.favorite.*;

/**
 * Selection for the {@code trailer} table.
 */
public class TrailerSelection extends AbstractSelection<TrailerSelection> {
    @Override
    protected Uri baseUri() {
        return TrailerColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TrailerCursor} object, which is positioned before the first entry, or null.
     */
    public TrailerCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailerCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public TrailerCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code TrailerCursor} object, which is positioned before the first entry, or null.
     */
    public TrailerCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new TrailerCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public TrailerCursor query(Context context) {
        return query(context, null);
    }


    public TrailerSelection id(long... value) {
        addEquals("trailer." + TrailerColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection idNot(long... value) {
        addNotEquals("trailer." + TrailerColumns._ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection orderById(boolean desc) {
        orderBy("trailer." + TrailerColumns._ID, desc);
        return this;
    }

    public TrailerSelection orderById() {
        return orderById(false);
    }

    public TrailerSelection movieId(long... value) {
        addEquals(TrailerColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieIdNot(long... value) {
        addNotEquals(TrailerColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public TrailerSelection movieIdGt(long value) {
        addGreaterThan(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdLt(long value) {
        addLessThan(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection movieIdLtEq(long value) {
        addLessThanOrEquals(TrailerColumns.MOVIE_ID, value);
        return this;
    }

    public TrailerSelection orderByMovieId(boolean desc) {
        orderBy(TrailerColumns.MOVIE_ID, desc);
        return this;
    }

    public TrailerSelection orderByMovieId() {
        orderBy(TrailerColumns.MOVIE_ID, false);
        return this;
    }

    public TrailerSelection favoriteMoviedbId(String... value) {
        addEquals(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection favoriteMoviedbIdNot(String... value) {
        addNotEquals(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection favoriteMoviedbIdLike(String... value) {
        addLike(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection favoriteMoviedbIdContains(String... value) {
        addContains(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection favoriteMoviedbIdStartsWith(String... value) {
        addStartsWith(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection favoriteMoviedbIdEndsWith(String... value) {
        addEndsWith(FavoriteColumns.MOVIEDB_ID, value);
        return this;
    }

    public TrailerSelection orderByFavoriteMoviedbId(boolean desc) {
        orderBy(FavoriteColumns.MOVIEDB_ID, desc);
        return this;
    }

    public TrailerSelection orderByFavoriteMoviedbId() {
        orderBy(FavoriteColumns.MOVIEDB_ID, false);
        return this;
    }

    public TrailerSelection favoriteTitle(String... value) {
        addEquals(FavoriteColumns.TITLE, value);
        return this;
    }

    public TrailerSelection favoriteTitleNot(String... value) {
        addNotEquals(FavoriteColumns.TITLE, value);
        return this;
    }

    public TrailerSelection favoriteTitleLike(String... value) {
        addLike(FavoriteColumns.TITLE, value);
        return this;
    }

    public TrailerSelection favoriteTitleContains(String... value) {
        addContains(FavoriteColumns.TITLE, value);
        return this;
    }

    public TrailerSelection favoriteTitleStartsWith(String... value) {
        addStartsWith(FavoriteColumns.TITLE, value);
        return this;
    }

    public TrailerSelection favoriteTitleEndsWith(String... value) {
        addEndsWith(FavoriteColumns.TITLE, value);
        return this;
    }

    public TrailerSelection orderByFavoriteTitle(boolean desc) {
        orderBy(FavoriteColumns.TITLE, desc);
        return this;
    }

    public TrailerSelection orderByFavoriteTitle() {
        orderBy(FavoriteColumns.TITLE, false);
        return this;
    }

    public TrailerSelection favoriteReleaseDate(String... value) {
        addEquals(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection favoriteReleaseDateNot(String... value) {
        addNotEquals(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection favoriteReleaseDateLike(String... value) {
        addLike(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection favoriteReleaseDateContains(String... value) {
        addContains(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection favoriteReleaseDateStartsWith(String... value) {
        addStartsWith(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection favoriteReleaseDateEndsWith(String... value) {
        addEndsWith(FavoriteColumns.RELEASE_DATE, value);
        return this;
    }

    public TrailerSelection orderByFavoriteReleaseDate(boolean desc) {
        orderBy(FavoriteColumns.RELEASE_DATE, desc);
        return this;
    }

    public TrailerSelection orderByFavoriteReleaseDate() {
        orderBy(FavoriteColumns.RELEASE_DATE, false);
        return this;
    }

    public TrailerSelection favoriteRating(Double... value) {
        addEquals(FavoriteColumns.RATING, value);
        return this;
    }

    public TrailerSelection favoriteRatingNot(Double... value) {
        addNotEquals(FavoriteColumns.RATING, value);
        return this;
    }

    public TrailerSelection favoriteRatingGt(double value) {
        addGreaterThan(FavoriteColumns.RATING, value);
        return this;
    }

    public TrailerSelection favoriteRatingGtEq(double value) {
        addGreaterThanOrEquals(FavoriteColumns.RATING, value);
        return this;
    }

    public TrailerSelection favoriteRatingLt(double value) {
        addLessThan(FavoriteColumns.RATING, value);
        return this;
    }

    public TrailerSelection favoriteRatingLtEq(double value) {
        addLessThanOrEquals(FavoriteColumns.RATING, value);
        return this;
    }

    public TrailerSelection orderByFavoriteRating(boolean desc) {
        orderBy(FavoriteColumns.RATING, desc);
        return this;
    }

    public TrailerSelection orderByFavoriteRating() {
        orderBy(FavoriteColumns.RATING, false);
        return this;
    }

    public TrailerSelection favoritePosterImage(String... value) {
        addEquals(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public TrailerSelection favoritePosterImageNot(String... value) {
        addNotEquals(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public TrailerSelection favoritePosterImageLike(String... value) {
        addLike(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public TrailerSelection favoritePosterImageContains(String... value) {
        addContains(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public TrailerSelection favoritePosterImageStartsWith(String... value) {
        addStartsWith(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public TrailerSelection favoritePosterImageEndsWith(String... value) {
        addEndsWith(FavoriteColumns.POSTER_IMAGE, value);
        return this;
    }

    public TrailerSelection orderByFavoritePosterImage(boolean desc) {
        orderBy(FavoriteColumns.POSTER_IMAGE, desc);
        return this;
    }

    public TrailerSelection orderByFavoritePosterImage() {
        orderBy(FavoriteColumns.POSTER_IMAGE, false);
        return this;
    }

    public TrailerSelection favoriteSynopsis(String... value) {
        addEquals(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public TrailerSelection favoriteSynopsisNot(String... value) {
        addNotEquals(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public TrailerSelection favoriteSynopsisLike(String... value) {
        addLike(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public TrailerSelection favoriteSynopsisContains(String... value) {
        addContains(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public TrailerSelection favoriteSynopsisStartsWith(String... value) {
        addStartsWith(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public TrailerSelection favoriteSynopsisEndsWith(String... value) {
        addEndsWith(FavoriteColumns.SYNOPSIS, value);
        return this;
    }

    public TrailerSelection orderByFavoriteSynopsis(boolean desc) {
        orderBy(FavoriteColumns.SYNOPSIS, desc);
        return this;
    }

    public TrailerSelection orderByFavoriteSynopsis() {
        orderBy(FavoriteColumns.SYNOPSIS, false);
        return this;
    }

    public TrailerSelection name(String... value) {
        addEquals(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameNot(String... value) {
        addNotEquals(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameLike(String... value) {
        addLike(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameContains(String... value) {
        addContains(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameStartsWith(String... value) {
        addStartsWith(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection nameEndsWith(String... value) {
        addEndsWith(TrailerColumns.NAME, value);
        return this;
    }

    public TrailerSelection orderByName(boolean desc) {
        orderBy(TrailerColumns.NAME, desc);
        return this;
    }

    public TrailerSelection orderByName() {
        orderBy(TrailerColumns.NAME, false);
        return this;
    }

    public TrailerSelection url(String... value) {
        addEquals(TrailerColumns.URL, value);
        return this;
    }

    public TrailerSelection urlNot(String... value) {
        addNotEquals(TrailerColumns.URL, value);
        return this;
    }

    public TrailerSelection urlLike(String... value) {
        addLike(TrailerColumns.URL, value);
        return this;
    }

    public TrailerSelection urlContains(String... value) {
        addContains(TrailerColumns.URL, value);
        return this;
    }

    public TrailerSelection urlStartsWith(String... value) {
        addStartsWith(TrailerColumns.URL, value);
        return this;
    }

    public TrailerSelection urlEndsWith(String... value) {
        addEndsWith(TrailerColumns.URL, value);
        return this;
    }

    public TrailerSelection orderByUrl(boolean desc) {
        orderBy(TrailerColumns.URL, desc);
        return this;
    }

    public TrailerSelection orderByUrl() {
        orderBy(TrailerColumns.URL, false);
        return this;
    }
}
