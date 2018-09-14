package com.example.lenovo.lwxjingdong.model;

import com.example.lenovo.lwxjingdong.app.LoginApi;
import com.example.lenovo.lwxjingdong.bean.LoginBean;
import com.example.lenovo.lwxjingdong.common.Contants;
import com.example.lenovo.lwxjingdong.contants.LoginContract;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lianxibase1.bwie.com.base.net.RetrofitUtils;

/**
 * Created by lenovo on 2018/9/13.
 */

public class LoginModel implements LoginContract.loginModel {
    @Override
    public Observable<LoginBean> Login(HashMap<String, String> map) {
        return RetrofitUtils.getInstance().createApi( Contants.LoginAll,LoginApi.class )
                .Login( map ).subscribeOn( Schedulers.newThread() )
                .observeOn( AndroidSchedulers.mainThread() )
                ;
    }
}
