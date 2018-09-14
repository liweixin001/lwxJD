package com.example.lenovo.lwxjingdong.model;

import com.example.lenovo.lwxjingdong.app.TwoApi;
import com.example.lenovo.lwxjingdong.bean.TwoBean;
import com.example.lenovo.lwxjingdong.common.Contants;
import com.example.lenovo.lwxjingdong.contants.TwoContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lianxibase1.bwie.com.base.net.RetrofitUtils;

/**
 * Created by lenovo on 2018/9/12.
 */

public class TwoModel implements TwoContract.TwoModel {
    @Override
    public Observable<TwoBean> fenlei() {
        return RetrofitUtils.getInstance().createApi( Contants.BASE_RELEASE_URL, TwoApi.class)
                .feilei().subscribeOn( Schedulers.io()).observeOn( AndroidSchedulers.mainThread());
    }
}
