package com.luanchuan.lcshop.fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.luanchuan.lcshop.R;
import com.luanchuan.lcshop.adapter.MyPagerAdapter;
import com.luanchuan.lcshop.adapter.MyRecyclerAdapter;
import com.luanchuan.lcshop.bean.MyGoodBean;
import com.luanchuan.lcshop.bean.SetGoodBean;

import java.util.ArrayList;


/**
 * Created by good on 2018/5/31.
 */

public class FavoriteFragment extends BaseFragment {
    private ArrayList<String> mTitleList = new ArrayList<>();//页卡标题集合
    private ArrayList<String> mKindsList = new ArrayList<>();//页卡每页子标题集合

    private View view1, view2, view3, view4, view5;//页卡视图
    private ArrayList<View> mViewList = new ArrayList<>();//页卡视图集合
    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    protected void init(View view,String name1,String name2) {
        //添加页卡标题
        mTitleList.add("女装");
        mTitleList.add("男鞋");
        mTitleList.add("美食");
        mTitleList.add("百货");
        mTitleList.add("护肤");
        mTitleList.add("男装");
        mTitleList.add("男鞋");
        mTitleList.add("美食");
        mTitleList.add("百货");
        mTitleList.add("护肤");


        //支持添加
        TabLayout mTabLayout = view.findViewById(R.id.templatepagertab4);   //绑定xml中的pagertab
        ViewPager mviewPager = view.findViewById(R.id.templatviewpager4);
        mviewPager.setAdapter(new MyPagerAdapter(getFragmentManager(), mTitleList));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
        mTabLayout.setupWithViewPager(mviewPager);
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment4;
    }

    public static FavoriteFragment newInstance(String s) {

        Bundle args = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
