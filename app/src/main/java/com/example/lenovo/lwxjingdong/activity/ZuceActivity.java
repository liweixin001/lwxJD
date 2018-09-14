package com.example.lenovo.lwxjingdong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.lwxjingdong.MainActivity;
import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.bean.RegisterBean;
import com.example.lenovo.lwxjingdong.contants.RegisterConteact;
import com.example.lenovo.lwxjingdong.presenter.RegisterPresenter;
import com.example.lenovo.lwxjingdong.utils.EncryptUtil;

import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import lianxibase1.bwie.com.base.base.mvp.BaseMvpActivity;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
//注册页面
public class ZuceActivity extends BaseMvpActivity<RegisterConteact.registeriModel,RegisterConteact.RegisterPresenter>implements RegisterConteact.RegisterView {

    @BindView( R.id.register_name )
    EditText register_name;
    @BindView(R.id.register_pwd)
    EditText register_pwd;
    @BindView(R.id.register_btn)
    Button register_btn;
    @BindView(R.id.CuoZuce)
    ImageView CuoZuce;
    private RegisterPresenter registerPresenter;


    @Override
    protected void initView() {

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.five_zuce;
    }

    @Override
    public BasePresenter initPresenter() {
        return new RegisterPresenter();
    }
    @OnClick(R.id.CuoZuce)
    public void CuoZuce(){
        finish();
    }
    @OnClick(R.id.register_btn)
    public void zhuce(){
        int min=10;
        int max=99;
        Random random = new Random();
        int num=random.nextInt(max)%(max-min+1)+min;
        String name=register_name.getText().toString();
        String pwd=register_pwd.getText().toString();
        String s2= EncryptUtil.encrypt( pwd );
        HashMap<String,String>map=new HashMap<>(  );
        map.put( "phone",name );
        map.put( "pwd",s2 );
        map.put( "pwd2",s2 );
        map.put( "sex","1" );
        map.put( "email","473100795@qq.com" );
        map.put( "nickName",""+num );

//        registerPresenter.login(register_name.getText().toString(),register_pwd.getText().toString());

        if (TextUtils.isEmpty( name )&&TextUtils.isEmpty( pwd )){
            Toast.makeText(ZuceActivity.this,"密码或者账号不能为空",Toast.LENGTH_SHORT).show();
        }else {
            presenter.register( map );
        }
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
    public void success(RegisterBean registerBean) {
        String status=registerBean.getStatus();
        String i="0000";
        startActivity(new Intent(ZuceActivity.this, MainActivity.class));

        if (status.equals( i )){
            Toast.makeText(ZuceActivity.this,"成功",Toast.LENGTH_SHORT).show();;
            finish();
        }else{
            Toast.makeText(ZuceActivity.this,"失败请检查",Toast.LENGTH_SHORT).show();;

        }
    }

    @Override
    public void failure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }




    @Override
    public void mobileVerify() {
        Toast.makeText(this, "手机号需11位合法数字", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mobileEmpty() {
        Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdVerify() {
        Toast.makeText(this,"密码不合法" , Toast.LENGTH_SHORT).show();
    }
}
