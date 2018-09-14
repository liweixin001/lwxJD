package com.example.lenovo.lwxjingdong.presenter;


import com.example.lenovo.lwxjingdong.bean.FristBean;
import com.example.lenovo.lwxjingdong.contants.Contract;

import io.reactivex.functions.Consumer;

/**
 * Created by admin on 2018/9/8.
 */

public class Presenter extends Contract.Presenter {
    @Override
    public void slect() {
        mModel.slect().subscribe(new Consumer<FristBean>() {
            @Override
            public void accept(FristBean fristBean) throws Exception {
                mView.success( fristBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.fail("网络有问题 ，请稍候再试");
            }
        });
    }
}
