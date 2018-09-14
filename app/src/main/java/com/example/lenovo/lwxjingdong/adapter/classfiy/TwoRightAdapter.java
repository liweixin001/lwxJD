package com.example.lenovo.lwxjingdong.adapter.classfiy;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.bean.TwoItemBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by lenovo on 2018/9/12.
 */

public class TwoRightAdapter extends RecyclerView.Adapter<TwoRightAdapter.TwoRightViewHolder>{

    private Context context;
    private List<TwoItemBean.DataBean.ListBean> list;

    public TwoRightAdapter(Context context, List<TwoItemBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TwoRightAdapter.TwoRightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.two_right, parent, false);
        return new TwoRightAdapter.TwoRightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TwoRightAdapter.TwoRightViewHolder holder, final int position) {
        holder.product_text.setText(list.get(position).getName());
        Uri uri = Uri.parse(list.get(position).getIcon());
        holder.product_img.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TwoRightViewHolder extends RecyclerView.ViewHolder {

        private TextView product_text;
        private SimpleDraweeView product_img;

        public TwoRightViewHolder(View itemView) {
            super(itemView);
            product_text = itemView.findViewById(R.id.product_text);
            product_img = itemView.findViewById( R.id.product_img);
        }
    }
}

