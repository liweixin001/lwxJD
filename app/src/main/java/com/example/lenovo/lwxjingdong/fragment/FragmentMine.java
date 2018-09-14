package com.example.lenovo.lwxjingdong.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.activity.LoginActivity;
import com.example.lenovo.lwxjingdong.activity.UpdateActivity;
import com.example.lenovo.lwxjingdong.activity.ZuceActivity;
import com.example.lenovo.lwxjingdong.bean.LoginBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lianxibase1.bwie.com.base.base.BaseFragment;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lenovo on 2018/9/12.
 */
//我的
public class FragmentMine extends BaseFragment{
    private Touxiang touxiang;

    private View view;

    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/myHead/";// sd路径
    //图片
    @BindView( R.id.four_sdvvvv)
    ImageView four_sdvvvv;
    @BindView( R.id.five_login )
    TextView five_login;
    @BindView( R.id.five_zuce )
    TextView five_zuce;
    @BindView( R.id.text_iv )
    TextView text_iv;

    private List<LoginBean.ResultBean.UserInfoBean> list=new ArrayList<>(  );
    private String status;
    private SharedPreferences sp;

    @Override
    protected int bindLayoutId() {
        return R.layout.five;
    }

    @Override
    protected void initData() {
        initViews();

        sp=getActivity().getSharedPreferences( "User", Context.MODE_PRIVATE );
//        EventBus.getDefault().register( this );
        Bitmap bt= BitmapFactory.decodeFile( path+"head.jpg" );// 从SD卡中找头像，转换成Bitmap
        if (bt!=null){
            @SuppressWarnings( "deprecation" )
            Drawable drawable=new BitmapDrawable( bt );//转换成drawable
            four_sdvvvv.setImageDrawable( drawable );
        }

    }

    private void initViews() {
        four_sdvvvv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {//更改头像
                showTypeDialog();
            }
        } );
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity ());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity (), R.layout.add_image, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener () {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener () {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File( Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        four_sdvvvv.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    @OnClick(R.id.five_login)
    public void five_login(){
        startActivity( new Intent( getActivity(),LoginActivity.class ) );
    }
    @OnClick(R.id.five_zuce)
    public void five_zuce(){
        startActivity( new Intent( getActivity(),ZuceActivity.class ) );
    }
    @Subscribe(sticky=true)
    public void getEventBus(LoginBean loginBean){
        status=loginBean.getStatus();
        five_login.setText( loginBean.getResult().getUserInfo().getNickName());
        five_login.setTextColor( Color.BLACK );
        five_zuce.setText( "" );
        text_iv.setText( "" );
        String headPic=loginBean.getResult().getUserInfo().getHeadPic();
        four_sdvvvv.setImageURI( Uri.parse( headPic ) );
        SharedPreferences.Editor edit=sp.edit();
        edit.putString( "Name",loginBean.getResult().getUserInfo().getNickName() );
        edit.putString( "Icon",loginBean.getResult().getUserInfo().getHeadPic() );
        edit.commit();
        //修改页面
        five_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( getActivity(),UpdateActivity.class ) );
            }
        } );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void setPicToView(Bitmap mbitmap) {
        String sdStatus = Environment.getExternalStorageState ();
        if (!sdStatus.equals (Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File (path);
        file.mkdirs ();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream (fileName);
            mbitmap.compress (Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } finally {
            try {
                // 关闭流
                b.flush ();
                b.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
    }
}
