package lianxibase1.bwie.com.base.base.mvp;


public interface IBaseView {

    BasePresenter initPresenter();

    void showLoading();
    void hideLoading();

    void fail(String msg);//请求失败


}
