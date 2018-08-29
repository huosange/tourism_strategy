package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Wiki;
import com.tourism.strategy.tourism_strategy.utils.StringUtils;

import java.util.List;

public class WikiAdapter extends BaseQuickAdapter<Wiki, BaseViewHolder> {

    private Context context;

    public WikiAdapter(Context context, List<Wiki> list) {
        super(R.layout.item_wiki_parent, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Wiki item) {
        helper.setText(R.id.title, StringUtils.getTitleByType(item.getCategory_type()));
        TextView textview = helper.getView(R.id.title);
        int icon = StringUtils.getIconByType(item.getCategory_type());
        if (icon > 0) {
            textview.setCompoundDrawables(context.getResources().getDrawable(icon), null, null, null);
        }
    }
}
