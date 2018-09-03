package com.tourism.strategy.tourism_strategy.model.expand;

import com.zaihuishou.expandablerecycleradapter.model.ExpandableListItem;

import java.util.List;

public class MyParent implements ExpandableListItem{

    public boolean mExpanded=false;
    public String name;
    public List<MyChild> children;

    @Override
    public List<?> getChildItemList() {
        return children;
    }

    @Override
    public boolean isExpanded() {
        return mExpanded;
    }

    @Override
    public void setExpanded(boolean isExpanded) {
        this.mExpanded=isExpanded;
    }
}
