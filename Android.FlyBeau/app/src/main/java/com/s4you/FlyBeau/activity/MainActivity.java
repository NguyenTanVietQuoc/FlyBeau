package com.s4you.FlyBeau.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.s4you.FlyBeau.R;
import com.s4you.FlyBeau.view.adapter.ViewPagerAdapter;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    ViewPagerAdapter adapter;
    static CircularFloating circularFloating;
    public static String POSITION = "POSITION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(),MainActivity.this);
        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);


        circularFloating = new CircularFloating(this);
        circularFloating.createCircularFloating();

        //Số lượng page load sẵn, nhỏ nhất là 1
        mViewPager.setOffscreenPageLimit(2);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
        tabLayout.getTabAt(0).getCustomView().setSelected(true);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
                    circularFloating.setVisibleFloatingMenu(true);
                else
                    circularFloating.setVisibleFloatingMenu(false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mViewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999 && resultCode == RESULT_OK) {
            if (data.getExtras() != null) {
                Bundle extras = data.getExtras();
                Bitmap c = (Bitmap) extras.get("data");
                //Toast.makeText(this,"Source: "+ c.toString(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplication(),UploadImagesActivity.class);
                i.putExtras(extras);
                startActivity(i);
            } else {
                Uri uri = data.getData();
                try {
                    Bitmap c = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    Bundle bundle = new Bundle();
                    bundle.putString("image",data.getDataString());
                    //Toast.makeText(this,"Source: "+ c.toString(),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplication(),UploadImagesActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
