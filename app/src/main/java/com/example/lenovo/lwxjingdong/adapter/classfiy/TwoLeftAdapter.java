package com.example.lenovo.lwxjingdong.adapter.classfiy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.bean.TwoBean;

import java.util.List;

/**
 * Created by lenovo on 2018/9/12.
 */

public class TwoLeftAdapter extends RecyclerView.Adapter<TwoLeftAdapter.TwoLeftViewHolder> {

    private Context context;
    private List<TwoBean.DataBean> data;
    private TwoLeftAdapter.OnItemClickListener onItemClickListener;

    public TwoLeftAdapter(Context context, List<TwoBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public TwoLeftAdapter.TwoLeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.two_left, parent, false);
        return new TwoLeftAdapter.TwoLeftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TwoLeftAdapter.TwoLeftViewHolder holder, final int position) {
        holder.zhu_name.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TwoLeftViewHolder extends RecyclerView.ViewHolder {

        private TextView zhu_name;

        public TwoLeftViewHolder(View itemView) {
            super(itemView);
            zhu_name = itemView.findViewById(R.id.zhu_name);
        }
    }
    public void setOnItemClickListener(TwoLeftAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
