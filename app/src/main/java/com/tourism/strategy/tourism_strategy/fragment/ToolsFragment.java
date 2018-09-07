package com.tourism.strategy.tourism_strategy.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tourism.strategy.tourism_strategy.ConsumeListActivity;
import com.tourism.strategy.tourism_strategy.DestinationActivity;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.greendao.manager.EntityManager;
import com.tourism.strategy.tourism_strategy.model.Consume;
import com.tourism.strategy.tourism_strategy.model.Weather;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;
import com.tourism.strategy.tourism_strategy.utils.ToastUtil;
import com.tourism.strategy.tourism_strategy.widget.MyRadioGroup;

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

    @BindView(R.id.address)
    public TextView address;
    @BindView(R.id.total)
    public FrameLayout total;
    @BindView(R.id.time)
    public TextView time;
    @BindView(R.id.add)
    public TextView add;
    @BindView(R.id.edittext1)
    public EditText edittext1;
    @BindView(R.id.edittext2)
    public EditText edittext2;
    @BindView(R.id.myBg)
    public ImageView myBg;
    @BindView(R.id.low)
    public TextView low;
    @BindView(R.id.high)
    public TextView high;
    @BindView(R.id.unit)
    public TextView unit;
    @BindView(R.id.myRadioGroup)
    public MyRadioGroup myRadioGroup;

    @BindView(R.id.rb1)
    public RadioButton rb1;
    @BindView(R.id.rb2)
    public RadioButton rb2;
    @BindView(R.id.rb3)
    public RadioButton rb3;
    @BindView(R.id.rb4)
    public RadioButton rb4;
    @BindView(R.id.rb5)
    public RadioButton rb5;
    @BindView(R.id.rb6)
    public RadioButton rb6;
    @BindView(R.id.rb7)
    public RadioButton rb7;
    private int currentType = -1;

    public static final int REQUEST_CODE = 1;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    private Weather mWeather;
    private List<RadioButton> rbs = new ArrayList<>();
    private int[] drawables = new int[]{R.drawable.consume_type_qt, R.drawable.consume_type_jt, R.drawable.consume_type_cy, R.drawable.consume_type_zs, R.drawable.consume_type_mp, R.drawable.consume_type_gw, R.drawable.consume_type_yl};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools, null);
        ButterKnife.bind(this, view);
        rbs.add(rb1);
        rbs.add(rb2);
        rbs.add(rb3);
        rbs.add(rb4);
        rbs.add(rb5);
        rbs.add(rb6);
        rbs.add(rb7);
        setDrawableSize();
        getWikiWeather(14);//默认是澳门
        address.setOnClickListener(this);
        total.setOnClickListener(this);
        add.setOnClickListener(this);
        myRadioGroup.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        currentType = 0;
                        break;
                    case R.id.rb2:
                        currentType = 1;
                        break;
                    case R.id.rb3:
                        currentType = 2;
                        break;
                    case R.id.rb4:
                        currentType = 3;
                        break;
                    case R.id.rb5:
                        currentType = 4;
                        break;
                    case R.id.rb6:
                        currentType = 5;
                        break;
                    case R.id.rb7:
                        currentType = 6;
                        break;
                }
            }
        });

        return view;
    }

    private void setDrawableSize() {
        for (int i = 0; i < rbs.size(); i++) {
            RadioButton rb = rbs.get(i);
            rb.setChecked(false);
            Rect rect = new Rect();
            int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
            rect.set(0, 0, size, size);
            Drawable drawable = getResources().getDrawable(drawables[i]);
            drawable.setBounds(rect);
            rb.setCompoundDrawables(null, drawable, null, null);
        }
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
                if (currentType < 0) {
                    ToastUtil.showText(getActivity(), "必须选择消费类型！");
                    return;
                }
                try {
                    double money = Double.parseDouble(s1);
                    Consume consume = new Consume(sdf.format(System.currentTimeMillis()), money, summary, mWeather.getCurrency_display(), currentType);
                    EntityManager.getInstance().getConsumeDao().insert(consume);
                    ToastUtil.showText(getActivity(), "添加成功！");
                    edittext1.setText("");
                    edittext2.setText("");
                    setDrawableSize();
                    currentType = -1;
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
                        address.setText("澳门");
                        unit.setText("葡币");
                        low.setText(mWeather.getTemp_min() + "℃");
                        high.setText(mWeather.getTemp_max() + "℃");
                        time.setText("当地时间：" + mWeather.getCurrent_time());
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
                final String name = data.getStringExtra("name");
                final String imageUrl = data.getStringExtra("url");
                ApiRetrofit.getInstance().getWikiWeather(id)
                        .compose(NetUtils.<Weather>io_main())
                        .subscribe(new Consumer<Weather>() {
                            @Override
                            public void accept(Weather weather) throws Exception {
                                mWeather = weather;
                                address.setText(name);
                                unit.setText(weather.getCurrency_display());
                                low.setText(mWeather.getTemp_min() + "℃");
                                high.setText(mWeather.getTemp_max() + "℃");
                                time.setText("当地时间：" + mWeather.getCurrent_time());
                                Glide.with(getActivity()).load(imageUrl).into(myBg);
                            }
                        });
            }
        }
    }
}
