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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import datviet.FlyBeau.R;
import datviet.FlyBeau.model.Group;
import datviet.FlyBeau.view.adapter.PickGroupAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PickGroupFragment extends Fragment {

    ListView lvGroup;
    ImageView imgMicro;
    SearchView searchView;
    SwipeRefreshLayout swipeRefresh;
    FloatingActionButton fab;
    Snackbar snackbar;
    PickGroupAdapter adapter;
    ArrayList<Group> lstGroup;
    final int VOICE_RECOGNITION = 222;

    public PickGroupFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pick_group, container, false);
        init(v);
        snackbar = Snackbar.make(v, "Chọn một trường bạn muốn tham gia", Snackbar.LENGTH_LONG)
                .setAction("Action", null);
        snackbar.show();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListViewAdapter();
    }

    public void init(View v)
    {

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
        lstGroup = new ArrayList<>();

        taoDuLieu();

        adapter = new PickGroupAdapter(getActivity(), lstGroup);
        lvGroup.setAdapter(adapter);
        lvGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), lstGroup.get(position).groupName, Toast.LENGTH_LONG).show();
                ((LoginActivity)getActivity()).loadFragment("home");
            }
        });

    }

    public void taoDuLieu()
    {
        Group a = new Group("Viet Nam National University", "1000", R.drawable.a);
        Group b = new Group("Ho Chi Minh City University", "2000", R.drawable.b);
        Group c = new Group("Can Tho University", "3000", R.drawable.c);
        Group d = new Group("Hanoi Medical University", "4000", R.drawable.a);
        Group e = new Group("Hue College of Medicine", "5000", R.drawable.b);
        Group f = new Group("Thai Nguyen University", "6000", R.drawable.c);
        Group g = new Group("Hanoi University of Mining and Geology", "7000", R.drawable.a);
        Group h = new Group("Da Nang University of Economics ", "8000", R.drawable.b);
        Group i = new Group("Ho Chi Minh City University of Science ", "9000", R.drawable.c);
        Group j = new Group("Viet Nam National University", "11000", R.drawable.a);
        Group k = new Group("Ho Chi Minh City University of Technology", "12000", R.drawable.b);
        lstGroup.add(a);
        lstGroup.add(b);
        lstGroup.add(c);
        lstGroup.add(d);
        lstGroup.add(e);
        lstGroup.add(f);
        lstGroup.add(g);
        lstGroup.add(h);
        lstGroup.add(i);
        lstGroup.add(j);
        lstGroup.add(k);
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
                if(prevVisibleItem != firstVisibleItem) {
                    if (prevVisibleItem < firstVisibleItem) {
                        //ScrollDown
                        fab.setVisibility(View.GONE);
                        Log.d(LoginActivity.tag, "ScrollDown");
                    }

                    else
                    {
                        //ScrollUp
                        fab.setVisibility(View.VISIBLE);
                        Log.d(LoginActivity.tag, "ScrollUp");
                    }
                    prevVisibleItem = firstVisibleItem;
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
