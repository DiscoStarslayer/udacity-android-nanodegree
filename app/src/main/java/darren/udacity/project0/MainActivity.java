package darren.udacity.project0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
    }

    private Toast createToastForApp(String appName) {
        Context context = getApplicationContext();
        String baseText = "This button will launch my ";
        String terminator = " app!";

        CharSequence toastText = baseText + appName + terminator;

        return Toast.makeText(context, toastText, Toast.LENGTH_SHORT);
    }

    public void spotifyClicked(View view) {
        Intent intent = new Intent();
        intent.setClass(view.getContext(), darren.udacity.project0.popularMovies.MainActivity.class);
        startActivity(intent);
    }

    public void scoresClicked(View view) {
        Toast toast = createToastForApp("scores");
        toast.show();
    }

    public void libraryClicked(View view) {
        Toast toast = createToastForApp("library");
        toast.show();
    }

    public void buildItBiggerClicked(View view) {
        Toast toast = createToastForApp("build it bigger");
        toast.show();
    }

    public void xyzReaderClicked(View view) {
        Toast toast = createToastForApp("xyz reader");
        toast.show();
    }

    public void capstoneClicked(View view) {
        Toast toast = createToastForApp("capstone");
        toast.show();
    }
}
