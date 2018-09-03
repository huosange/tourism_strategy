package com.tourism.strategy.tourism_strategy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tourism.strategy.tourism_strategy.DestinationActivity;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.adapter.MoneyAdapter;
import com.tourism.strategy.tourism_strategy.model.Money;
import com.tourism.strategy.tourism_strategy.model.Weather;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.http.bean.Api;
import io.reactivex.functions.Consumer;

/**
 * 设置
 */
public class ToolsFragment extends Fragment {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private List<Money> list = new ArrayList<>();
    private MoneyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools, null);
        ButterKnife.bind(this, view);
        initMoney();
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        adapter = new MoneyAdapter(getActivity(), list);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), DestinationActivity.class);
                startActivity(intent);
            }
        });
        recyclerview.setAdapter(adapter);

//        ApiRetrofit.getInstance().getWikiWeather(1)
//                .compose(NetUtils.<Weather>io_main())
//                .subscribe(new Consumer<Weather>() {
//                    @Override
//                    public void accept(Weather weather) throws Exception {
//                        Log.i("a","b");
//                    }
//                });
        return view;
    }

    private void initMoney() {
        Money money1 = new Money(R.mipmap.type_qt_wxz, "其他");
        list.add(money1);

        Money money2 = new Money(R.mipmap.type_jt_wxz, "交通");
        list.add(money2);

        Money money3 = new Money(R.mipmap.type_cy_wxz, "餐饮");
        list.add(money3);

        Money money4 = new Money(R.mipmap.type_zs_wxz, "住宿");
        list.add(money4);

        Money money5 = new Money(R.mipmap.type_mp_wxz, "门票");
        list.add(money5);

        Money money6 = new Money(R.mipmap.type_gw_wxz, "购物");
        list.add(money6);

        Money money7 = new Money(R.mipmap.type_yl_wxz, "娱乐");
        list.add(money7);
    }
}