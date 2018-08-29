package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tourism.strategy.tourism_strategy.adapter.AlbumAdapter;
import com.tourism.strategy.tourism_strategy.model.Album;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class AlbumActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    private int attractionId;
    private int count;
    private List<Album> list = new ArrayList<>();
    private AlbumAdapter adapter;
    private int totalPage;
    private int currentPage = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);

        count = getIntent().getIntExtra("count", 0);
        attractionId = getIntent().getIntExtra("attractionId", 0);
        totalPage = count / 10 + (count % 10 == 0 ? 0 : 1);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new AlbumAdapter(this, list);
        //分页加载
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (currentPage >= totalPage) {
                    adapter.loadMoreEnd();
                } else {
                    currentPage++;
                    load(currentPage);
                }
            }
        }, recyclerview);
        recyclerview.setAdapter(adapter);
        load(currentPage);
    }

    private void load(int page) {
        ApiRetrofit.getInstance().getAlbum(attractionId, page)
                .compose(NetUtils.<List<Album>>io_main())
                .subscribe(new Consumer<List<Album>>() {
                    @Override
                    public void accept(List<Album> albums) throws Exception {
                        list.addAll(albums);
                        adapter.notifyDataSetChanged();
                        adapter.loadMoreComplete();
                    }
                });
    }
}
