package com.example.lenovo.lwxjingdong.contants;

import com.example.lenovo.lwxjingdong.bean.LoginBean;
import com.example.lenovo.lwxjingdong.model.LoginModel;

import java.util.HashMap;

import io.reactivex.Observable;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
import lianxibase1.bwie.com.base.base.mvp.IBaseModel;

/**
 * Created by lenovo on 2018/9/13.
 */

public interface LoginContract {
    abstract class LoginPresenter extends BasePresenter<LoginModel,LoginView>{
        @Override
        public LoginModel getmModel() {
            return new LoginModel();
        }
        public abstract void login(HashMap<String,String>map);
    }
    interface loginModel extends IBaseModel{
        Observable<LoginBean>Login(HashMap<String,String>map);
    }
    interface LoginView{
        void success(LoginBean loginBean);
        void failure(String error);
    }
}
