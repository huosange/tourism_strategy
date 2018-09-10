package com.tourism.strategy.tourism_strategy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tourism.strategy.tourism_strategy.FirstActivity;
import com.tourism.strategy.tourism_strategy.HomeTripDetailActivity;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.adapter.HomeTripAdapter;
import com.tourism.strategy.tourism_strategy.model.HomeTrip;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.MyImageLoader;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.http.bean.Api;
import io.reactivex.functions.Consumer;

/**
 * 游记Fragment
 */
public class YoujiFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private List<HomeTrip> list = new ArrayList<>();
    private HomeTripAdapter adapter;
    private LayoutInflater myInflater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_youji, container, false);
        ButterKnife.bind(this, view);

        myInflater = LayoutInflater.from(getActivity());
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeTripAdapter(getActivity(), list);
        adapter.addHeaderView(getBannerHeader());
        adapter.addHeaderView(getButtonHeader());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), HomeTripDetailActivity.class);
                startActivity(intent);
            }
        });
        recyclerview.setAdapter(adapter);
        ApiRetrofit.getInstance().getHomeTrip(1)
                .compose(NetUtils.<List<HomeTrip>>io_main())
                .subscribe(new Consumer<List<HomeTrip>>() {
                    @Override
                    public void accept(List<HomeTrip> homeTrips) throws Exception {
                        list.addAll(homeTrips);
                        adapter.notifyDataSetChanged();
                    }
                });
        return view;
    }

    private View getBannerHeader() {
        View view = myInflater.inflate(R.layout.youji_header_banner, null);
        Banner banner = view.findViewById(R.id.banner);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.banner_1);
        list.add(R.mipmap.banner_2);
        list.add(R.mipmap.banner_4);
        banner.setImages(list);
        banner.setImageLoader(new MyImageLoader());
        banner.start();
        return view;
    }

    public View getButtonHeader() {
        View view = myInflater.inflate(R.layout.youji_header_button, null);
        TextView textview1 = view.findViewById(R.id.textview1);
        TextView textview2 = view.findViewById(R.id.textview2);
        TextView textview3 = view.findViewById(R.id.textview3);
        textview1.setOnClickListener(this);
        textview2.setOnClickListener(this);
        textview3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        FirstActivity activity = (FirstActivity) getActivity();
        switch (v.getId()) {
            case R.id.textview1:
                activity.bottomBar.setDefaultTab(R.id.tab_gonglue);
                break;
            case R.id.textview2:
                activity.bottomBar.setDefaultTab(R.id.tab_gongju);
                break;
            case R.id.textview3:
                activity.bottomBar.setDefaultTab(R.id.tab_gongju);
                break;
        }
    }
}
