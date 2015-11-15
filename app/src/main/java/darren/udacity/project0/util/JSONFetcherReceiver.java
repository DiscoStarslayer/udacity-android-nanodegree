package darren.udacity.project0.util;

import org.json.JSONObject;

public interface JSONFetcherReceiver {
    void processJSON(JSONObject object, String key);
}
