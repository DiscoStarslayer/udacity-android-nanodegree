package darren.udacity.project0.popularMovies.data.trailer;

import android.net.Uri;
import android.provider.BaseColumns;

import darren.udacity.project0.popularMovies.data.FavoritesProvider;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteColumns;
import darren.udacity.project0.popularMovies.data.trailer.TrailerColumns;

/**
 * Table of all the trailers
 */
public class TrailerColumns implements BaseColumns {
    public static final String TABLE_NAME = "trailer";
    public static final Uri CONTENT_URI = Uri.parse(FavoritesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";

    /**
     * Name of the trailer
     */
    public static final String NAME = "name";

    /**
     * URL of the trailer
     */
    public static final String URL = "url";


    public static final String DEFAULT_ORDER = _ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            NAME,
            URL
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("" + MOVIE_ID)) return true;
            if (c.equals(NAME) || c.contains("" + NAME)) return true;
            if (c.equals(URL) || c.contains("" + URL)) return true;
        }
        return false;
    }

    public static final String PREFIX_FAVORITE = TABLE_NAME + "__" + FavoriteColumns.TABLE_NAME;
}
