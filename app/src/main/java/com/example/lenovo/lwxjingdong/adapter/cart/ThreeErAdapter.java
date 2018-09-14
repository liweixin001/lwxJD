package com.example.lenovo.lwxjingdong.adapter.cart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.bean.ThreeBean;
import com.example.lenovo.lwxjingdong.fragment.FragmentCart;
import com.example.lenovo.lwxjingdong.widget.MyJIaJianView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by lenovo on 2018/9/13.
 */

public class  ThreeErAdapter extends RecyclerView.Adapter<ThreeErAdapter.ThreeErViewHolder> implements View.OnClickListener {

    private OnItemClickListener mItemClickListener;

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private CartAllCheckboxlistener allCheckboxlistener;
    private Context context;
    private List<ThreeBean.DataBean.ListBean> listBeans;
    private CheckListener checkListener;

    public void setCheckListener(CheckListener checkListener) {
        this.checkListener = checkListener;
    }

    public ThreeErAdapter(Context context,List<ThreeBean.DataBean.ListBean> listBeans){
        this.context = context;
        this.listBeans = listBeans;
    }
    public void setAllCheckboxlistener(CartAllCheckboxlistener allCheckboxlistener) {
        this.allCheckboxlistener = allCheckboxlistener;
    }

    @Override
    public ThreeErViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.three_er, parent, false);
        ThreeErViewHolder holder = new ThreeErViewHolder( view );
        view.setOnClickListener( this );
        return holder;
    }

    @Override
    public void onBindViewHolder(final ThreeErViewHolder holder, int position) {
        final ThreeBean.DataBean.ListBean bean = listBeans.get(position);
        holder.productCheckbox.setChecked(bean.isSelected());
        holder.title.setText(bean.getTitle());
        holder.price.setText("优惠价:¥"+listBeans.get(position).getBargainPrice());
        String[] imgs = bean.getImages().split("\\|");
        Glide.with(context).load(imgs[0]).into(holder.product_icon);
        holder.jiajianqi.setNumEt(bean.getTotalNum());
        holder.jiajianqi.setJiaJianListener(new MyJIaJianView.JiaJianListener() {
            @Override
            public void getNum(int num) {
                bean.setTotalNum(num);
//                EventBus.getDefault().post("");
                if (checkListener!=null){
                    checkListener.notifyParent();
                }
            }
        });
        holder.productCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.productCheckbox.isChecked()){
                    bean.setSelected(true);
                }else{
                    bean.setSelected(false);
                }
                if(checkListener!=null){
                    checkListener.notifyParent();
                }
            }
        });

        holder.itemView.setTag( position );
    }

    @Override
    public int getItemCount() {
        return listBeans.size()==0?0:listBeans.size();
    }

    @Override
    public void onClick(View view) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) view.getTag());
        }
    }

    public class ThreeErViewHolder extends RecyclerView.ViewHolder{

        private CheckBox productCheckbox;
        private ImageView product_icon;
        private TextView title;
        private TextView price;
        private MyJIaJianView jiajianqi;

        public ThreeErViewHolder(View itemView) {
            super(itemView);
            productCheckbox = itemView.findViewById(R.id.productCheckbox);
            product_icon = itemView.findViewById(R.id.product_icon);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            jiajianqi = itemView.findViewById(R.id.jiajianqi);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
