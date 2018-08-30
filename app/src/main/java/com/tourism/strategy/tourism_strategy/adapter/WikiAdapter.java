package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.OverViewActivity;
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
    protected void convert(BaseViewHolder helper, final Wiki item) {
        helper.setText(R.id.title, StringUtils.getTitleByType(item.getCategory_type()));
        ImageView imageview=helper.getView(R.id.imageview);
        int icon = StringUtils.getIconByType(item.getCategory_type());
        if (icon > 0) {
            imageview.setBackgroundResource(icon);
        }
        RecyclerView recyclerview=helper.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(context,2));
        ChildAdapter adapter=new ChildAdapter(context,item.getPages());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(context, OverViewActivity.class);
                Wiki.Page page=item.getPages().get(position);
                intent.putExtra("page",page);
                intent.putExtra("name",page.getTitle());
                context.startActivity(intent);
            }
        });
        recyclerview.setAdapter(adapter);
    }
}
