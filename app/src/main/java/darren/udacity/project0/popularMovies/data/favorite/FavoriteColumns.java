package darren.udacity.project0.popularMovies.data.favorite;

import android.net.Uri;
import android.provider.BaseColumns;

import darren.udacity.project0.popularMovies.data.FavoritesProvider;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteColumns;
import darren.udacity.project0.popularMovies.data.trailer.TrailerColumns;

/**
 * A favorite movie
 */
public class FavoriteColumns implements BaseColumns {
    public static final String TABLE_NAME = "favorite";
    public static final Uri CONTENT_URI = Uri.parse(FavoritesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * The moviedb id
     */
    public static final String MOVIEDB_ID = "moviedb_id";

    /**
     * The title of the movie
     */
    public static final String TITLE = "title";

    /**
     * The release date of the movie
     */
    public static final String RELEASE_DATE = "release_date";

    /**
     * The voted rating of the movie
     */
    public static final String RATING = "rating";

    /**
     * The URL pointing to the poster
     */
    public static final String POSTER_IMAGE = "poster_image";

    /**
     * The synopsis of the movie
     */
    public static final String SYNOPSIS = "synopsis";


    public static final String DEFAULT_ORDER = _ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIEDB_ID,
            TITLE,
            RELEASE_DATE,
            RATING,
            POSTER_IMAGE,
            SYNOPSIS
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIEDB_ID) || c.contains("" + MOVIEDB_ID)) return true;
            if (c.equals(TITLE) || c.contains("" + TITLE)) return true;
            if (c.equals(RELEASE_DATE) || c.contains("" + RELEASE_DATE)) return true;
            if (c.equals(RATING) || c.contains("" + RATING)) return true;
            if (c.equals(POSTER_IMAGE) || c.contains("" + POSTER_IMAGE)) return true;
            if (c.equals(SYNOPSIS) || c.contains("" + SYNOPSIS)) return true;
        }
        return false;
    }

}
