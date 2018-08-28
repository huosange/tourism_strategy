package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tourism.strategy.tourism_strategy.adapter.DetailAdapter;
import com.tourism.strategy.tourism_strategy.model.AttractionDetail;
import com.tourism.strategy.tourism_strategy.model.AttractionTags;
import com.tourism.strategy.tourism_strategy.model.MultipleItem;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.net.MyApi;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class AttractionDetailActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;

    @BindView(R.id.content)
    public TextView content;
    @BindView(R.id.imageview)
    public ImageView imageview;
    @BindView(R.id.name_cn)
    public TextView nameCn;
    @BindView(R.id.name_en)
    public TextView nameEn;
    @BindView(R.id.imageCount)
    public TextView imageCount;
    @BindView(R.id.tourismCount)
    public TextView tourismCount;

    private int attractionId;

    private DetailAdapter adapter;
    private List<MultipleItem> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);
        ButterKnife.bind(this);


        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new DetailAdapter(this,list);
        recyclerview.setAdapter(adapter);


        attractionId = getIntent().getIntExtra("attractionId", 0);
        ApiRetrofit.getInstance().getAttraction(attractionId)
                .compose(NetUtils.<AttractionDetail>io_main())
                .subscribe(new Consumer<AttractionDetail>() {
                    @Override
                    public void accept(AttractionDetail attractionDetail) throws Exception {
                        nameCn.setText(attractionDetail.getName_zh_cn());
                        nameEn.setText(attractionDetail.getName_en());
                        tourismCount.setText("游记"+attractionDetail.getAttraction_trips_count());
                        imageCount.setText("图片"+attractionDetail.getPhotos_count());

                        content.setText(attractionDetail.getDescription()+"\n"+Html.fromHtml(attractionDetail.getTips_html()));
                        Glide.with(AttractionDetailActivity.this).load(attractionDetail.getImage_url()).into(imageview);
                        //设置recyclerview数据
                        List<AttractionTags> tags=attractionDetail.getAttraction_trip_tags();
                        for(AttractionTags tag:tags){
                            tag.setItemType(MultipleItem.TEXT_AND_IMAGES);
                            list.add(tag);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
