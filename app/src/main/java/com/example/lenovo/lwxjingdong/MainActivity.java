package com.example.lenovo.lwxjingdong;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.lenovo.lwxjingdong.fragment.FragmentCart;
import com.example.lenovo.lwxjingdong.fragment.FragmentClassify;
import com.example.lenovo.lwxjingdong.fragment.FragmentFind;
import com.example.lenovo.lwxjingdong.fragment.FragmentFistPage;
import com.example.lenovo.lwxjingdong.fragment.FragmentMine;
import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar tab_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );

            ActionBar actionBar=getSupportActionBar();
            actionBar.hide();
        }
        tab_var = findViewById( R.id.tab_var );
        initView();
    }

    private void initView() {
        ImmersionBar.with(this).init();
        tab_var.init(getSupportFragmentManager())//初始化方法布局管理
                .setFontSize(0)//设置文字大小
                //参数1：选中后的颜色，参数2：选中前的颜色
                //参数1：文字内容。参数2：导航图片。参数3：切换哪个fragment类
                .setImgSize(150,150)
                .setTabPadding(5,1,1)
                .addTabItem("首页", R.drawable.ac1, R.drawable.ac0, FragmentFistPage.class)
                .addTabItem("分类", R.drawable.abx, R.drawable.abw, FragmentClassify.class)
                .addTabItem("发现", R.drawable.abz, R.drawable.aby, FragmentFind.class)
                .addTabItem("购物车", R.drawable.abv, R.drawable.abu, FragmentCart.class)
                .addTabItem("我的", R.drawable.ac3, R.drawable.ac2, FragmentMine.class)
                .isShowDivider(false);
    }
}
