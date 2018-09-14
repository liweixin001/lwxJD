package com.example.lenovo.lwxjingdong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.bean.LoginBean;
import com.example.lenovo.lwxjingdong.contants.LoginContract;
import com.example.lenovo.lwxjingdong.presenter.LoginPresenter;
import com.example.lenovo.lwxjingdong.utils.EncryptUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import lianxibase1.bwie.com.base.base.mvp.BaseMvpActivity;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
//登陆页面
public class LoginActivity extends BaseMvpActivity<LoginContract.loginModel,LoginContract.LoginPresenter> implements LoginContract.LoginView{
    @BindView( R.id.login_btn )
    Button login_btn;
    @BindView(R.id.login_name)
    EditText login_name;
    @BindView(R.id.login_pwd)
    EditText login_pwd;
    @BindView(R.id.goto_zhuce)
    TextView goto_zhuce;

    @Override
    protected void initView() {
    }
    @OnClick(R.id.goto_zhuce)
    public void zhuce(){
        Intent intent=new Intent( LoginActivity.this,ZuceActivity.class );
        startActivity( intent );
        finish();
    }

    @OnClick(R.id.login_btn)
    public void login(){
        String name=login_name.getText().toString();
        String pwd=login_pwd.getText().toString();
        String s2= EncryptUtil.encrypt( pwd );
        HashMap<String,String>map=new HashMap<>(  );
        map.put( "phone",name );
        map.put( "pwd",s2 );
        if (TextUtils.isEmpty( name )&&TextUtils.isEmpty( pwd )){
            Toast.makeText(LoginActivity.this,"密码或者账号不能为空",Toast.LENGTH_SHORT).show();
        }else {
            presenter.login( map );
        }
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public BasePresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void fail(String msg) {

    }

    @Override
    public void success(LoginBean loginBean) {
        EventBus.getDefault().postSticky( loginBean );
        finish();
    }

    @Override
    public void failure(String error) {
        Log.i("aaa",error+"");

    }
}
