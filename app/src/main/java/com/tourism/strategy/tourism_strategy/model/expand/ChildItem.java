package com.tourism.strategy.tourism_strategy.model.expand;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tourism.strategy.tourism_strategy.FirstActivity;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.WikiDestinations;
import com.zaihuishou.expandablerecycleradapter.viewholder.AbstractExpandableAdapterItem;

public class ChildItem extends AbstractExpandableAdapterItem {

    private TextView name;
    private Activity activity;
    private MyChild m;

    public ChildItem(Activity activity){
        this.activity=activity;
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_child;
    }

    @Override
    public void onBindViews(View root) {
        name = root.findViewById(R.id.textview);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("id",m.id);
                intent.putExtra("url",m.image_url);
                activity.setResult(3,intent);
                activity.finish();
            }
        });
    }

    @Override
    public void onSetViews() {

    }

    @Override
    public void onUpdateViews(Object model, int position) {
        super.onUpdateViews(model, position);
        m = (MyChild) model;
        name.setText(m.name);
    }
}
