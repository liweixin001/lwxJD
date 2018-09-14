package com.example.lenovo.lwxjingdong.contants;

import com.example.lenovo.lwxjingdong.bean.RegisterBean;
import com.example.lenovo.lwxjingdong.model.RegisterModel;

import java.util.HashMap;

import io.reactivex.Observable;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
import lianxibase1.bwie.com.base.base.mvp.IBaseModel;

/**
 * Created by lenovo on 2018/9/13.
 */

public interface RegisterConteact {
    abstract class RegisterPresenter extends BasePresenter<RegisterModel,RegisterView>{
        @Override
        public RegisterModel getmModel() {
            return new RegisterModel();
        }
        public abstract void register(HashMap<String,String>map);
    }
    interface registeriModel extends IBaseModel{
        Observable<RegisterBean>Register(HashMap<String,String>map);
    }
     interface RegisterView{
        void success(RegisterBean registerBean);
        void failure(String error);
        void mobileVerify();
        void mobileEmpty();
        void pwdVerify();
    }
}
