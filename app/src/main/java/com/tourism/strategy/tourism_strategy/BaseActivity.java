package com.tourism.strategy.tourism_strategy;

import android.app.Dialog;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private LayoutInflater inflater;
    private LinearLayout root;
    private TextView title;
    private RelativeLayout titlebar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(this);
        super.setContentView(R.layout.activity_base);
        ImmersionBar.with(this).statusBarColor(R.color.status_bar_color).keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN|WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN).init();
        root = findViewById(R.id.root);
        title = findViewById(R.id.title);
        titlebar = findViewById(R.id.titlebar);
    }

    public void hideTitle() {
        titlebar.setVisibility(View.GONE);
    }

    public void setTitle(String t) {
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

    private Dialog progressDialog;

    public void showDialog() {
        progressDialog = new Dialog(this, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.dialog);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.progressTextView);
        msg.setText("卖力加载中");
        progressDialog.show();
    }

    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
