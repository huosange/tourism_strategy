package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.AlbumActivity;
import com.tourism.strategy.tourism_strategy.AttractionDetailActivity;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.TourismActivity;
import com.tourism.strategy.tourism_strategy.model.AttractionContent;
import com.tourism.strategy.tourism_strategy.model.AttractionDetail;
import com.tourism.strategy.tourism_strategy.model.HtmlTextItem;
import com.tourism.strategy.tourism_strategy.model.ItemAttraction;
import com.tourism.strategy.tourism_strategy.model.ItemAttractionList;
import com.tourism.strategy.tourism_strategy.model.MultipleItem;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private Context context;

    public DetailAdapter(Context context, List<MultipleItem> list) {
        super(list);
        this.context = context;
        addItemType(MultipleItem.TEXT_AND_IMAGES, R.layout.item_text_and_images);
        addItemType(MultipleItem.TEXT, R.layout.item_text);
        addItemType(MultipleItem.HEADER, R.layout.item_header);
        addItemType(MultipleItem.NEAR, R.layout.item_near);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                HtmlTextItem htmlTextItem = (HtmlTextItem) item;
                helper.setText(R.id.textview, Html.fromHtml(htmlTextItem.getHtmlText()));
                break;
            case MultipleItem.TEXT_AND_IMAGES:
                AttractionContent ac = (AttractionContent) item;
                String description = ac.getDescription();
                SpannableString spannableString = new SpannableString(description);

                List<Integer> indexs = new ArrayList<>();
                for (int i = 0; i < spannableString.length(); i++) {
                    String temp = spannableString.charAt(i) + "";
                    if (temp.equals("#")) {
                        indexs.add(i);
                    }
                }
                for (int j = 0; j < indexs.size(); j = j + 2) {
                    spannableString.setSpan(new StyleSpan(Typeface.BOLD), indexs.get(j) + 1, indexs.get(j + 1), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
                helper.setText(R.id.textview, spannableString);

                //给recyclerview设置数据源
                RecyclerView recyclerview = helper.getView(R.id.recyclerview);
                recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                ImageView imageview = helper.getView(R.id.imageview);
                ImageAdapter adapter = new ImageAdapter(context, ac.getNotes());
                recyclerview.setAdapter(adapter);
                break;
            case MultipleItem.HEADER:
                ImageView iv = helper.getView(R.id.imageview);
                final AttractionDetail ad = (AttractionDetail) item;
                Glide.with(context).load(ad.getImage_url()).into(iv);
                TextView photoTv = helper.getView(R.id.imageCount);
                TextView tourismTv = helper.getView(R.id.tourismCount);
                photoTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, AlbumActivity.class);
                        intent.putExtra("attractionId", ad.getId());
                        intent.putExtra("count", ad.getPhotos_count());
                        context.startActivity(intent);
                    }
                });
                tourismTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, TourismActivity.class);
                        intent.putExtra("attractionId", ad.getId());
                        context.startActivity(intent);
                    }
                });

                helper.setText(R.id.name_en, ad.getName_en())
                        .setText(R.id.name_cn, ad.getName_zh_cn())
                        .setText(R.id.imageCount, "图片" + ad.getPhotos_count())
                        .setText(R.id.tourismCount, "游记" + ad.getAttraction_trips_count());
                break;
            case MultipleItem.NEAR:
                final ItemAttractionList itemAttractionList = (ItemAttractionList) item;
                helper.setText(R.id.textview, (itemAttractionList.getTitle()));
                RecyclerView rc = helper.getView(R.id.recyclerview);
                NearAdapter nearAdapter = new NearAdapter(context, itemAttractionList.getList());
                rc.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                nearAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (itemAttractionList.getTitle().equals("附近旅行地")) {
                            Intent intent = new Intent(context, AttractionDetailActivity.class);
                            intent.putExtra("attractionId", itemAttractionList.getList().get(position).getId());
                            context.startActivity(intent);
                        } else {
                            return;
                        }
                    }
                });
                rc.setAdapter(nearAdapter);
                break;
        }
    }
}
