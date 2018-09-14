package com.example.lenovo.lwxjingdong.presenter;

import android.text.TextUtils;

import com.example.lenovo.lwxjingdong.bean.RegisterBean;
import com.example.lenovo.lwxjingdong.contants.RegisterConteact;
import com.example.lenovo.lwxjingdong.utils.RegexValidateUtil;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by lenovo on 2018/9/13.
 */

public class RegisterPresenter extends RegisterConteact.RegisterPresenter{
    private RegisterConteact.RegisterView registerView;

    @Override
    public void register(HashMap<String, String> map) {
        mModel.Register( map ).subscribe( new Consumer<RegisterBean>() {
            @Override
            public void accept(RegisterBean registerBean) throws Exception {
                mView.success( registerBean );
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.failure( "异常" );
            }
        } );
    }

    public void login(String moblie,String pwd){
        if (TextUtils.isEmpty( moblie )){
            registerView.mobileEmpty();
            return;
        }
        if (!RegexValidateUtil.checkCellphone(moblie)){
            registerView.mobileVerify();
            return;
        }
        if (TextUtils.isEmpty( pwd )){
            registerView.pwdVerify();
            return;
        }
    }
}
