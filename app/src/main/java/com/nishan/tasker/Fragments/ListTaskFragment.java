package com.nishan.tasker.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nishan.tasker.R;


public class ListTaskFragment extends TaskerFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_list_task, null);
        initiliseView(view);
        initialiseListener();
        return view;
    }

    @Override
    protected void initiliseView(View view) {

    }

    @Override
    protected void initialiseListener() {

    }
}
