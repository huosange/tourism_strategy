package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Wiki;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends BaseQuickAdapter<Wiki.Page.Child.Section, BaseViewHolder> {

    public Context context;

    public SectionAdapter(Context context, List<Wiki.Page.Child.Section> list) {
        super(R.layout.item_section, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Wiki.Page.Child.Section item) {
        SpannableString spannableString = new SpannableString(item.getDescription());
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
        TextView textview = helper.getView(R.id.description);
        textview.setText(spannableString);

        TextView titleTv = helper.getView(R.id.title);
        if (TextUtils.isEmpty(item.getTitle())) {
            titleTv.setVisibility(View.GONE);
        } else {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(item.getTitle());
        }
        RecyclerView recyclerview = helper.getView(R.id.recyclerview);
        PhotoAdapter adapter = new PhotoAdapter(context, item.getPhotos());
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerview.setAdapter(adapter);
    }
}
