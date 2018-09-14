package com.example.lenovo.lwxjingdong.model;

import com.example.lenovo.lwxjingdong.app.RegisterApi;
import com.example.lenovo.lwxjingdong.bean.RegisterBean;
import com.example.lenovo.lwxjingdong.common.Contants;
import com.example.lenovo.lwxjingdong.contants.RegisterConteact;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lianxibase1.bwie.com.base.net.RetrofitUtils;

/**
 * Created by lenovo on 2018/9/13.
 */

public class RegisterModel implements RegisterConteact.registeriModel{
    @Override
    public Observable<RegisterBean> Register(HashMap<String, String> map) {
        return RetrofitUtils.getInstance()
                .createApi( Contants.REGISTER,RegisterApi.class )
                .Reigster( map )
                .subscribeOn( Schedulers.newThread() )
                .observeOn( AndroidSchedulers.mainThread() );
    }
}
