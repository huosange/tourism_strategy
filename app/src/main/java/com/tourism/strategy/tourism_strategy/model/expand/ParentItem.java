package com.tourism.strategy.tourism_strategy.model.expand;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.WikiDestinations;
import com.zaihuishou.expandablerecycleradapter.viewholder.AbstractExpandableAdapterItem;

public class ParentItem extends AbstractExpandableAdapterItem {

    private TextView name;
    private ImageView icon;

    @Override
    public void onExpansionToggled(boolean expanded) {
        if (expanded) {
            icon.setBackgroundResource(R.mipmap.icon_top);
        } else {
            icon.setBackgroundResource(R.mipmap.icon_bottom);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_string;
    }

    @Override
    public void onBindViews(View root) {
        name = root.findViewById(R.id.textview);
        icon=root.findViewById(R.id.icon);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doExpandOrUnexpand();
            }
        });
    }

    @Override
    public void onSetViews() {

    }

    @Override
    public void onUpdateViews(Object model, int position) {
        super.onUpdateViews(model, position);
        MyParent m= (MyParent) model;
        name.setText(m.name);
    }
}
