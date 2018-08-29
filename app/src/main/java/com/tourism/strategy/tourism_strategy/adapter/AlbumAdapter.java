package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Album;

import java.util.List;

public class AlbumAdapter extends BaseQuickAdapter<Album,BaseViewHolder>{

    private Context context;
    private DisplayMetrics dm;

    public AlbumAdapter(Context context, List<Album> list){
        super(R.layout.item_album,list);
        this.context=context;
        this.dm=context.getResources().getDisplayMetrics();
    }

    @Override
    protected void convert(BaseViewHolder helper, Album item) {
        ImageView imageview=helper.getView(R.id.imageview);
        ViewGroup.LayoutParams params=imageview.getLayoutParams();


        params.width= (int) (dm.widthPixels/2);
        params.height= (int) (((double)item.getImage_height()/item.getImage_width())*params.width);
        imageview.setLayoutParams(params);
        Glide.with(context).load(item.getImage_url()).into(imageview);
    }
}
