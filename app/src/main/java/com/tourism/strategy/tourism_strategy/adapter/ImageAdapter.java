package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Note;

import java.util.List;

public class ImageAdapter extends BaseQuickAdapter<Note,BaseViewHolder>{

    private Context context;

    public ImageAdapter(Context context,List<Note> list){
        super(R.layout.item_hor_image,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Note item) {
        ImageView imageview=helper.getView(R.id.imageview);
        ViewGroup.LayoutParams params=imageview.getLayoutParams();
        params.height=450;
        params.width= (int) (((double)item.getWidth()/item.getHeight())*params.height);
        imageview.setLayoutParams(params);
        Glide.with(context).load(item.getPhoto_url()).into(imageview);
    }
}
