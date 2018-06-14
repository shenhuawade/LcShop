package com.luanchuan.lcshop.fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import com.kaopiz.kprogresshud.KProgressHUD;
import com.luanchuan.lcshop.R;

import com.luanchuan.lcshop.adapter.MyRecyclerAdapter;
import com.luanchuan.lcshop.http.HttpMethods;
import com.luanchuan.lcshop.jsonfactory.JsonToMap;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.Map;
import rx.Subscriber;
/**111111
 * Created by good on 2018/5/31.
 * 继承BaseFragment
 * init方法：初始化布局
 * getLayout() 重写方法，返回布局id，不用调用
 * newInstance（） 由主函数调用，创建fragment实例
 * getRequestdata  第一次请求网络初始化布局
 * getmovie（）  构造观察者函数，实现订阅
 */

public class RecyclerTemplateFragment extends BaseFragment{
    private View Heaner;
    private RecyclerView mRecycleView; //recycle列表
    private Subscriber<String> subscriber;
    public ArrayList<Map<String ,Object>> maps;
    public ArrayList<Map<String ,Object>> mapscaches;
    private KProgressHUD hud;
    public int paths=1;
    private String name1;
    private MyRecyclerAdapter mAdapter;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;

    @Override
    protected void init(View view, final String name1, String name2) {
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setLinearLayout();
//        mRecycleView=view.findViewById(R.id.recyclerview);
        Heaner = LayoutInflater.from(getActivity()).inflate(R.layout.listview_addhoe, null);//加载中间组合内容，京东超级返，作为view
//        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));//设置recycler Manager
//        mRecycleView.setHasFixedSize(true);//设置recyclerView标记，如果确定内容的高度都一致，设置为true，提高内容渲染效率；（如果高度不确定系统要自己适配高度）
//        getMovie(paths,name1);
        mPullLoadMoreRecyclerView.setFooterViewText("12312321");
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                System.out.println("最优券正在加载中");
                //设置下拉时显示的日期和时间
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                // 更新显示的label
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                                Toast.makeText(getContext(), "已刷新", Toast.LENGTH_SHORT).show();
                            }
                        },0);  //默认3000
                    }
                });
            }

            @Override
            public void onLoadMore() {
                //设置下拉时显示的日期和时间
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // 更新显示的label
                paths = paths+1;
                getMovie(paths,name1);;//加载列表内容函数入口，每次下拉，paths+1，更新数据

            }
        });

        getRequestdata(paths,name1);

    }
    /**
     * 请求网络数据,生成一个map,并调用绑定adapter进行加载
     */
    private void getRequestdata(int paths, String name1) {
//        hud= KProgressHUD.create(getActivity())
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("飞速加载中")
//                .setCancellable(true)
//                .setAnimationSpeed(2)
//                .setDimAmount(10.5f)
//                .show();
        HttpMethods.BASE_URL = "http://openapi.qingtaoke.com/";
        HttpMethods.getInstance().getTopMovie(new Subscriber<String>() {
            @Override
            public void onCompleted() {
//                Toast.makeText(getContext(), "已加载最新内容", Toast.LENGTH_SHORT).show();
//                if (hud!=null){
//                    hud.dismiss();
//                }
            }
            @Override
            public void onError(Throwable e) {
//                Toast.makeText(getActivity(), "加载失败，没有该类目数据", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNext(String s) {
                //请求的字符串转化成MAP列表对象//适配信息
                maps = JsonToMap.getArrJson(s);
                mAdapter=new MyRecyclerAdapter(R.layout.list_new_layout,maps); //设置适配器adapter
//                mRecycleView.setAdapter(mAdapter);
                mPullLoadMoreRecyclerView.setAdapter(mAdapter);  //mPullLoadMoreRecyclerView绑定适配器
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        }, paths,name1);
    }




    //进行网络请求关键代码，由getrequestdata和下拉动作调用// url请求解析成json形成list列表模块
    private void getMovie(final int paths,String name1){
        subscriber = new Subscriber<String >() {
            @Override
            public void onCompleted() {
//                Toast.makeText(getContext(), "已加载最新内容", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onError(Throwable e) {
//                textView.setText(e.getMessage());
                Toast.makeText(getContext(), "请检查是否联网", Toast.LENGTH_SHORT).show();}
            @Override
            public void onNext(String s) {
                //mRecycleView.setVisibility(View.GONE);
                maps.addAll(JsonToMap.getArrJson(s));   //请求的字符串转化成MAP列表对象//适配信息
                System.out.println(maps);
                mAdapter.setMapArrayList(maps);
                mAdapter.openLoadAnimation();
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();   //下拉控件加载完成
                mAdapter.notifyDataSetChanged();//调用适配器模块//goodlistview1列表里边显示适配的数据
//                Toast.makeText(getContext(), "已经加载最新n", Toast.LENGTH_SHORT).show();


            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber, paths,name1);       //调用OKhttp来订阅消息
    }



    @Override
    protected int getLayout() {return R.layout.tempfragment_userecycleview;}

    public static RecyclerTemplateFragment newInstance(String name1, String name2) {
        Bundle args = new Bundle();
        RecyclerTemplateFragment fragment = new RecyclerTemplateFragment();
        args.putString("name1", name1);   //接收fragment之间传递参数
        args.putString("name2", name2);   //接收fragment之间传递参数，备用
        fragment.setArguments(args);
        return fragment;
    }


}
