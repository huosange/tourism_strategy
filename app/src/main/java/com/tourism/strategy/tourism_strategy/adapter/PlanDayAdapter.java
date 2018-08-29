package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.PlanDetail;

import java.util.List;

public class PlanDayAdapter extends BaseQuickAdapter<PlanDetail.PlanDay, BaseViewHolder> {

    private Context context;

    public PlanDayAdapter(Context context, List<PlanDetail.PlanDay> list) {
        super(R.layout.item_plan_day, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PlanDetail.PlanDay item) {
        helper.setText(R.id.memo, item.getMemo());
        RecyclerView recyclerview = helper.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        PlanNodeAdapter adapter = new PlanNodeAdapter(context, item.getPlan_nodes());
        recyclerview.setAdapter(adapter);
    }
}
