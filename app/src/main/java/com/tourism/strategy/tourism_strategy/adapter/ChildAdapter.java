package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Wiki;

import java.util.List;

/**
 * Created by Auser on 2018/8/29.
 */

public class ChildAdapter extends BaseQuickAdapter<Wiki.Page,BaseViewHolder>{

    private Context context;
    public ChildAdapter(Context context, List<Wiki.Page> list){
        super(R.layout.item_wiki_child,list);
        this.context=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, Wiki.Page item) {
        helper.setText(R.id.textview,item.getTitle());
    }
}
