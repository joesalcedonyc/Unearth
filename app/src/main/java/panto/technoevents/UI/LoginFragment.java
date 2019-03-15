package panto.technoevents.UI;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import panto.technoevents.R;

public class LoginFragment extends Fragment {
    private View rootView;
    private VideoView videoView;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
//        videoView = rootView.findViewById(R.id.login_video_background);
//        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.beyer);
//        videoView.setVideoURI(uri);
//        videoView.start();
        return rootView;
    }
}
