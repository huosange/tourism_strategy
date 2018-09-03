package com.tourism.strategy.tourism_strategy.model.expand;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.WikiDestinations;
import com.zaihuishou.expandablerecycleradapter.viewholder.AbstractExpandableAdapterItem;

public class ChildItem extends AbstractExpandableAdapterItem {

    private TextView name;

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
                //跳回到工具包fragment
            }
        });
    }

    @Override
    public void onSetViews() {

    }

    @Override
    public void onUpdateViews(Object model, int position) {
        super.onUpdateViews(model, position);
        MyChild m = (MyChild) model;
        name.setText(m.name);
    }
}
