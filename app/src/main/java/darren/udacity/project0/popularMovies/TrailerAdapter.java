package darren.udacity.project0.popularMovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import darren.udacity.project0.R;

/**
 * Created by Darren on 10/12/2015.
 * <p/>
 * Adapter for displaying trailers
 */
public class TrailerAdapter extends BaseAdapter implements Serializable {
    Context context = null;
    private ArrayList<YoutubeVideo> videos = null;

    TrailerAdapter(Context context, ArrayList<YoutubeVideo> videos) {
        super();
        this.videos = videos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_element_trailer, null);
        } else {
            view = convertView;
        }

        TextView trailerTitle = (TextView) view.findViewById(R.id.trailer_name);
        YoutubeVideo video = (YoutubeVideo) getItem(position);

        trailerTitle.setText(video.getTitle());

        return view;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }
}
