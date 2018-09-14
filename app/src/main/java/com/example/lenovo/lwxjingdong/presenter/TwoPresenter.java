package com.example.lenovo.lwxjingdong.presenter;

import com.example.lenovo.lwxjingdong.bean.TwoBean;
import com.example.lenovo.lwxjingdong.contants.TwoContract;

import io.reactivex.functions.Consumer;

/**
 * Created by lenovo on 2018/9/12.
 */

public class TwoPresenter extends TwoContract.TwoPresenter{
    @Override
    public void fenlei() {
        mModel.fenlei().subscribe( new Consumer<TwoBean>() {
            @Override
            public void accept(TwoBean twoBean) throws Exception {
                mView.success( twoBean );
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.fail( "网不好，等等" );
            }
        } );
    }
}
