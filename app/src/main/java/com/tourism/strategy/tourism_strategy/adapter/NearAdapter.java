package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.ItemAttraction;

import java.text.DecimalFormat;
import java.util.List;

public class NearAdapter extends BaseQuickAdapter<ItemAttraction,BaseViewHolder>{

    private Context context;
    private DecimalFormat df;

    public NearAdapter(Context context,List<ItemAttraction> list){
        super(R.layout.item_near_item,list);
        this.context=context;
        df=new DecimalFormat("#.###");
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemAttraction item) {
        helper.setText(R.id.textview,item.getName_zh_cn())
        .setText(R.id.distance,df.format(item.getDistance())+"km");
        ImageView imageview=helper.getView(R.id.imageview);
        Glide.with(context).load(item.getImage_url()).into(imageview);
    }
}
