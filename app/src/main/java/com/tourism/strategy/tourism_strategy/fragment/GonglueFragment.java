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
import com.tourism.strategy.tourism_strategy.adapter.CategoryAdapter;
import com.tourism.strategy.tourism_strategy.model.Category;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class GonglueFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private List<Category> categoryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gonglue, container, false);
        ButterKnife.bind(this, view);

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        final CategoryAdapter adapter = new CategoryAdapter(getActivity(), categoryList);
        recyclerview.setAdapter(adapter);

        if (NetUtils.isNetworkConnected(getActivity())) {
            ApiRetrofit.getInstance().getAreas()
                    .compose(NetUtils.<List<Category>>io_main())
                    .subscribe(new Consumer<List<Category>>() {
                        @Override
                        public void accept(List<Category> categories) throws Exception {
                            if (categories != null) {
                                categoryList.addAll(categories);
                            }
                            adapter.notifyDataSetChanged();
                            hideDialog();
                        }
                    });
        }
        return view;
    }
}
