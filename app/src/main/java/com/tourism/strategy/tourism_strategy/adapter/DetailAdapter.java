package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.AttractionContent;
import com.tourism.strategy.tourism_strategy.model.AttractionDetail;
import com.tourism.strategy.tourism_strategy.model.HtmlTextItem;
import com.tourism.strategy.tourism_strategy.model.MultipleItem;

import java.util.List;

public class DetailAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private Context context;

    public DetailAdapter(Context context, List<MultipleItem> list) {
        super(list);
        this.context = context;
        addItemType(MultipleItem.TEXT_AND_IMAGES, R.layout.item_text_and_images);
        addItemType(MultipleItem.TEXT, R.layout.item_text);
        addItemType(MultipleItem.HEADER, R.layout.item_header);
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
                StyleSpan bold = new StyleSpan(Typeface.BOLD);
                int start = spannableString.toString().indexOf("#");
                int end = spannableString.toString().lastIndexOf("#");
                spannableString.setSpan(bold, start + 1, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
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
                AttractionDetail ad = (AttractionDetail) item;
                Glide.with(context).load(ad.getImage_url()).into(iv);
                helper.setText(R.id.name_en, ad.getName_en())
                        .setText(R.id.name_cn, ad.getName_zh_cn())
                        .setText(R.id.imageCount, "图片" + ad.getPhotos_count())
                        .setText(R.id.tourismCount, "游记" + ad.getAttraction_trips_count());
                break;
        }
    }
}
