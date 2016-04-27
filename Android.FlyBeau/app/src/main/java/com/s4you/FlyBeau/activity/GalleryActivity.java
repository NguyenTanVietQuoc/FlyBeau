package com.s4you.FlyBeau.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.s4you.FlyBeau.R;
import com.s4you.FlyBeau.view.adapter.GalleryAdapter;

import java.util.ArrayList;

public class GalleryActivity extends Activity {
    RecyclerView recyclerView;
    CircularFloating circularFloating;
    GalleryAdapter adapter;
    ArrayList<String> lstURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        init();
        control();
    }

    private void init(){
        circularFloating = new CircularFloating(this);
        circularFloating.createCircularFloating();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);

        //Set number column of gallery
//        GridLayoutManager manager = new GridLayoutManager(this, 3);
//        recyclerView.setLayoutManager(manager);

        StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager1);

        lstURL = new ArrayList<>();
        for (int i = 0; i <50; i++) {
//            lstURL.add("http://4k.com/wp-content/uploads/2014/06/4k-image-tiger-jumping.jpg");
            lstURL.add("https://s-media-cache-ak0.pinimg.com/736x/a9/d0/67/a9d067a51a766e076c4233890d538be0.jpg");
            lstURL.add("https://s-media-cache-ak0.pinimg.com/736x/56/11/f9/5611f9b59512a5b840602e19eaf50989.jpg");
            lstURL.add("http://www.roeselienraimond.com/blog/wp-content/uploads/2013/12/fox_portrait.jpg");
            lstURL.add("https://s-media-cache-ak0.pinimg.com/736x/29/13/af/2913af6cee86f3d64d659321ce2ac62a.jpg");
            lstURL.add("http://www.intrawallpaper.com/static/images/hd-widescreen-wallpaper-2_IxEldTT.jpg");
            lstURL.add("http://i.dailymail.co.uk/i/pix/2015/09/28/08/2CD1E26200000578-0-image-a-312_1443424459664.jpg");
            lstURL.add("http://images.nationalgeographic.com/wpf/media-live/photos/000/015/cache/afghan-girl-portrait_1563_990x742.jpg");
        }
            adapter = new GalleryAdapter(GalleryActivity.this, lstURL);
        recyclerView.setAdapter(adapter);

    }

    public void control()
    {
        recyclerView.setOnScrollListener(onScrollListener());
    }

    public  RecyclerView.OnScrollListener onScrollListener() {
        return new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            //ScrollDown
            circularFloating.setVisibleFloatingMenu(false);
        } else {
            //ScrollUp
            circularFloating.setVisibleFloatingMenu(true);
        }

    }
};
}

}//END
