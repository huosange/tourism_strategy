package com.tourism.strategy.tourism_strategy.fragment;

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
public class YoujiFragment extends BaseFragment {

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
        return view;
    }
}
