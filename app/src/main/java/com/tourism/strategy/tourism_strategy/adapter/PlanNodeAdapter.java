package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.AttractionDetailActivity;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.PlanDetail;

import java.util.ArrayList;
import java.util.List;

public class PlanNodeAdapter extends BaseQuickAdapter<PlanDetail.PlanDay.PlanNode, BaseViewHolder> {

    private Context context;

    public PlanNodeAdapter(Context context, List<PlanDetail.PlanDay.PlanNode> list) {
        super(R.layout.item_plan_node, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final PlanDetail.PlanDay.PlanNode item) {
        SpannableString spannableString = new SpannableString(item.getTips());

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

        helper.setText(R.id.entry_name, item.getEntry_name())
                .setText(R.id.tips, spannableString);
        ImageView imageview = helper.getView(R.id.imageview);
        Glide.with(context).load(item.getImage_url()).into(imageview);
    }
}
