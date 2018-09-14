package com.example.lenovo.lwxjingdong.adapter.fristpage;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.bean.FristBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by lenovo on 2018/9/12.
 */

public class OneMiaoShaAdapter  extends RecyclerView.Adapter<OneMiaoShaAdapter.MiaoViewHolder>{
    private Context context;
    private FristBean.DataBean data;
    private final List<FristBean.DataBean.MiaoshaBean.ListBean> list;

    public OneMiaoShaAdapter(Context context, FristBean.DataBean data) {
        this.context = context;
        this.data = data;
        list = data.getMiaosha().getList();
    }

    @Override
    public MiaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.one_miaosha, parent, false);
        return new MiaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MiaoViewHolder holder, int position) {
        holder.miao_text.setText(list.get(position).getTitle());
        Uri uri = Uri.parse(list.get(position).getImages().split("\\|")[0]);
        holder.miao_img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MiaoViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView miao_img;
        private TextView miao_text;

        public MiaoViewHolder(View itemView) {
            super(itemView);
            miao_img = itemView.findViewById(R.id.miao_img);
            miao_text = itemView.findViewById(R.id.miao_text);
        }
    }
}
