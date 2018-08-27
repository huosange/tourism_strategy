package com.tourism.strategy.tourism_strategy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tourism.strategy.tourism_strategy.adapter.CategoryAdapter;
import com.tourism.strategy.tourism_strategy.model.Category;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private List<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        final CategoryAdapter adapter = new CategoryAdapter(this, categoryList);
        recyclerview.setAdapter(adapter);

        ApiRetrofit.getInstance().getAreas()
                .compose(NetUtils.<List<Category>>io_main())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        if (categories != null) {
                            categoryList.addAll(categories);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
