package com.example.lenovo.lwxjingdong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.lwxjingdong.R;

/**
 * Created by lenovo on 2018/9/12.
 */

public class FragmentFind extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate( getActivity(), R.layout.activity_main,null );
        return view;
    }

}
