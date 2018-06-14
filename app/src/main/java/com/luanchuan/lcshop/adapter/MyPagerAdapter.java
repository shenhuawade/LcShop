package com.luanchuan.lcshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.luanchuan.lcshop.fragment.BaseFragment;
import com.luanchuan.lcshop.fragment.RecyclerTemplateFragment;

import java.util.ArrayList;

/**
 * Created by good on 2018/6/8.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> mTitleList; //分类标题列表，取出来进行加载fram分页
    public MyPagerAdapter(FragmentManager fm, ArrayList<String> mTitleList) {
        super(fm);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
        this.mTitleList=mTitleList;
    }
    @Override
    public Fragment getItem(int position) {

        BaseFragment baseFragment = null;
//        switch (position) {
//            case 0:
//                baseFragment = TemplateFragment.newInstance("TemplateFragment");
//                break;
//        }

        baseFragment = RecyclerTemplateFragment.newInstance(mTitleList.get(position),null);
        //TemplateFragment加载方式1
        // RecyclerTemplateFragment 加载方式 2
        // viewpageradapter加载不同的分页，分页模板都是Template类型
        return baseFragment;
    }
    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);//页卡标题
    }
}
