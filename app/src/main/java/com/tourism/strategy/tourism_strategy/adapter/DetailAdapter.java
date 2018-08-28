package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.AttractionTags;
import com.tourism.strategy.tourism_strategy.model.MultipleItem;
import com.tourism.strategy.tourism_strategy.model.Note;

import java.util.List;

public class DetailAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private Context context;

    public DetailAdapter(Context context, List<MultipleItem> list) {
        super(list);
        this.context = context;
        addItemType(MultipleItem.TEXT_AND_IMAGES, R.layout.item_text_and_images);
        addItemType(MultipleItem.TEXT, R.layout.item_text);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                break;
            case MultipleItem.TEXT_AND_IMAGES:
                AttractionTags tag = (AttractionTags) item;
                //横向展示图片的列表
                RecyclerView recyclerview = helper.getView(R.id.recyclerview);
                ImageAdapter adapter = new ImageAdapter(context, tag.getAttraction_contents().get(0).getNotes());
                recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerview.setAdapter(adapter);


                helper.setText(R.id.textview, tag.getName());

                Note note = tag.getAttraction_contents().get(0).getNotes().get(0);
                ImageView imageview = helper.getView(R.id.imageview);
                ViewGroup.LayoutParams params = imageview.getLayoutParams();
                params.height = 450;
                params.width = (int) (((double) note.getWidth() / note.getHeight()) * 450);
                imageview.setLayoutParams(params);
                Glide.with(context).load(tag.getAttraction_contents().get(0).getNotes().get(0).getPhoto_url()).into(imageview);
                break;
        }
    }
}
