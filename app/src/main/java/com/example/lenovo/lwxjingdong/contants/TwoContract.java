package com.example.lenovo.lwxjingdong.contants;

import com.example.lenovo.lwxjingdong.bean.TwoBean;
import com.example.lenovo.lwxjingdong.model.TwoModel;

import io.reactivex.Observable;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
import lianxibase1.bwie.com.base.base.mvp.IBaseModel;
import lianxibase1.bwie.com.base.base.mvp.IBaseView;

/**
 * Created by lenovo on 2018/9/12.f
 */

public interface TwoContract {
abstract class  TwoPresenter extends BasePresenter<TwoModel,TwoView> {
    public abstract void fenlei();

    @Override
    public TwoContract.TwoModel getmModel() {
        return new com.example.lenovo.lwxjingdong.model.TwoModel();
    }

}
interface TwoModel extends IBaseModel{
    Observable<TwoBean> fenlei();
}
interface TwoView extends IBaseView{
    void success(TwoBean twoBean);
    void fail(String msg);
}
}

