package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.media.Image;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Wiki;

import java.util.List;

public class PhotoAdapter extends BaseQuickAdapter<Wiki.Page.Child.Section.Photo, BaseViewHolder> {

    private Context context;

    public PhotoAdapter(Context context, List<Wiki.Page.Child.Section.Photo> list) {
        super(R.layout.item_photo, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Wiki.Page.Child.Section.Photo item) {
        ImageView imageview = helper.getView(R.id.imageview);
        ViewGroup.LayoutParams params = imageview.getLayoutParams();
        params.height = 450;
        params.width = (int) (((double) item.getImage_width() / item.getImage_height()) * params.height);
        imageview.setLayoutParams(params);
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.icon_default_image);
        Glide.with(context).load(item.getImage_url()).apply(options).into(imageview);
    }
}
