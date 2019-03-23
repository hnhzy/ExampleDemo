package com.hzy.exampledemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.hzy.exampledemo.R;
import com.hzy.exampledemo.ui.DatailActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by hzy on 2019/3/14
 *
 * @author Administrator
 */
public class SceneListAdapter extends CommonAdapter<Integer> {

    public SceneListAdapter(Context context, List<Integer> datas) {
        super(context, R.layout.item_scene_list, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final Integer id, int position) {
        final ImageView imageView = holder.getView(R.id.imv);
        holder.setImageResource(R.id.imv, id)
                .setOnClickListener(R.id.imv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatailActivity.start((Activity) mContext, id, imageView);
                    }
                });
    }
}
