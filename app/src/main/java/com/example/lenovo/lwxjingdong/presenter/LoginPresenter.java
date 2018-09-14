package com.example.lenovo.lwxjingdong.presenter;

import com.example.lenovo.lwxjingdong.bean.LoginBean;
import com.example.lenovo.lwxjingdong.contants.LoginContract;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by lenovo on 2018/9/13.
 */

public class LoginPresenter extends LoginContract.LoginPresenter{
    @Override
    public void login(HashMap<String, String> map) {
        mModel.Login( map ).subscribe( new Consumer<LoginBean>() {
            @Override
            public void accept(LoginBean loginBean) throws Exception {
                mView.success( loginBean );
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.failure( "异常" );
            }
        } );
    }
}
