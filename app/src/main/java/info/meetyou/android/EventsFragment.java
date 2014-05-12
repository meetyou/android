package info.meetyou.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午3:28
 * Mail: specialcyci@gmail.com
 */
public class EventsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.events, container, false);
    }

}
