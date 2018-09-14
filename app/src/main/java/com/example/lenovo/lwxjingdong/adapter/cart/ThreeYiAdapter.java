package com.example.lenovo.lwxjingdong.adapter.cart;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.bean.ThreeBean;
import com.example.lenovo.lwxjingdong.fragment.FragmentCart;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by lenovo on 2018/9/13.
 */

public class ThreeYiAdapter extends RecyclerView.Adapter<ThreeYiAdapter.ThreeYiViewHolder>implements CheckListener {

    private CartAllCheckboxlistener allCheckboxlistener;
    private Context context;
    private List<ThreeBean.DataBean> cartList;
    private ThreeErAdapter threeErAdapter;

    public ThreeYiAdapter(Context context,List<ThreeBean.DataBean> cartList){
        this.context = context;
        this.cartList = cartList;
    }
    public void addPageData(List<ThreeBean.DataBean> list){
        if (cartList!=null){
            cartList.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void setAllCheckboxlistener(CartAllCheckboxlistener allCheckboxlistener) {
        this.allCheckboxlistener = allCheckboxlistener;
    }


    @Override
    public ThreeYiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.three_yi,parent,false);
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }

        return new ThreeYiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ThreeYiViewHolder holder, int position) {
        final ThreeBean.DataBean bean = cartList.get(position);
        holder.sellerCheckbox.setChecked(bean.isSelected());
        holder.sellerNameTv.setText(bean.getSellerName());
        holder.productXRV.setLayoutManager(new LinearLayoutManager(context));
        threeErAdapter = new ThreeErAdapter(context,bean.getList());
        holder.productXRV.setAdapter(threeErAdapter);
        threeErAdapter.setCheckListener(this);

        for (int i = 0; i < bean.getList().size(); i++) {
            if (!bean.getList().get(i).isSelected()){
                holder.sellerCheckbox.setChecked(false);
                break;
            }else {
                holder.sellerCheckbox.setChecked(true);
            }
        }
        holder.sellerCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.sellerCheckbox.isChecked()){
                    bean.setSelected(true);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setSelected(true);
                    }
                }else {
                    bean.setSelected(false);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setSelected(false);
                    }
                }
                notifyDataSetChanged();
                if (allCheckboxlistener!=null){
                    allCheckboxlistener.notifyAllCheckboxStatus();
                }
            }
        });
    }

    public List<ThreeBean.DataBean> getCartList() {
        return cartList;
    }

    @Override
    public int getItemCount() {
        return cartList.size()==0?0:cartList.size();
    }

    @Override
    public void notifyParent() {
        notifyDataSetChanged();
        if (allCheckboxlistener!=null);
        allCheckboxlistener.notifyAllCheckboxStatus();
    }

    public class ThreeYiViewHolder extends RecyclerView.ViewHolder{
        private CheckBox sellerCheckbox;
        private TextView sellerNameTv;

        private RecyclerView productXRV;
        public ThreeYiViewHolder(View itemView) {
            super(itemView);
            sellerCheckbox = itemView.findViewById(R.id.sellerCheckbox);
            sellerNameTv = itemView.findViewById(R.id.sellerNameTv);
            productXRV = itemView.findViewById(R.id.productXRV);

        }

    }

}
