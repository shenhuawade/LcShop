package com.luanchuan.lcshop.adapter;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.luanchuan.lcshop.R;
import com.luanchuan.lcshop.bean.MyGoodBean;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.luanchuan.lcshop.R.id.item_tv_des;
import static com.luanchuan.lcshop.R.id.item_tv_title;

/**
 * Created by good on 2018/6/4.
 */

public class MyAdapterJson extends CommonAdapter<Map<String,Object>> {
    //避免重新加载内容需要引入一个mapArrayList
    private ArrayList<Map<String, Object>> mapArrayList;
    public MyAdapterJson(Context context, int layoutId, List<Map<String,Object>> datas) {
        super(context, layoutId, datas);
    }
    @Override
    protected void convert(ViewHolder viewHolder, Map<String, Object> item, int position) {
        if (mapArrayList !=null){
            item=mapArrayList.get(position);
        }
        viewHolder.setText(item_tv_title, (String)item.get("goods_title"));
        viewHolder.setText(item_tv_des, (String) item.get("goods_cat"));
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
        Glide.with(viewHolder.getConvertView())
                .load(PicUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options.placeholder(R.mipmap.ic_loading_large))
                .apply(options.error(R.drawable.timg1))
                .into((ImageView) viewHolder.getView(R.id.item_img_));
//        viewHolder.setOnClickListener(item_tv_title, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "你点击了。。。。"+, Toast.LENGTH_SHORT).show();
//
//            }
//        });
//

    }

    public void setMapArrayList(ArrayList<Map<String, Object>> mapArrayList) {
        this.mapArrayList = mapArrayList;
    }
}