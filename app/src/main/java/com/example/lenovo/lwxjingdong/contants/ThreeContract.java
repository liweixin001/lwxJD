package com.example.lenovo.lwxjingdong.contants;

import com.example.lenovo.lwxjingdong.bean.ThreeBean;
import com.example.lenovo.lwxjingdong.model.ThreeModel;

import java.util.HashMap;

import io.reactivex.Observable;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
import lianxibase1.bwie.com.base.base.mvp.IBaseModel;
import lianxibase1.bwie.com.base.base.mvp.IBaseView;

/**
 * Created by lenovo on 2018/9/13.
 */

public interface ThreeContract {
    abstract class  ThreePresenter extends BasePresenter<ThreeModel,ThreeView>{
        public abstract void shopping(HashMap<String,String>parms);

        @Override
        public ThreeContract.ThreeModel getmModel() {
            return new com.example.lenovo.lwxjingdong.model.ThreeModel();
        }
    }
    interface ThreeModel extends IBaseModel{
        Observable<ThreeBean>shopping(HashMap<String,String>parms);

    }
    interface ThreeView extends IBaseView{
        void success(ThreeBean threeBean);
        void fail(String msg);
    }
}
