package com.tourism.strategy.tourism_strategy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tourism.strategy.tourism_strategy.model.Address;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class SwitchActivity extends AppCompatActivity {

    @BindView(R.id.textview)
    public TextView textview;

    private android.os.Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        handler = new Handler();
        textview.setText("正在检查版本...");
        Bmob.initialize(this, "17b9d6c85c508ae554350d6282e096a0");
        BmobQuery<Address> query = new BmobQuery<Address>();
        query.getObject("68a5367394", new QueryListener<Address>() {
            @Override
            public void done(final Address address, BmobException e) {
                if (e == null) {
                    if (address.getUrl().equals("http://www.baidu.com")) {
                        //跳转到自己的应用
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(SwitchActivity.this, MainActivity.class));
                                finish();
                            }
                        }, 2000);
                    } else {
                        //跳转到webview
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(SwitchActivity.this, CustomWebViewActivity.class);
                                intent.putExtra("url", address.getUrl());
                                startActivity(intent);
                                finish();
                            }
                        }, 2000);
                    }
                } else {
                    textview.setText("检查版本失败...");
                }
            }
        });
    }
}
