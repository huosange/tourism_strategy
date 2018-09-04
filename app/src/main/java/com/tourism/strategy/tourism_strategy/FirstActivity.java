package com.tourism.strategy.tourism_strategy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.tourism.strategy.tourism_strategy.fragment.GonglueFragment;
import com.tourism.strategy.tourism_strategy.fragment.SettingFragment;
import com.tourism.strategy.tourism_strategy.fragment.ToolsFragment;
import com.tourism.strategy.tourism_strategy.fragment.YoujiFragment;
import com.tourism.strategy.tourism_strategy.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    public BottomBar bottomBar;

    private List<Fragment> fragmentList = new ArrayList<>();
    private int oldPage = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        hideTitle();
        ButterKnife.bind(this);

        initFragment();
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                int page = -1;
                switch (tabId) {
                    case R.id.tab_youji:
                        page = 0;
                        break;
                    case R.id.tab_gonglue:
                        page = 1;
                        break;
                    case R.id.tab_gongju:
                        page = 2;
                        break;
                    case R.id.tab_shezhi:
                        page = 3;
                        break;
                }
                if (oldPage != page) {
                    changePage(page);
                }
            }
        });

        setView();
    }

    private void initFragment() {
        fragmentList.add(new YoujiFragment());
        fragmentList.add(new GonglueFragment());
        fragmentList.add(new ToolsFragment());
        fragmentList.add(new SettingFragment());
    }

    private void setView() {
        getSupportFragmentManager().beginTransaction().add(R.id.contentContainer, fragmentList.get(0)).show(fragmentList.get(0)).commit();
    }

    public void changePage(int pagePosition) {
        FragmentTransaction bt = getSupportFragmentManager().beginTransaction();
        bt.hide(fragmentList.get(oldPage));
        if (fragmentList.get(pagePosition).isAdded()) {
            bt.show(fragmentList.get(pagePosition)).commit();
        } else {
            bt.add(R.id.contentContainer, fragmentList.get(pagePosition)).show(fragmentList.get(pagePosition)).commit();
        }
        oldPage = pagePosition;
    }

    private long mBackPressed;
    private static final int TIME_INTERVAL = 2000;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            ToastUtil.showText(this, "再按一次退出应用");
        }
        mBackPressed = System.currentTimeMillis();
    }
}
