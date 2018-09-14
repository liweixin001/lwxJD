package com.example.lenovo.lwxjingdong.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.activity.DetailsActivity;
import com.example.lenovo.lwxjingdong.adapter.cart.CartAllCheckboxlistener;
import com.example.lenovo.lwxjingdong.adapter.cart.ThreeErAdapter;
import com.example.lenovo.lwxjingdong.adapter.cart.ThreeYiAdapter;
import com.example.lenovo.lwxjingdong.bean.ThreeBean;
import com.example.lenovo.lwxjingdong.contants.ThreeContract;
import com.example.lenovo.lwxjingdong.presenter.ThreePresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import lianxibase1.bwie.com.base.base.mvp.BaseMvpFragment;
import lianxibase1.bwie.com.base.base.mvp.BasePresenter;

/**
 * Created by lenovo on 2018/9/12.
 */

public class FragmentCart extends BaseMvpFragment<ThreeContract.ThreeModel,ThreeContract.ThreePresenter> implements ThreeContract.ThreeView,CartAllCheckboxlistener, ThreeErAdapter.OnItemClickListener {


    @BindView(R.id.cartRV)
    RecyclerView xRecyclerView;
    @BindView( R.id.totalPrice )
    TextView totalpriceTv;
    @BindView(R.id.allCheckbox)
    CheckBox allcheckBox;

    private List<ThreeBean.DataBean> list;
    private ThreeYiAdapter threeYiAdapter;
    private ThreeErAdapter threeErAdapter;

    private List<ThreeBean.DataBean.ListBean> listBeans;

    @Override
    protected int bindLayoutId() {
//        EventBus.getDefault().register(this);
        return R.layout.three;
    }

    @Override
    protected void initData() {
        super.initData();
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HashMap<String,String> prams = new HashMap<>();
        prams.put("uid","71");

        presenter.shopping(prams);
       initView();
    }

    private void initView() {

        allcheckBox.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allcheckBox.isChecked()){
                    if (list!=null&&list.size()>0){
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(true);
                            for (int i1 = 0; i1 < list.get(i).getList().size(); i1++) {
                                list.get(i).getList().get(i1).setSelected(true);
                            }
                        }
                    }
                }else {
                    if (list!=null&&list.size()>0){
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(false);
                            for (int i1 = 0; i1 < list.get(i).getList().size(); i1++) {
                                list.get(i).getList().get(i1).setSelected(false);
                            }
                        }
                    }

                }
                threeYiAdapter.notifyDataSetChanged();
                totalPrice();
            }
        } );
    }

    private void totalPrice() {
        double totalPrice=0;
        for (int i = 0; i < threeYiAdapter.getCartList().size(); i++) {
            for (int i1 = 0; i1 < threeYiAdapter.getCartList().get(i).getList().size(); i1++) {

                if (threeYiAdapter.getCartList().get(i).getList().get(i1).isSelected()){
                    ThreeBean.DataBean.ListBean listBean = threeYiAdapter.getCartList().get(i).getList().get(i1);
                    totalPrice+=listBean.getBargainPrice()*listBean.getTotalNum();
                }
            }
        }
        totalpriceTv.setText("总价"+totalPrice);
    }

    @Override
    public BasePresenter initPresenter() {
        return new ThreePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void success(ThreeBean threeBean) {
// Log.i("threeBean",threeBean.toString());
        list = threeBean.getData();
        threeYiAdapter = new ThreeYiAdapter(getActivity(),list);
        xRecyclerView.setAdapter(threeYiAdapter);
        threeYiAdapter.setAllCheckboxlistener( this );
        threeErAdapter = new ThreeErAdapter(getActivity(),listBeans);
        threeErAdapter.setmItemClickListener(this);

    }



    @Override
    public void fail(String msg) {

    }

    @Override
    public void notifyAllCheckboxStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        if (threeYiAdapter!=null){
            for (int i = 0; i < threeYiAdapter.getCartList().size(); i++) {
                stringBuilder.append(threeYiAdapter.getCartList().get(i).isSelected());
                for (int i1 = 0; i1 < threeYiAdapter.getCartList().get(i).getList().size(); i1++) {
                    stringBuilder.append(threeYiAdapter.getCartList().get(i).getList().get(i1).isSelected());
                }
            }
        }
        if (stringBuilder.toString().contains("false")){
            allcheckBox.setChecked(false);

        }else {
            allcheckBox.setChecked(true);
        }
        totalPrice();
    }


    @Override
    public void onItemClick(int position) {
        startActivity( new Intent( getActivity(), DetailsActivity.class) );
    }
}
