package com.hzy.exampledemo.adapter;

import android.content.Context;

import com.hzy.exampledemo.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by hzy on 2019/3/14
 *
 * @author Administrator
 * */
public class MainListAdapter extends CommonAdapter<String> {

    public MainListAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_main_list, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String str, int position) {
        holder.setText(R.id.tv_str, str);
    }
}
