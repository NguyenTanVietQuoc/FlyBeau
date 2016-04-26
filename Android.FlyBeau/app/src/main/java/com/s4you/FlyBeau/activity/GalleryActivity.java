package com.s4you.FlyBeau.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.s4you.FlyBeau.R;
import com.s4you.FlyBeau.view.adapter.GalleryAdapter;

import java.util.ArrayList;

public class GalleryActivity extends Activity {
    RecyclerView recyclerView;
    GalleryAdapter adapter;
    ArrayList<String> lstURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        init();

    }

    private void init(){
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);

        lstURL = new ArrayList<>();
        for (int i = 0; i <50; i++)
            lstURL.add("http://4k.com/wp-content/uploads/2014/06/4k-image-tiger-jumping.jpg");
        adapter = new GalleryAdapter(GalleryActivity.this, lstURL);
        recyclerView.setAdapter(adapter);

    }
}//END
