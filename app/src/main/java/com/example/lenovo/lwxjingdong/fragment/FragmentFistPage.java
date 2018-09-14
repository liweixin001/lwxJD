package com.example.lenovo.lwxjingdong.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.lwxjingdong.activity.SelectActivity;
import com.example.lenovo.lwxjingdong.adapter.fristpage.OneAdapter;
import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.bean.FristBean;
import com.example.lenovo.lwxjingdong.contants.Contract;
import com.example.lenovo.lwxjingdong.presenter.Presenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import butterknife.BindView;
import butterknife.OnClick;
import lianxibase1.bwie.com.base.base.mvp.BaseMvpFragment;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;

/**
 * Created by lenovo on 2018/9/12.
 */
//首页展示
public class FragmentFistPage extends BaseMvpFragment<Contract.Model,Contract.Presenter> implements Contract.View{
    @BindView(R.id.shouye_rev)
    XRecyclerView shouye_rev;
    @BindView( R.id.sm_btn )
    Button sm_btn;
    @BindView( R.id.sel_et )
    EditText sel_et;

    private OneAdapter oneAdapter;

    @Override
    protected int bindLayoutId() {
        return R.layout.one;
    }

    @Override
    protected void initData() {
        super.initData();
        shouye_rev.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.slect();
    }

    @Override
    public BasePresenter initPresenter() {
        return new Presenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void success(FristBean oneBean) {
        oneAdapter = new OneAdapter(getActivity(), oneBean.getData());
        shouye_rev.setAdapter( oneAdapter );
    }

    @Override
    public void fail(String msg) {

    }

    @OnClick(R.id.sm_btn)
    public void sm_btn(){
        sm_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CaptureActivity.class));
                
            }
        } );
    }
    @OnClick(R.id.sel_et)
    public void sel_et(){
        sel_et.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SelectActivity.class));

            }
        } );
    }
}
