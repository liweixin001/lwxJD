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

public class OneTuiJianAdapter  extends RecyclerView.Adapter<OneTuiJianAdapter.TuiViewHolder>{
    private Context context;
    private FristBean.DataBean data;
    private final List<FristBean.DataBean.TuijianBean.ListBeanX> list;

    public OneTuiJianAdapter(Context context, FristBean.DataBean data) {
        this.context = context;
        this.data = data;
        list = data.getTuijian().getList();
    }

    @Override
    public TuiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.one_tuijian, parent, false);
        return new TuiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TuiViewHolder holder, int position) {
        holder.tui_text.setText(list.get(position).getTitle());
        Uri uri = Uri.parse(list.get(position).getImages().split("\\|")[0]);
        holder.tui_img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TuiViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView tui_img;
        private TextView tui_text;

        public TuiViewHolder(View itemView) {
            super(itemView);
            tui_img = itemView.findViewById(R.id.tui_img);
            tui_text = itemView.findViewById(R.id.tui_text);
        }
    }
}
