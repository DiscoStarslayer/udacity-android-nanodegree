package darren.udacity.project0.util;

import android.os.AsyncTask;
import android.util.Log;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Darren on 7/12/2015.
 * <p/>
 * Class to fetch json responses from a server asynchronously
 */
public class JSONFetcher extends AsyncTask<String, Void, JSONObject> {
    private static String LOG_TAG = JSONFetcher.class.getSimpleName();
    JSONFetcherReceiver callback = null;
    String key = null;

    OkHttpClient client = new OkHttpClient();

    public JSONFetcher(JSONFetcherReceiver callback) {
        this.callback = callback;
        client.networkInterceptors().add(new StethoInterceptor());
    }

    @Override
    protected void onPostExecute(JSONObject object) {
        super.onPostExecute(object);

        callback.processJSON(object, key);
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        String jsonString = null;
        key = params[1];

        try {
            Request request = new Request.Builder()
                    .url(params[0])
                    .build();

            Response response = client.newCall(request).execute();
            jsonString = response.body().string();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error: ", e);
            return null;
        }

        try {
            return processJson(jsonString);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }

    JSONObject processJson(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }
}

