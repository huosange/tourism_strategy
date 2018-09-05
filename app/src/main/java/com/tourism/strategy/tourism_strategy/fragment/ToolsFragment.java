package com.tourism.strategy.tourism_strategy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tourism.strategy.tourism_strategy.ConsumeListActivity;
import com.tourism.strategy.tourism_strategy.DestinationActivity;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.adapter.MoneyAdapter;
import com.tourism.strategy.tourism_strategy.greendao.manager.EntityManager;
import com.tourism.strategy.tourism_strategy.model.Consume;
import com.tourism.strategy.tourism_strategy.model.Money;
import com.tourism.strategy.tourism_strategy.model.Weather;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;
import com.tourism.strategy.tourism_strategy.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * 设置
 */
public class ToolsFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;
    @BindView(R.id.address)
    public Button address;
    @BindView(R.id.total)
    public FrameLayout total;
    @BindView(R.id.add)
    public Button add;
    @BindView(R.id.edittext1)
    public EditText edittext1;
    @BindView(R.id.edittext2)
    public EditText edittext2;
    @BindView(R.id.myBg)
    public ImageView myBg;

    private List<Money> list = new ArrayList<>();
    private MoneyAdapter adapter;
    public static final int REQUEST_CODE = 1;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    private Weather mWeather;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools, null);
        ButterKnife.bind(this, view);
        initMoney();
        getWikiWeather(14);//默认是澳门
        address.setOnClickListener(this);
        total.setOnClickListener(this);
        add.setOnClickListener(this);
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        adapter = new MoneyAdapter(getActivity(), list);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        recyclerview.setAdapter(adapter);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address:
                Intent intent = new Intent(getActivity(), DestinationActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.total:
                startActivity(new Intent(getActivity(), ConsumeListActivity.class));
                break;
            case R.id.add:
                String s1 = edittext1.getText().toString();
                String summary = edittext2.getText().toString();
                if (TextUtils.isEmpty(s1)) {
                    ToastUtil.showText(getActivity(), "消费金额不能为空！");
                    return;
                }

                try {
                    double money = Double.parseDouble(s1);
                    Consume consume = new Consume(sdf.format(System.currentTimeMillis()), money, summary, mWeather.getCurrency_display());
                    EntityManager.getInstance().getConsumeDao().insert(consume);
                    edittext1.setText("");
                    edittext2.setText("");
                } catch (Exception e) {
                    ToastUtil.showText(getActivity(), "请输入正确的金额！");
                }
                break;
        }
    }

    private void getWikiWeather(int id) {
        ApiRetrofit.getInstance().getWikiWeather(id)
                .compose(NetUtils.<Weather>io_main())
                .subscribe(new Consumer<Weather>() {
                    @Override
                    public void accept(Weather weather) throws Exception {
                        mWeather = weather;
                        Glide.with(getActivity()).load("http://m.chanyouji.cn/destinations/14-landscape.jpg").into(myBg);
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 3) {
            if (requestCode == REQUEST_CODE) {
                final int id = data.getIntExtra("id", 0);
                ApiRetrofit.getInstance().getWikiWeather(id)
                        .compose(NetUtils.<Weather>io_main())
                        .subscribe(new Consumer<Weather>() {
                            @Override
                            public void accept(Weather weather) throws Exception {
                                mWeather = weather;
                                address.setText(weather.getTemp_min() + "~~" + weather.getTemp_max());
                                //自己拼凑图片url
                                Glide.with(getActivity()).load("http://m.chanyouji.cn/destinations/" + id + "-landscape.jpg").into(myBg);
                            }
                        });
            }
        }
    }
}
