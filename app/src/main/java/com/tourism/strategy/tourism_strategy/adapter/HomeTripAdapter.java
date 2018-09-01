package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.HomeTrip;

import java.util.List;

public class HomeTripAdapter extends BaseQuickAdapter<HomeTrip, BaseViewHolder> {

    private Context context;

    public HomeTripAdapter(Context context, List<HomeTrip> list) {
        super(R.layout.item_home_trip, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTrip item) {
        ImageView imageview = helper.getView(R.id.imageview);
        Glide.with(context).load(item.getFront_cover_photo_url()).into(imageview);
        helper.setText(R.id.textview, item.getName());
    }
}
