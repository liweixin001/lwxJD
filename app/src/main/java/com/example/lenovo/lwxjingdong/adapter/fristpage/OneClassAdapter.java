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

public class OneClassAdapter extends RecyclerView.Adapter<OneClassAdapter.ClassViewHolder>{
    private Context context;
    private FristBean.DataBean data;
    private final List<FristBean.DataBean.FenleiBean> class_feilei;

    public OneClassAdapter(Context context, FristBean.DataBean data) {
        this.context = context;
        this.data = data;
        class_feilei = data.getFenlei();
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.one_class_item, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        holder.fei_text.setText(class_feilei.get(position).getName());
        Uri uri = Uri.parse(class_feilei.get(position).getIcon());
        holder.fei_img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return class_feilei.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView fei_img;
        private TextView fei_text;

        public ClassViewHolder(View itemView) {
            super(itemView);
            fei_img = itemView.findViewById(R.id.fei_img);
            fei_text = itemView.findViewById(R.id.fei_text);
        }
    }
}
