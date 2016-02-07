package darren.udacity.project0.popularMovies;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import darren.udacity.project0.popularMovies.data.trailer.TrailerCursor;

/**
 * Created by Darren on 10/12/2015.
 * Object to represent a youtube video
 */
public class YoutubeVideo implements Serializable {
    String videoId = null;
    String title = null;
    String baseUrl = "http://www.youtube.com/watch?v=";

    YoutubeVideo(JSONObject video) {
        try {
            videoId = video.getString("key");
            title = video.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    YoutubeVideo(TrailerCursor trailerCursor) {
        videoId = trailerCursor.getUrl();
        title = trailerCursor.getName();
        videoId = videoId.replace(baseUrl, "");
    }

    public String getVideoUrl() {
        return baseUrl + videoId;
    }

    public String getTitle() {
        return title;
    }
}
