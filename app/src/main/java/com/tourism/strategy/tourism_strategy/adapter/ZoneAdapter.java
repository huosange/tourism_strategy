package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.TravelListActivity;
import com.tourism.strategy.tourism_strategy.model.Zone;

import java.util.List;

public class ZoneAdapter extends BaseQuickAdapter<Zone, BaseViewHolder>{

    private Context context;

    public ZoneAdapter(Context context, List<Zone> list) {
        super(R.layout.item_zone, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final Zone item) {
        helper.setText(R.id.name, item.getName_zh_cn() + item.getName_en());
        ImageView imageview = helper.getView(R.id.imageview);
        Glide.with(context).load(item.getImage_url()).into(imageview);
        TextView strategy = helper.getView(R.id.strategy);
        TextView trip = helper.getView(R.id.trip);
        TextView travel = helper.getView(R.id.travel);
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TravelListActivity.class);
                intent.putExtra("cid",item.getId());
                context.startActivity(intent);
            }
        });
    }

}
