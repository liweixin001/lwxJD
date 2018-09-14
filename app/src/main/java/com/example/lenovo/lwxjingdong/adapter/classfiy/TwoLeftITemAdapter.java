package com.example.lenovo.lwxjingdong.adapter.classfiy;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.lwxjingdong.R;
import com.example.lenovo.lwxjingdong.bean.TwoItemBean;

import java.util.List;

/**
 * Created by lenovo on 2018/9/12.
 */

public class TwoLeftITemAdapter extends RecyclerView.Adapter<TwoLeftITemAdapter.TwoLeftITemViewHolder> {

    private Context context;
    private List<TwoItemBean.DataBean> data;

    public TwoLeftITemAdapter(Context context, List<TwoItemBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public TwoLeftITemAdapter.TwoLeftITemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.two_left_item, parent, false);
        return new TwoLeftITemAdapter.TwoLeftITemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TwoLeftITemAdapter.TwoLeftITemViewHolder holder, int position) {
        holder.fl_item_text.setText(data.get(position).getName());
        TwoRightAdapter twoLeftAdapter = new TwoRightAdapter(context,data.get(position).getList());
        holder.fl_item_rev.setAdapter(twoLeftAdapter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TwoLeftITemViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView fl_item_rev;
        private TextView fl_item_text;

        public TwoLeftITemViewHolder(View itemView) {
            super(itemView);
            fl_item_rev = itemView.findViewById(R.id.fl_item_rev);
            fl_item_text = itemView.findViewById( R.id.fl_item_text);
            fl_item_rev.setLayoutManager(new GridLayoutManager(context,3));
        }
    }
}
