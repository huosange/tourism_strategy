package com.tourism.strategy.tourism_strategy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.adapter.SettingAdapter;
import com.tourism.strategy.tourism_strategy.model.SettingBean;
import com.tourism.strategy.tourism_strategy.utils.CacheDataManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置
 */
public class SettingFragment extends Fragment {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private SettingAdapter adapter;
    private List<SettingBean> list = new ArrayList<>();
    private android.os.Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        ButterKnife.bind(this, view);

        handler = new Handler();
        list.add(new SettingBean(R.mipmap.icon_update, "检查更新"));
        list.add(new SettingBean(R.mipmap.icon_about_us, "关于我们"));
        list.add(new SettingBean(R.mipmap.icon_clear, "清除缓存：" + CacheDataManager.getTotalCacheSize(getActivity())));
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SettingAdapter(getActivity(), list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //检查更新
                        break;
                    case 1:
                        //关于我们
                        break;
                    case 2:
                        //清理缓存
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                CacheDataManager.clearAllCache(getActivity());
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        list.remove(2);
                                        list.add(new SettingBean(R.mipmap.icon_clear, "清除缓存：" + CacheDataManager.getTotalCacheSize(getActivity())));
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }).start();
                        break;
                }
            }
        });
        recyclerview.setAdapter(adapter);
        return view;
    }
}
