package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.SettingBean;

import java.util.List;

public class SettingAdapter extends BaseQuickAdapter<SettingBean, BaseViewHolder> {

    private Context context;

    public SettingAdapter(Context context, List<SettingBean> list) {
        super(R.layout.item_setting, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingBean item) {
        helper.setText(R.id.textview, item.getName())
                .setImageResource(R.id.imageview, item.getIcon());
    }
}
