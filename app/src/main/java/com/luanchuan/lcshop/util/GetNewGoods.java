package com.luanchuan.lcshop.util;

import android.content.Context;
import android.widget.ListView;

import com.luanchuan.lcshop.http.Get_Good_Json;

import java.util.ArrayList;


/**
 * Created by good on 2018/5/31.
 */
// 第1步：构造Retrofit模式访问接口Get_Good_Json
// 第2步：Retrofit retrofit=new Retrofit.builder(){}....  构建retrofit对象
// 第3步：因为Get_Good_Json是接口，所以用retrofit来构造一个代理接口对象，命名为movieservice
//           Get_Good_Json movieservice=retrofit.creat(Get_Good_Json.class)    获得代理对象
// 第4步：接口代理对象movieservice调用接口内方法  会返回一个被观察者（此处观察者模式设计）
// 第5步：【观察者模式为】被观察者.subscribe(观察者)
// 第6步：现在构造观察者 new Subcriber<String>{....  OnNext(s)，相应被观察者传过来的数据}
public class GetNewGoods  {
    private Get_Good_Json movieservice;
    Context context;
    private ListView mGridView;
    String abcd;
    private ArrayList<String> mapArrayList;

    public  GetNewGoods(Context context){
    this.context=context;
    }


    // 第4步：接口代理对象movieservice调用接口内方法  会返回一个被观察者（此处观察者模式设计）
    /*
     * 用于获取推举的数据
     * @param subscriber 由调用者传过来的观察者对象
     * @param page 页码
     */

}


