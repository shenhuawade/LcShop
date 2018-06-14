package com.luanchuan.lcshop.fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.view.View;

import com.luanchuan.lcshop.R;

import com.luanchuan.lcshop.adapter.MyPagerAdapter;


import java.util.ArrayList;


/**
 * Created by good on 2018/5/31.
 * 继承BaseFragment
 * init方法：初始化布局
 * getLayout() 重写方法，返回布局id，不用调用
 * newInstance（） 由主函数调用，创建fragment实例
 * getRequestdata  第一次请求网络初始化布局
 * getmovie（）  构造观察者函数，实现订阅
 */

public class HomeFragment extends BaseFragment {
    private ArrayList<String> mTitleList = new ArrayList<>();//页卡标题集合
    private ArrayList<String> mKindsList = new ArrayList<>();//页卡每页子标题集合

    private View view1, view2, view3, view4, view5;//页卡视图
    private ArrayList<View> mViewList = new ArrayList<>();//页卡视图集合
    private ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    protected void init(View view,String name1,String name2) {
        //添加页卡标题
        mTitleList.add("男装");
        mTitleList.add("男鞋");
        mTitleList.add("美食");
        mTitleList.add("百货");
        mTitleList.add("护肤");
        mTitleList.add("男装");
        mTitleList.add("男鞋");
        mTitleList.add("美食");
        mTitleList.add("百货");
        mTitleList.add("护肤");
        //添加子标题

        mKindsList.add("面部护理");
        mKindsList.add("美发护发");
        mKindsList.add("女性卫生");
        mKindsList.add("面膜");
        mKindsList.add("护肤套装");
        mKindsList.add("卸妆洁面");
        mKindsList.add("夏日防晒");

        //支持添加
        TabLayout mTabLayout=view.findViewById(R.id.templatepagertab1);   //绑定xml中的pagertab
        ViewPager mviewPager =view.findViewById(R.id.templatviewpager1);
        mviewPager.setAdapter(new MyPagerAdapter(getFragmentManager(),mTitleList));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
        mTabLayout.setupWithViewPager(mviewPager);
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment1;
    }

    public static HomeFragment newInstance(String s) {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
