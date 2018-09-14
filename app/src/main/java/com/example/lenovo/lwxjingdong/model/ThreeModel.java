package com.example.lenovo.lwxjingdong.model;

import com.example.lenovo.lwxjingdong.app.ThreeApi;
import com.example.lenovo.lwxjingdong.bean.ThreeBean;
import com.example.lenovo.lwxjingdong.common.Contants;
import com.example.lenovo.lwxjingdong.contants.ThreeContract;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lianxibase1.bwie.com.base.net.RetrofitUtils;

/**
 * Created by lenovo on 2018/9/13.
 */

public class ThreeModel implements ThreeContract.ThreeModel {
    @Override
    public Observable<ThreeBean> shopping(HashMap<String, String> parms) {
        return RetrofitUtils
                .getInstance()
                .createApi( Contants.BASE_RELEASE_URL, ThreeApi.class )
                .shopping( parms )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() );
    }
}
