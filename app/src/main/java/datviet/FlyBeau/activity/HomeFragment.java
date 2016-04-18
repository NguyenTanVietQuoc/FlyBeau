package datviet.FlyBeau.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import datviet.FlyBeau.R;
import datviet.FlyBeau.model.Home;
import datviet.FlyBeau.view.adapter.HomeAdpater;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ListView lvGroup;
    ImageView imgMicro;
    SearchView searchView;
    SwipeRefreshLayout swipeRefresh;
    FloatingActionButton fab;
    Snackbar snackbar;
    HomeAdpater adapter;
    private TextView stickyView;
    private View heroImageView;
    private View stickyViewSpacer;

    ArrayList<Home> lstHome;
    final int VOICE_RECOGNITION = 222;
    boolean isDetach;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        init(v);
        snackbar = Snackbar.make(v, "Chọn một trường bạn muốn tham gia", Snackbar.LENGTH_LONG)
                .setAction("Action", null);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(LoginActivity.tag, "onViewCreated");

        setListViewAdapter();


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LoginActivity.tag, "onResume");
        if (isDetach) {
            //Do set activity có lưu trạng thái
            //nên lỗi header listview khi resume sau khi back pressed
            //nếu có detach thì set lại position listview để ko lỗi
            lvGroup.setSelection(0);

        }
        lvGroup.requestFocus();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LoginActivity.tag, "onPause");
        isDetach = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isDetach = true;
        Log.d(LoginActivity.tag, "onDetach");

    }

    public void init(View v) {

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.show();
            }
        });

        swipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        lvGroup = (ListView) v.findViewById(R.id.lvPickGroup);
        imgMicro = (ImageView) v.findViewById(R.id.imgMicro_BC);
        searchView = (SearchView) v.findViewById(R.id.searchView);

        heroImageView = v.findViewById(R.id.heroImageView);
        stickyView = (TextView) v.findViewById(R.id.stickyView);

        /* Inflate list header layout */
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listHeader = inflater.inflate(R.layout.header_listview_home, null);
        stickyViewSpacer = listHeader.findViewById(R.id.stickyViewPlaceholder);

        /* Add list view header */
        lvGroup.addHeaderView(listHeader);

        lvGroup.setOnScrollListener(onScrollListener());

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                searchView.clearFocus();
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            public void callSearch(String query) {
                //Do searching


            }

        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        imgMicro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // Specify free form input
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Tìm kiếm bằng giọng nói");
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                startActivityForResult(intent, VOICE_RECOGNITION);
            }
        });
    }

    private void setListViewAdapter() {
        lstHome = new ArrayList<>();
        Log.d(LoginActivity.tag, "setListViewAdapter");
        taoDuLieu();

        adapter = new HomeAdpater(getActivity(), lstHome);
        lvGroup.setAdapter(adapter);
        lvGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if (position != 0) {
//                    Toast.makeText(getActivity(), lstHome.get(position - 1).name, Toast.LENGTH_LONG).show();
                    Intent i =  new Intent(getActivity(), ViewImageActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    public void taoDuLieu() {
        for (int x = 0; x < 5; x++) {
            Home a = new Home("Green", "Viet Nam National University", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "1", R.drawable.rsz_ha, R.drawable.rsz_ha);
            Home b = new Home("Blue eye", "Ho Chi Minh City University", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "2", R.drawable.rsz_hb, R.drawable.rsz_faceb);
            Home c = new Home("Yellow", "Can Tho University", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "3000", R.drawable.rsz_1hc, R.drawable.facec);
            Home d = new Home("Orange Juice", "Hanoi Medical University", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "4", R.drawable.hd, R.drawable.hd);
            Home e = new Home("Pink", "Hue College of Medicine", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "5", R.drawable.he, R.drawable.rsz_faceb);
            Home f = new Home("White Hat", "Thai Nguyen University", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "6", R.drawable.hf, R.drawable.hf);
            Home g = new Home("Grey", "Hanoi University of ", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "7", R.drawable.rsz_ha, R.drawable.facea);
            Home h = new Home("Black", "Da Nang University ", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "8", R.drawable.rsz_hb, R.drawable.rsz_faceb);
            Home i = new Home("Green", "Ho Chi Minh City Uni ", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "9", R.drawable.rsz_1hc, R.drawable.rsz_1hc);
            Home j = new Home("Green", "Viet Nam National ", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "11", R.drawable.hd, R.drawable.facea);
            Home k = new Home("Green", "Ho Chi Minh City Unive", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "12", R.drawable.he, R.drawable.rsz_faceb);
            Home hh = new Home("Green", "Da Nang University ", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "8", R.drawable.rsz_hb, R.drawable.rsz_faceb);
            Home ii = new Home("Green", "Ho Chi Minh City Uni ", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "9", R.drawable.rsz_1hc, R.drawable.facec);
            Home jj = new Home("Green", "Viet Nam National ", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "11", R.drawable.hd, R.drawable.facea);
            Home kk = new Home("Green", "Ho Chi Minh City Unive", "Although humans are part of nature, human activity is often understood as a separate category from other natural phenomena.", "12", R.drawable.he, R.drawable.rsz_faceb);

            lstHome.add(a);
            lstHome.add(b);
            lstHome.add(c);
            lstHome.add(d);
            lstHome.add(e);
            lstHome.add(f);
            lstHome.add(g);
            lstHome.add(h);
            lstHome.add(i);
            lstHome.add(j);
            lstHome.add(k);
            lstHome.add(hh);
            lstHome.add(ii);
            lstHome.add(jj);
            lstHome.add(kk);


        }
    }

    //Load more data khi scroll đến item cuối
    private AbsListView.OnScrollListener onScrollListener() {
        return new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int count = lvGroup.getCount();
                if (scrollState == SCROLL_STATE_IDLE) {
                    //position item cuối đang hiển thị mà bằng tổng số item lst thì load
                }


            }

            private int prevVisibleItem = 0;

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {

                if (prevVisibleItem != firstVisibleItem) {
                    if (prevVisibleItem < firstVisibleItem) {
                        //ScrollDown
                        fab.setVisibility(View.GONE);
                        Log.d(LoginActivity.tag, "ScrollDown");
                    } else {
                        //ScrollUp
                        fab.setVisibility(View.VISIBLE);
                        Log.d(LoginActivity.tag, "ScrollUp");
                    }
                    prevVisibleItem = firstVisibleItem;
                }
                if (lvGroup.getFirstVisiblePosition() == 0) {
                    View firstChild = lvGroup.getChildAt(0);
                    int topY = 0;
                    if (firstChild != null) {
                        topY = firstChild.getTop();
                    }

                    int heroTopY = stickyViewSpacer.getTop();
                    stickyView.setY(Math.max(0, heroTopY + topY));
//                    Log.d(LoginActivity.tag, "topY: " + topY);
//                    Log.d(LoginActivity.tag, "heroTopY: " + heroTopY);
                    /* Set the image to scroll half of the amount that of ListView */
                    heroImageView.setY(topY * 1.0f);
                } else {
                    stickyView.setY(0);
                }


            }

        };
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_RECOGNITION && resultCode == getActivity().RESULT_OK) {
            ArrayList<String> results;
            results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            // TODO Do something with the recognized voice strings
            searchView.setQuery(results.get(0), true);
            searchView.setIconifiedByDefault(false);
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
            searchView.clearFocus();
        }
    }

}//END
