package testanim.user.flipkartemulate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by USER on 01-Jan-16.
 */
public class SlideshowFragment extends Fragment {


public Fragment getInstance(int position)
{
    SlideshowFragment fragment=new SlideshowFragment();
    return  fragment;
}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View slide1=inflater.inflate(R.layout.slideshow_fragment,container,false);
        return slide1;

    }
}
