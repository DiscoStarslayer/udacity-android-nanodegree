package darren.udacity.project0.popularMovies;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;

import darren.udacity.project0.R;
import darren.udacity.project0.popularMovies.data.FavoritesProvider;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteColumns;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteContentValues;
import darren.udacity.project0.popularMovies.data.favorite.FavoriteCursor;
import darren.udacity.project0.popularMovies.data.trailer.TrailerContentValues;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_popular_movies);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_popular_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        PopularMovieActivityFragment fragment =
                (PopularMovieActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_popularity) {
            fragment.updateMoviesUri("popular");
        } else if (id == R.id.action_rating) {
            fragment.updateMoviesUri("top_rated");
        } else if (id == R.id.action_favorite) {
        }

        return super.onOptionsItemSelected(item);
    }
}
