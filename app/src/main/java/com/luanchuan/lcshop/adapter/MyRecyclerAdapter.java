package com.luanchuan.lcshop.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luanchuan.lcshop.R;
import com.luanchuan.lcshop.bean.MyGoodBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by good on 2018/6/8.
 */

public class MyRecyclerAdapter extends BaseQuickAdapter<Map<String,Object>,BaseViewHolder>{
    //避免重新加载内容需要引入一个mapArrayList
    private ArrayList<Map<String, Object>> mapArrayList;

    public MyRecyclerAdapter(int layoutResId, @Nullable List<Map<String, Object>> data) {
        super(layoutResId, data);
    }
    public MyRecyclerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Map<String, Object> item) {
        if (mapArrayList !=null){
            item=mapArrayList.get(helper.getLayoutPosition());
        }
        helper.setText(R.id.item_tv_title, (String)item.get("goods_title"));
        helper.setText(R.id.item_tv_des, (String) item.get("goods_cat"));
        String PicUrl = (String) item.get("goods_pic");
        if(PicUrl.startsWith("//")){
            PicUrl = "https:"+PicUrl;
        }
        // 图片加载模块，采用插件Glide
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);
        Glide.with(helper.getConvertView())
                .load(PicUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options.placeholder(R.mipmap.ic_loading_large))
                .apply(options.error(R.drawable.timg1))
                .into((ImageView) helper.getView(R.id.item_img_));
    }

    public void setMapArrayList(ArrayList<Map<String, Object>> mapArrayList) {
        this.mapArrayList = mapArrayList;
    }
}
