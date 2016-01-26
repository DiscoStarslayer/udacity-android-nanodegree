package darren.udacity.project0.popularMovies.data;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import darren.udacity.project0.BuildConfig;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteColumns;
import darren.udacity.project0.popularMovies.data.trailer.TrailerColumns;

public class FavoritesSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = FavoritesSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;
    private static FavoritesSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final FavoritesSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_FAVORITE = "CREATE TABLE IF NOT EXISTS "
            + FavoriteColumns.TABLE_NAME + " ( "
            + FavoriteColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FavoriteColumns.MOVIEDB_ID + " TEXT, "
            + FavoriteColumns.TITLE + " TEXT DEFAULT 'Title', "
            + FavoriteColumns.RELEASE_DATE + " TEXT, "
            + FavoriteColumns.RATING + " REAL, "
            + FavoriteColumns.POSTER_IMAGE + " TEXT, "
            + FavoriteColumns.SYNOPSIS + " TEXT "
            + " );";

    public static final String SQL_CREATE_TABLE_TRAILER = "CREATE TABLE IF NOT EXISTS "
            + TrailerColumns.TABLE_NAME + " ( "
            + TrailerColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TrailerColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + TrailerColumns.NAME + " TEXT NOT NULL, "
            + TrailerColumns.URL + " TEXT NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + TrailerColumns.MOVIE_ID + ") REFERENCES favorite (_id) ON DELETE CASCADE"
            + " );";

    // @formatter:on

    public static FavoritesSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static FavoritesSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static FavoritesSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new FavoritesSQLiteOpenHelper(context);
    }

    private FavoritesSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new FavoritesSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static FavoritesSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new FavoritesSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private FavoritesSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new FavoritesSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_FAVORITE);
        db.execSQL(SQL_CREATE_TABLE_TRAILER);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
