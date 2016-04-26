package com.s4you.FlyBeau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.s4you.FlyBeau.R;

/**
 * Created by NamNgo on 10/04/2016.
 */
public class LoginFragment  extends Fragment{
    EditText etUser, etPass;
    Button btLogin, btRegister;
//
    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        init(v);

        return v;
    }//END onCreateView

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 600);

    }

    public  void init(View v)
    {
        etUser = (EditText) v.findViewById(R.id.etUser);
        etUser.requestFocus();
        etPass = (EditText) v.findViewById(R.id.etPass);
        btLogin = (Button) v.findViewById(R.id.btLogin);
        btRegister = (Button) v.findViewById(R.id.btRegister);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),MainActivity.class));
            }

        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginActivity)getActivity()).loadFragment("register");
            }
        });
    }

}//END
