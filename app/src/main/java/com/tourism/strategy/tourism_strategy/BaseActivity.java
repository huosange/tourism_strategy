package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private LayoutInflater inflater;
    private LinearLayout root;
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(this);
        super.setContentView(R.layout.activity_base);
        ImmersionBar.with(this).statusBarColor(R.color.status_bar_color).init();
        root = findViewById(R.id.root);
        title=findViewById(R.id.title);
    }

    public void setTitle(String t){
        title.setText(t);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = inflater.inflate(layoutResID, null);
        root.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
