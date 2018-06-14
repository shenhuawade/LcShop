package com.luanchuan.lcshop.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.luanchuan.lcshop.R;
import com.luanchuan.lcshop.adapter.MyAdapterJson;
import com.luanchuan.lcshop.http.HttpMethods;
import com.luanchuan.lcshop.jsonfactory.JsonToMap;

import java.util.ArrayList;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by good on 2018/5/31.
 * 继承BaseFragment
 * init方法：初始化布局
 * getLayout() 重写方法，返回布局id，不用调用
 * newInstance（） 由主函数调用，创建fragment实例
 * getRequestdata  第一次请求网络初始化布局
 * getmovie（）  构造观察者函数，实现订阅
 */

public class TemplateFragment extends BaseFragment {
    private LinearLayout buju;
    private View Heaner;
    private PullToRefreshListView mPullRefreshGridView;
    private ListView mGridView;
    private KProgressHUD hud;
    private ConvenientBanner convenientBanner;
    private int paths = 1;
    private LinearLayout linearLayout;
    private Subscriber<String> subscriber;
    public ArrayList<Map<String ,Object>> maps;
    private MyAdapterJson jsonadapter;
    @Override
    protected void init(View view, final String name1, String name2) {
        buju = (LinearLayout) view.findViewById(R.id.buju);//加载基础布局，tempfragment_uselistview.xml里边
        Heaner = LayoutInflater.from(getActivity()).inflate(R.layout.listview_addhoe, null);//加载中间组合内容，京东超级返，作为view
        mPullRefreshGridView =view.findViewById(R.id.pull_refresh_grid);//下拉加载动画控件在控件在fragment1。xml里边
        mGridView = mPullRefreshGridView.getRefreshableView();//下拉加载动画
        convenientBanner =Heaner.findViewById(R.id.iamges);//上部大图部分
        mGridView.addHeaderView(Heaner);
//        ArrayList<MyGoodBean> beans;
//        beans=new SetGoodBean().setbeans(getContext());
//        BeanAdapter adapter = new BeanAdapter(getContext(),R.layout.list_new_layout,beans);
//        mGridView.setAdapter(adapter);
        mPullRefreshGridView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新监听
        mPullRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //设置下拉时显示的日期和时间
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                // 更新显示的label
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mPullRefreshGridView.onRefreshComplete();
                                Toast.makeText(getContext(), "已刷新", Toast.LENGTH_SHORT).show();
                            }
                        },0);  //默认3000
                    }
                });
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //设置下拉时显示的日期和时间
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // 更新显示的label
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                paths = paths+1;
                getMovie(paths,name1);;//加载列表内容函数入口，每次下拉，paths+1，更新数据
            }
        });
        getRequestdata(name1);
        Toast.makeText(getContext(), name1, Toast.LENGTH_SHORT).show();

    }


    /**
     * 第一次请求网络数据
     */
    private void getRequestdata(String name1) {
        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("飞速加载中")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        HttpMethods.BASE_URL = "http://openapi.qingtaoke.com/";
        HttpMethods.getInstance().getTopMovie(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                hud.dismiss();
                //Toast.makeText(getActivity(), "请求失败,请稍后尝试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String s) {
                buju.setVisibility(View.GONE);
                hud.dismiss();
//                Toast.makeText(getActivity(), "==="+s, Toast.LENGTH_SHORT).show();
                maps = JsonToMap.getArrJson(s);
                jsonadapter=new MyAdapterJson(getContext(),R.layout.list_new_layout,maps); //调用适配器模块
                mGridView.setAdapter(jsonadapter);          //调用适配器模块//goodlistview1列表里边显示适配的数据

            }
        }, 1,name1);
    }

    //进行网络请求关键代码，由getrequestdata和下拉动作调用// url请求解析成json形成list列表模块
    private void getMovie(final int paths,String name1){
        subscriber = new Subscriber<String >() {
            @Override
            public void onCompleted() {
                Toast.makeText(getContext(), "已加载最新内容", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Throwable e) {
//                textView.setText(e.getMessage());
                Toast.makeText(getContext(), "请检查是否联网", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNext(String jsonString) {
                buju.setVisibility(View.GONE);
                maps.addAll(JsonToMap.getArrJson(jsonString));   //请求的字符串转化成MAP列表对象//适配信息
                System.out.println(maps);
                jsonadapter.setMapArrayList(maps);
                jsonadapter.notifyDataSetChanged();//调用适配器模块//goodlistview1列表里边显示适配的数据
                mPullRefreshGridView.onRefreshComplete();
                Toast.makeText(getContext(), "已经加载最新n", Toast.LENGTH_SHORT).show();
            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber, paths,name1);       //调用OKhttp来订阅消息
    }
    @Override
    protected int getLayout() {
        return R.layout.tempfragment_uselistview;
    }
    public static TemplateFragment newInstance(String name1,String name2) {
        Bundle args = new Bundle();
        TemplateFragment fragment = new TemplateFragment();
        args.putString("name1", name1);   //用于fragment之间传递参数
        args.putString("name2", name2);   //用于fragment之间传递参数，备用
        fragment.setArguments(args);
        return fragment;
    }


}
