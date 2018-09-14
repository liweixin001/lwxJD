package com.example.lenovo.lwxjingdong.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.adapter.classfiy.TwoLeftAdapter;
import com.example.lenovo.lwxjingdong.adapter.classfiy.TwoLeftITemAdapter;
import com.example.lenovo.lwxjingdong.bean.TwoBean;
import com.example.lenovo.lwxjingdong.bean.TwoItemBean;
import com.example.lenovo.lwxjingdong.common.Contants;
import com.example.lenovo.lwxjingdong.contants.TwoContract;
import com.example.lenovo.lwxjingdong.presenter.TwoPresenter;
import com.example.lenovo.lwxjingdong.utils.OkHttpUtils;
import com.example.lenovo.lwxjingdong.utils.RequestCollBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import lianxibase1.bwie.com.base.base.mvp.BaseMvpFragment;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/9/12.
 */
//分类展示
public class FragmentClassify extends BaseMvpFragment<TwoContract.TwoModel,TwoContract.TwoPresenter> implements TwoContract.TwoView{
   @BindView( R.id.zhu_rev )
    RecyclerView zhu_rev;
    @BindView(R.id.zi_rev)
    RecyclerView zi_rev;
    @BindView(R.id.zi_text)
    TextView zi_text;
    private int id = 1;
    private Handler handler = new Handler();

    @Override
    protected int bindLayoutId() {
        return R.layout.two;
    }

    @Override
    protected void initData() {
        super.initData();
        zhu_rev.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        zi_rev.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        presenter.fenlei();
        initItem();
    }

    private void initItem() {
        HashMap<String,String>prams=new HashMap<>(  );
        prams.put( "cid",id+"");
        OkHttpUtils.getInstance().postData( Contants.BASE_RELEASE_URL + "product/getProductCatagory", prams, new RequestCollBack() {
            @Override
            public void onFailure(Call call, IOException e) {
                
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (200==response.code()){
                    String string=response.body().string();
                    preaJson(string);
                }
            }
        } );
    }

    private void preaJson(String string) {
        Gson gson = new Gson();
        TwoItemBean twoItemBean=gson.fromJson( string,TwoItemBean.class );
        final List<TwoItemBean.DataBean>data=twoItemBean.getData();
        handler.post( new Runnable() {
            @Override
            public void run() {
                TwoLeftITemAdapter twoLeftITemAdapter=new TwoLeftITemAdapter(getActivity(),data);
                zi_rev.setAdapter( twoLeftITemAdapter );
            }
        } );
    }

    @Override
    public BasePresenter initPresenter() {
        return new TwoPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void success(final TwoBean twoBean) {
        TwoLeftAdapter twoLeftAdapter = new TwoLeftAdapter(getActivity(), twoBean.getData());
        zhu_rev.setAdapter(twoLeftAdapter);

        twoLeftAdapter.setOnItemClickListener(new TwoLeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                id=twoBean.getData().get(position).getCid();
                zi_text.setText(twoBean.getData().get(position).getName());
                initItem();
            }
        });
    }

    @Override
    public void fail(String msg) {

    }
}
