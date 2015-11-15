package darren.udacity.project0.util;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Darren on 7/12/2015.
 * <p/>
 * Class to fetch json responses from a server asynchronously
 */
public class JSONFetcher extends AsyncTask<String, Void, JSONObject> {
    private static String LOG_TAG = JSONFetcher.class.getSimpleName();
    JSONFetcherReceiver callback = null;
    String key = null;

    public JSONFetcher(JSONFetcherReceiver callback) {
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(JSONObject object) {
        super.onPostExecute(object);

        callback.processJSON(object, key);
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        String jsonString = null;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        key = params[1];

        try {
            URL url = new URL(params[0]);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("/n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            jsonString = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error: ", e);
            return null;
        } finally {
            if (urlConnection != null && reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream: ", e);
                }
            }
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

