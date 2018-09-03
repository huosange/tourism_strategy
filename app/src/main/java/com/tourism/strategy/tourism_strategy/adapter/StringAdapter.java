package com.tourism.strategy.tourism_strategy.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.WikiDestinations;

import java.util.List;

public class StringAdapter extends BaseQuickAdapter<WikiDestinations.Destination, BaseViewHolder> {

    public StringAdapter(List<WikiDestinations.Destination> list) {
        super(R.layout.item_string, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, WikiDestinations.Destination item) {
        helper.setText(R.id.textview, item.getName_zh_cn());
    }
}
