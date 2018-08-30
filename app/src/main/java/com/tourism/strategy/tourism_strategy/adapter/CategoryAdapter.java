package com.tourism.strategy.tourism_strategy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tourism.strategy.tourism_strategy.CountryActivity;
import com.tourism.strategy.tourism_strategy.R;
import com.tourism.strategy.tourism_strategy.model.Category;
import com.tourism.strategy.tourism_strategy.model.Country;

import java.util.List;

public class CategoryAdapter extends BaseQuickAdapter<Category,BaseViewHolder>{

    private Context context;

    public CategoryAdapter(Context context,List<Category> list){
        super(R.layout.item_category,list);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        String cid=item.getCategory();
        String title="";
        if(cid.equals("1")){
            title="国外.亚洲";
        }else if(cid.equals("2")){
            title="国外.欧洲";
        }else if(cid.equals("3")){
            title="国外.其它";
        }else if(cid.equals("99")){
            title="国内.港澳台";
        }else if(cid.equals("999")){
            title="国内.大陆";
        }
        helper.setText(R.id.title,title);
        RecyclerView itemRecyclerview=helper.getView(R.id.recyclerview);
        itemRecyclerview.setLayoutManager(new GridLayoutManager(context,2));
        final List<Country> countries=item.getDestinations();
        CountryAdapter adapter=new CountryAdapter(context,countries);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(context, CountryActivity.class);
                intent.putExtra("name",countries.get(position).getName_zh_cn());
                intent.putExtra("cid",countries.get(position).getId());
                context.startActivity(intent);
            }
        });
        itemRecyclerview.setAdapter(adapter);
    }
}
