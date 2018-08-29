package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Plan;

import java.util.List;

public class PlanAdapter extends BaseQuickAdapter<Plan, BaseViewHolder> {

    private Context context;

    public PlanAdapter(Context context, List<Plan> list) {
        super(R.layout.item_plan, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Plan item) {
        helper.setText(R.id.textview, item.getDescription());
        ImageView imageview = helper.getView(R.id.imageview);
        Glide.with(context).load(item.getImage_url()).into(imageview);
    }
}
