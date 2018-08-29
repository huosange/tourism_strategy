package com.tourism.strategy.tourism_strategy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tourism.strategy.tourism_strategy.adapter.DetailAdapter;
import com.tourism.strategy.tourism_strategy.model.AttractionContent;
import com.tourism.strategy.tourism_strategy.model.AttractionDetail;
import com.tourism.strategy.tourism_strategy.model.AttractionTags;
import com.tourism.strategy.tourism_strategy.model.HtmlTextItem;
import com.tourism.strategy.tourism_strategy.model.ItemAttraction;
import com.tourism.strategy.tourism_strategy.model.ItemAttractionList;
import com.tourism.strategy.tourism_strategy.model.MultipleItem;
import com.tourism.strategy.tourism_strategy.net.ApiRetrofit;
import com.tourism.strategy.tourism_strategy.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class AttractionDetailActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    public RecyclerView recyclerview;
    private int attractionId;

    private DetailAdapter adapter;
    private List<MultipleItem> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);
        ButterKnife.bind(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailAdapter(this, list);
        recyclerview.setAdapter(adapter);
        attractionId = getIntent().getIntExtra("attractionId", 0);
        ApiRetrofit.getInstance().getAttraction(attractionId)
                .compose(NetUtils.<AttractionDetail>io_main())
                .subscribe(new Consumer<AttractionDetail>() {
                    @Override
                    public void accept(AttractionDetail attractionDetail) throws Exception {
                        //设置recyclerview数据
                        List<AttractionTags> tags = attractionDetail.getAttraction_trip_tags();
                        //header
                        attractionDetail.setItemType(MultipleItem.HEADER);
                        list.add(attractionDetail);
                        //纯文本
                        HtmlTextItem htmlTextItem = new HtmlTextItem();
                        htmlTextItem.setHtmlText(attractionDetail.getTips_html());
                        htmlTextItem.setItemType(MultipleItem.TEXT);
                        list.add(htmlTextItem);
                        //图片和文字item
                        for (AttractionTags tag : tags) {
                            for (AttractionContent ac : tag.getAttraction_contents()) {
                                ac.setItemType(MultipleItem.TEXT_AND_IMAGES);
                                list.add(ac);
                            }
                        }
                        //附近履行地和酒店
                        ItemAttractionList nearAttractions = new ItemAttractionList("附近旅行地");
                        nearAttractions.setList(attractionDetail.getAttractions());
                        nearAttractions.setItemType(MultipleItem.NEAR);
                        list.add(nearAttractions);
                        ItemAttractionList nearHotels = new ItemAttractionList("附近酒店");
                        nearHotels.setList(attractionDetail.getHotels());
                        nearHotels.setItemType(MultipleItem.NEAR);
                        list.add(nearHotels);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
