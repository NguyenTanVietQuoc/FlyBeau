package com.s4you.FlyBeau.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;

import com.s4you.FlyBeau.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    ImageView imgMicro;
    SearchView searchView;
    final int VOICE_RECOGNITION = 222;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        imgMicro = (ImageView) v.findViewById(R.id.imgMicro_BC);
        searchView = (SearchView) v.findViewById(R.id.searchView);

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

        //MainActivity.circularFloating.setVisibleFloatingMenu(false);
        return v;
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
}
