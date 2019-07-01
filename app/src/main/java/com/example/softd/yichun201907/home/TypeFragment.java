package com.example.softd.yichun201907.home;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softd.yichun201907.R;
import com.example.softd.yichun201907.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypeFragment extends BaseFragment {


    public TypeFragment() {
        // Required empty public constructor
    }


  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }
*/
    @Override
    protected int initLayout() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

}
