package com.tourism.strategy.tourism_strategy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.WikiDestinations;
import com.tourism.strategy.tourism_strategy.model.expand.ChildItem;
import com.tourism.strategy.tourism_strategy.model.expand.MyChild;
import com.tourism.strategy.tourism_strategy.model.expand.MyParent;
import com.tourism.strategy.tourism_strategy.model.expand.ParentItem;
import com.zaihuishou.expandablerecycleradapter.adapter.BaseExpandableAdapter;
import com.zaihuishou.expandablerecycleradapter.viewholder.AbstractAdapterItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DestinationFragment extends Fragment {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private BaseExpandableAdapter expandableAdapter;

    private final int ITEM_TYPE_PARENT = 1;
    private final int ITEM_TYPE_CHILD = 2;
    private List<MyParent> list = new ArrayList<>();

    public void setList(List<WikiDestinations.Destination> temp) {
        for (WikiDestinations.Destination wd : temp) {
            MyParent m = new MyParent();
            m.name = wd.getName_zh_cn();
            m.mExpanded = false;
            List<MyChild> m2s = new ArrayList<>();
            for (WikiDestinations.Destination.Child c : wd.getChildren()) {
                MyChild m2 = new MyChild();
                m2.name = c.getName_zh_cn();
                m2.id = c.getId();
                m2.image_url = c.getImage_url();
                m2s.add(m2);
            }
            m.children = m2s;
            list.add(m);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destination, container, false);
        ButterKnife.bind(this, view);

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        initAdapter();
        recyclerview.setAdapter(expandableAdapter);
        return view;
    }

    private void initAdapter() {
        expandableAdapter = new BaseExpandableAdapter(list) {
            @NonNull
            @Override
            public AbstractAdapterItem<Object> getItemView(Object type) {
                int itemType = (int) type;
                switch (itemType) {
                    case ITEM_TYPE_PARENT:
                        return new ParentItem();
                    case ITEM_TYPE_CHILD:
                        return new ChildItem(getActivity());
                }
                return null;
            }

            @Override
            public Object getItemViewType(Object t) {
                if (t instanceof MyParent) {
                    return ITEM_TYPE_PARENT;
                } else if (t instanceof MyChild) {
                    return ITEM_TYPE_CHILD;
                }
                return -1;
            }
        };
    }
}
