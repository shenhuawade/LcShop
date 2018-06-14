package com.luanchuan.lcshop.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.luanchuan.lcshop.R;
import com.luanchuan.lcshop.adapter.BeanAdapter;
import com.luanchuan.lcshop.bean.SetGoodBean;
import com.luanchuan.lcshop.bean.MyGoodBean;

import java.util.ArrayList;

/**
 * Created by good on 2018/5/31.HomeFragment
 */

public class BookFragment extends BaseFragment {
    private LinearLayout buju;
    private View Heaner;
    private PullToRefreshListView mPullRefreshGridView;
    private ListView mGridView;
    private ConvenientBanner convenientBanner;

    @Override
    protected void init(View view,String name1,String name2) {
        buju = (LinearLayout) view.findViewById(R.id.buju);//加载一个liner控件，名字叫布局，控件在activity——first。xml里边
        Heaner = LayoutInflater.from(getActivity()).inflate(R.layout.listview_addhoe, null);//加载中间组合内容，京东超级返，作为view
        mPullRefreshGridView =view.findViewById(R.id.pull_refresh_grid);//下拉加载动画控件在activity——first。xml里边
        mGridView = mPullRefreshGridView.getRefreshableView();//下拉加载动画
        convenientBanner =Heaner.findViewById(R.id.iamges);//上部大图部分
        mGridView.addHeaderView(Heaner);
        ArrayList<MyGoodBean> beans=new ArrayList<>();
        beans=new SetGoodBean().setbeans(getContext());
        BeanAdapter adapter = new BeanAdapter(getContext(),R.layout.list_new_layout,beans);
        mGridView.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment2;
    }




    public static BookFragment newInstance(String s) {

        Bundle args = new Bundle();

        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
