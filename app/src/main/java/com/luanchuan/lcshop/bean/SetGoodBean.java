package com.luanchuan.lcshop.bean;

import android.content.Context;

import com.luanchuan.lcshop.R;

import java.util.ArrayList;

/**
 * Created by good on 2018/6/4.
 */

public class SetGoodBean {
    public ArrayList<MyGoodBean> setbeans(Context context){
        ArrayList<MyGoodBean> myGoodBeans =new ArrayList<>();
        MyGoodBean myGoodBean1 =new MyGoodBean();
        myGoodBean1.news_title = "崇尚英雄缅怀先烈,铭记习近平十句话";
        myGoodBean1.news_des = "美国国务卿蓬佩奥当日表示，伊朗如果不改变当前路线";
        myGoodBean1.news_url = "http://www.sina.com";
        myGoodBean1.news_icon =context.getResources().getDrawable(R.drawable.timg1);
        myGoodBeans.add(myGoodBean1);

        MyGoodBean newsBean1 = new MyGoodBean();
        newsBean1.news_title = "激励干部担当作为 中央专门印发此文件 请接收！";
        newsBean1.news_des = "天地英雄气，千秋尚凛然。”无数英雄先烈是我们民族的脊梁，是我";
        newsBean1.news_url = "http://www.sina.com";
        newsBean1.news_icon = context.getResources().getDrawable(R.drawable.timg1);
        myGoodBeans.add(newsBean1);

        MyGoodBean newsBean2 = new MyGoodBean();
        newsBean2.news_title = "深圳突进源头创新 发起“原点冲击”";
        newsBean2.news_des = "在《关于进一步激励广大干部新时代新担当新作为的意见》";
        newsBean2.news_url = "http://www.sina.com";
        newsBean2.news_icon = context.getResources().getDrawable(R.drawable.timg1);
        myGoodBeans.add(newsBean2);

        MyGoodBean newsBean3 = new MyGoodBean();
        newsBean3.news_title = "中美经贸暂时停战？中方:不希望反复";
        newsBean3.news_des = "新华社北京5月21日电（记者闫子敏）针对有一些美国官员称中";
        newsBean3.news_url = "http://www.sina.com";
        newsBean3.news_icon = context.getResources().getDrawable(R.drawable.timg1);
        myGoodBeans.add(newsBean3);

        MyGoodBean newsBean4 = new MyGoodBean();
        newsBean4.news_title = "美国务卿蓬佩奥威胁对伊朗实施“最严厉”制裁";
        newsBean4.news_des = "5月21日，美国国务卿蓬佩奥在华盛顿的美国智库传统基金会发表演讲。美国国务卿蓬佩奥当日表示，伊朗如果不改变当前路线，将受到美国“最";
        newsBean4.news_url = "http://www.sina.com";
        newsBean4.news_icon = context.getResources().getDrawable(R.drawable.timg1);
        myGoodBeans.add(newsBean4);

        MyGoodBean newsBean5 = new MyGoodBean();
        newsBean5.news_title = "过好“三期”关口 向美丽中国进发";
        newsBean5.news_des = "“两山论”引领绿色发展之路";
        newsBean5.news_url = "http://www.sina.com";
        newsBean5.news_icon = context.getResources().getDrawable(R.drawable.timg1);
        myGoodBeans.add(newsBean5);

        MyGoodBean newsBean6 = new MyGoodBean();
        newsBean6.news_title = "过好“三期”关口 向美丽中国进发";
        newsBean6.news_des = "“两山论”引领绿色发展之路";
        newsBean6.news_url = "http://www.sina.com";
        newsBean6.news_icon = context.getResources().getDrawable(R.drawable.timg1);
        myGoodBeans.add(newsBean6);

        MyGoodBean newsBean7 = new MyGoodBean();
        newsBean7.news_title = "过好“三期”关口 向美丽中国进发";
        newsBean7.news_des = "“两山论”引领绿色发展之路";
        newsBean7.news_url = "http://www.sina.com";
        newsBean7.news_icon = context.getResources().getDrawable(R.drawable.timg1);
        myGoodBeans.add(newsBean7);

    return myGoodBeans;
    }
}
