package com.example.lenovo.lwxjingdong.contants;

import com.example.lenovo.lwxjingdong.bean.FristBean;
import com.example.lenovo.lwxjingdong.model.Model;

import io.reactivex.Observable;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
import lianxibase1.bwie.com.base.base.mvp.IBaseModel;
import lianxibase1.bwie.com.base.base.mvp.IBaseView;

/**
 * Created by lenovo on 2018/9/12.
 */
//契约类
public interface Contract {
    //Presenter
    abstract class Presenter extends BasePresenter<Model,View> {
        public abstract void slect();

        @Override
        public Model getmModel() {
            return  new com.example.lenovo.lwxjingdong.model.Model();
        }
    }
    //model层接口
    interface Model extends IBaseModel {
        Observable<FristBean> slect();
    }
    //view层接口
    interface View extends IBaseView {
        void success(FristBean oneBean);
        void fail(String msg);
    }
}
