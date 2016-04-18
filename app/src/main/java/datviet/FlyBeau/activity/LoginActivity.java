package datviet.FlyBeau.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import datviet.FlyBeau.R;

public class LoginActivity extends AppCompatActivity {

    public static String tag = "loi"; ///test 18/04
    private SectionsPagerAdapter adapter;
    FrameLayout flContainer;
    boolean loginShowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        flContainer = (FrameLayout) findViewById(R.id.container);

        setupAdapter();

    }//END oncreate

    private void setupAdapter() {
        adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        LoginFragment login = new LoginFragment();
//        Bundle bCN = new Bundle();
//        bCN.putString("role", "cn");
//        main.setArguments(bCN);
        adapter.addFragment(login, "login");

        RegisterFragment register = new RegisterFragment();
        adapter.addFragment(register, "register");

        PickGroupFragment group = new PickGroupFragment();
        adapter.addFragment(group, "group");

        HomeFragment home = new HomeFragment();
        adapter.addFragment(home, "home");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, adapter.getItem(0))
                .commit();
        loginShowing = true;
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {

            try {
                return mFragmentList.get(position);
            } catch (Exception e) {
                return new Empty_Fragment();
            }

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }


    public void loadFragment(String request) {
        ///if (loginShowing)
        {
            switch (request) {
                case "login":

                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations
                                    (R.anim.slide_in_right, R.anim.slide_out_left,
                                            R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.container, adapter.getItem(0))
                            .commit();
                    loginShowing = true;

                    break;
                case "register":
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations
                                    (R.anim.slide_in_right, R.anim.slide_out_left,
                                            R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.container, adapter.getItem(1))
                            .addToBackStack(null)
                            .commit();
                    loginShowing = false;
                    break;
                case "pickgroup":
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations
                                    (R.anim.slide_in_right, R.anim.slide_out_left,
                                            R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.container, adapter.getItem(2))
                            .addToBackStack(null)
                            .commit();
                    loginShowing = false;
                    break;
                case "home":
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations
                                    (R.anim.slide_in_right, R.anim.slide_out_left,
                                            R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.container, adapter.getItem(3))
//                            .replace(R.id.container, new HomeFragment())
                            .addToBackStack(null)
                            .commit();
                    loginShowing = false;
                    break;
            }
        }
//        else {
//            getSupportFragmentManager().popBackStack();
//        }
    }

    @Override
    public void onBackPressed() {
//        if (loginShowing)
            super.onBackPressed();
//        else
//            getSupportFragmentManager().popBackStack();
    }

}//END

