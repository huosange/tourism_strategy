package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Money;

import java.util.List;

public class MoneyAdapter extends BaseQuickAdapter<Money,BaseViewHolder>{

    private Context context;

    public MoneyAdapter(Context context,List<Money> list){
        super(R.layout.item_money,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Money item) {
        ImageView imageview=helper.getView(R.id.imageview);
        imageview.setImageResource(item.getIcon());
        helper.setText(R.id.textview,item.getName());
    }
}
