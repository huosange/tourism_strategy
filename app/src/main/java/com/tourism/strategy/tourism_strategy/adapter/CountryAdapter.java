package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Country;

import java.util.List;

public class CountryAdapter extends BaseQuickAdapter<Country, BaseViewHolder> {

    private Context context;

    public CountryAdapter(Context context, List<Country> list) {
        super(R.layout.item_country, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Country item) {
        ImageView imageview = helper.getView(R.id.imageview);
        String url = item.getImage_url();
        url = url.replace("http://cyjm.qiniudn.com", "http://m.chanyouji.cn");
        Glide.with(context).load(url).into(imageview);
        helper.setText(R.id.name_cn, item.getName_zh_cn())
                .setText(R.id.name_en, item.getName_en())
                .setText(R.id.count, item.getPoi_count() + "个旅行地");
    }
}
