package com.example.lenovo.lwxjingdong.model;

import com.example.lenovo.lwxjingdong.app.Api;
import com.example.lenovo.lwxjingdong.bean.FristBean;
import com.example.lenovo.lwxjingdong.common.Contants;
import com.example.lenovo.lwxjingdong.contants.Contract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lianxibase1.bwie.com.base.net.RetrofitUtils;

/**
 * Created by lenovo on 2018/9/12.
 */

public class Model  implements Contract.Model{
    @Override
    public Observable<FristBean> slect() {
        return RetrofitUtils.getInstance().createApi( Contants.BASE_RELEASE_URL, Api.class)
                .shouye().subscribeOn( Schedulers.io()).observeOn( AndroidSchedulers.mainThread());
    }
}
