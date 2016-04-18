package datviet.FlyBeau.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

import datviet.FlyBeau.R;

public class ViewImageActivity extends AppCompatActivity {
    int a;
    ViewPager viewPager;
    ImageView imgRotate;

    ScreenSlidePagerAdapter adapter;
    ArrayList<Integer> lstImageId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        init();
        setupViewPager();
    }

    public void init() {
        viewPager = (ViewPager) findViewById(R.id.container);
        imgRotate = (ImageView) findViewById(R.id.imgRotate);

        imgRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewImageFragment currentFragment = (ViewImageFragment) adapter.getCurrentFragment(viewPager.getCurrentItem());
                currentFragment.setImageRotate();
            }
        });
    }

    private void setupViewPager() {
        lstImageId.add(R.drawable.ha);
        lstImageId.add(R.drawable.hb);
        lstImageId.add(R.drawable.hc);
        lstImageId.add(R.drawable.hd);
        lstImageId.add(R.drawable.he);
        lstImageId.add(R.drawable.hf);
        lstImageId.add(R.drawable.hk);
        lstImageId.add(R.drawable.hkk);
        lstImageId.add(R.drawable.hkkk);
        adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(2);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Tránh lag khi dùng hình độ phân giải cao
                //Nên đặt hình vào thư mục drawable theo đúng dpi
                if (state != ViewPager.SCROLL_STATE_IDLE) {
                    final int childCount = viewPager.getChildCount();
                    for (int i = 0; i < childCount; i++)
                        viewPager.getChildAt(i).setLayerType(View.LAYER_TYPE_HARDWARE, null);
                }
            }
        });


    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        //Dùng hashmap để add, get, remove fragment theo key
        private HashMap mFragmentMap = new HashMap();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(int position, ViewImageFragment fragment) {
            mFragmentMap.put(position, fragment);
        }

        @Override
        public Fragment getItem(int position) {
            try {
                ViewImageFragment fragment = new ViewImageFragment();
                fragment.setImageId(lstImageId.get(position));
                addFragment(position, fragment);
                return fragment;
            } catch (Exception e) {
                return new Empty_Fragment();
            }

        }

        public Fragment getCurrentFragment(int position) {
           // return mFragmentList.get(position);
            return (Fragment) mFragmentMap.get(position);
        }

        @Override
        public int getCount() {
            return lstImageId.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
           // mFragmentList.remove(position);
            mFragmentMap.remove(position);
        }
    }///END adapter

    @Override
    public void onBackPressed() {
//        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
//        } else {
//            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
//        }
    }

}//END
