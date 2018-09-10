package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.HomeTripDetail;

import java.util.List;

public class HomeTripDetailAdapter extends BaseQuickAdapter<HomeTripDetail.TripDay, BaseViewHolder> {

    public HomeTripDetailAdapter(List<HomeTripDetail.TripDay> list) {
        super(R.layout.item_hometrip_detail, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTripDetail.TripDay item) {
        List<HomeTripDetail.TripDay.TripNode> list=item.getNodes();
        if(list.size()>0){
            List<HomeTripDetail.TripDay.TripNode.TripNote> notes=list.get(0).getNotes();
            if(notes.size()>0){
                helper.setText(R.id.day, "第" + item.getDay() + "天")
                        .setText(R.id.content,notes.get(0).getDescription());
            }else{
                helper.setText(R.id.day, "第" + item.getDay() + "天")
                        .setText(R.id.content,"");
            }
        }
    }
}
