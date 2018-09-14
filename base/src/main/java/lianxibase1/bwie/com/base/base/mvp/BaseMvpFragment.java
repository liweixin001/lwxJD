package lianxibase1.bwie.com.base.base.mvp;


import lianxibase1.bwie.com.base.base.BaseFragment;

/**
 * Created by 春风再来 on 2018/9/5.
 */

public abstract  class BaseMvpFragment <M extends IBaseModel,P extends BasePresenter> extends BaseFragment implements IBaseView {
    public M model;
    public P presenter;

    @Override
    protected abstract int bindLayoutId();

    @Override
    protected void initData() {
        presenter = (P) initPresenter();
        if (presenter!=null){
            model = (M) presenter.getmModel();
            if (model!=null){
                presenter.attach(model,this);
            }
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter!=null){
            presenter.detach();//解除绑定，回收资源，释放内存，提高性能
        }
    }
}
