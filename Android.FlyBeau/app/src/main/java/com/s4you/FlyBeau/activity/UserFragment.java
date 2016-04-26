package com.s4you.FlyBeau.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.s4you.FlyBeau.R;
import com.s4you.FlyBeau.controller.SetOnLickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {


    LinearLayout layout_user, layout_rating, layout_total_picture, layout_total_point,
            layout_upload_images, layout_setting;
    SetOnLickListener setOnLickListener;
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_user, container, false);

        //MainActivity.circularFloating.setVisibleFloatingMenu(false);

        setOnLickListener = new SetOnLickListener(v,getContext());
        init(v);

        return v;
    }

    public void init(View v){
        layout_user = (LinearLayout) v.findViewById(R.id.layout_user);
        layout_rating = (LinearLayout) v.findViewById(R.id.layout_rating);
        layout_total_picture = (LinearLayout) v.findViewById(R.id.layout_total_picture);
        layout_total_point = (LinearLayout) v.findViewById(R.id.layout_total_point);
        layout_upload_images = (LinearLayout) v.findViewById(R.id.layout_upload_images);
        layout_setting = (LinearLayout) v.findViewById(R.id.layout_setting);

        layout_user.setOnClickListener(setOnLickListener);
        layout_rating.setOnClickListener(setOnLickListener);
        layout_total_point.setOnClickListener(setOnLickListener);
        layout_upload_images.setOnClickListener(setOnLickListener);
        layout_setting.setOnClickListener(setOnLickListener);

        layout_total_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), GalleryActivity.class);
                startActivity(i);
            }
        });

    }

}
