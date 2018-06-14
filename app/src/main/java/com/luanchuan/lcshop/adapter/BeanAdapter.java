package com.luanchuan.lcshop.adapter;
import android.content.Context;

import com.luanchuan.lcshop.R;
import com.luanchuan.lcshop.bean.MyGoodBean;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

import static com.luanchuan.lcshop.R.id.item_tv_des;
import static com.luanchuan.lcshop.R.id.item_tv_title;

/**
 * Created by good on 2018/6/4.
 */

public class BeanAdapter extends CommonAdapter<MyGoodBean> {
    public BeanAdapter(Context context, int layoutId, List<MyGoodBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, MyGoodBean item, int position) {
        viewHolder.setText(item_tv_title,item.news_title);
        viewHolder.setText(item_tv_des,item.news_des);
        viewHolder.setImageDrawable(R.id.item_img_,item.news_icon);
    }

}