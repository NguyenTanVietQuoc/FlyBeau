package datviet.FlyBeau.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import datviet.FlyBeau.R;
import datviet.FlyBeau.activity.Empty_Fragment;
import datviet.FlyBeau.activity.HomeFragment;
import datviet.FlyBeau.activity.MainActivity;
import datviet.FlyBeau.activity.SearchFragment;
import datviet.FlyBeau.activity.UserFragment;

/**
 * Created by Zanty on 13/04/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    TextView tv;
    ImageView img;
    private  int[] icons = {R.drawable.tab_home_icon, R.drawable.tab_search_icon, R.drawable.tab_user_icon};
    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        HomeFragment home = new HomeFragment();
        SearchFragment search =  new SearchFragment();
        UserFragment user = new UserFragment();
        switch (position)
        {
            case 0:
                return home;
            case 1:
                return search;
            case 2:
                return user;
            default:
                return home;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tabs, null);
        ImageView img = (ImageView) v.findViewById(R.id.iv_customtabs);
        img.setImageResource(icons[position]);
        return v;
    }
}
