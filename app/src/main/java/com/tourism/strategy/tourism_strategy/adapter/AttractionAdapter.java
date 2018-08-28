package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Attraction;

import java.util.List;

public class AttractionAdapter extends BaseQuickAdapter<Attraction, BaseViewHolder> {

    private Context context;

    public AttractionAdapter(Context context, List<Attraction> list) {
        super(R.layout.item_attraction, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Attraction item) {
        helper.setText(R.id.name, item.getName())
                .setText(R.id.score, item.getUser_score())
                .setText(R.id.description, item.getDescription())
                .setText(R.id.tripsCount, item.getAttraction_trips_count() + "篇游记");
        ImageView imageview = helper.getView(R.id.imageview);
        Glide.with(context).load(item.getImage_url()).into(imageview);
    }
}
