package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Consume;

import java.util.List;

public class ConsumeAdapter extends BaseQuickAdapter<Consume, BaseViewHolder> {

    private int[] images = new int[]{R.mipmap.type_qt_wxz, R.mipmap.type_jt_wxz, R.mipmap.type_cy_wxz, R.mipmap.type_zs_wxz, R.mipmap.type_mp_wxz, R.mipmap.type_gw_wxz, R.mipmap.type_yl_wxz};
    private String[] names = new String[]{"其他", "交通", "餐饮", "住宿", "门票", "购物", "娱乐"};

    public ConsumeAdapter(List<Consume> list) {
        super(R.layout.item_consume, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Consume item) {
        helper.setText(R.id.time, item.getTime())
                .setText(R.id.money, item.getMoney() + "韩元")
                .setText(R.id.summary, item.getSummary())
                .setText(R.id.typeName, names[item.getType()])
                .setImageResource(R.id.imageview, images[item.getType()]);
    }
}
