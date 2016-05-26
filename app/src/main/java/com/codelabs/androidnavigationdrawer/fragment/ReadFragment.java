package com.codelabs.androidnavigationdrawer.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codelabs.androidnavigationdrawer.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {


    public ReadFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_read, container, false);
        return rootView;
    }

}
