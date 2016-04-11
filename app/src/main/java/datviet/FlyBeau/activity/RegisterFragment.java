package datviet.FlyBeau.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import datviet.FlyBeau.R;

//git remote add origin https://github.com/nthanhnambmt/Fly-Beau.git

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    EditText etFullName, etEmail, etCountry, etCity, etUser, etPass;
    Button btNext;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        init(v);
        return v;
    }//END onCreateView

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public  void init(View v)
    {
        etFullName = (EditText) v.findViewById(R.id.etFullName);
        etUser = (EditText) v.findViewById(R.id.etUser);
        etEmail= (EditText) v.findViewById(R.id.etEmail);
        etPass = (EditText) v.findViewById(R.id.etPass);
        etCountry = (EditText) v.findViewById(R.id.etCountry);
        etCity = (EditText) v.findViewById(R.id.etCity);
        etFullName.requestFocus();

        btNext = (Button) v.findViewById(R.id.btNext);

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).loadFragment("pickgroup");
            }

        });


    }
}//
