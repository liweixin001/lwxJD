package com.example.lenovo.lwxjingdong.presenter;

import com.example.lenovo.lwxjingdong.bean.ThreeBean;
import com.example.lenovo.lwxjingdong.contants.ThreeContract;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by lenovo on 2018/9/13.
 */

public class ThreePresenter  extends ThreeContract.ThreePresenter{

    @Override
    public void shopping(HashMap<String,String> parms) {
        mModel.shopping(parms).subscribe(new Consumer<ThreeBean>() {
            @Override
            public void accept(ThreeBean threeBean) throws Exception {
                mView.success(threeBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.fail("网络有问题 ，请稍候再试");
            }
        });
    }
}
