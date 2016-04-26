package com.s4you.FlyBeau.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import com.s4you.FlyBeau.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewImageFragment extends Fragment {
    int b;
    private int imageId;
    boolean touch = false;
    SubsamplingScaleImageView imageView;

    public ViewImageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_image, container, false);
        imageView = (SubsamplingScaleImageView) view.findViewById(R.id.imgViewImage);

        imageView.setImage(ImageSource.resource(imageId));

        return view;
    }


    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setImageRotate() {
        imageView.setOrientation((imageView.getOrientation() + 90) % 360);
    }


}//END
