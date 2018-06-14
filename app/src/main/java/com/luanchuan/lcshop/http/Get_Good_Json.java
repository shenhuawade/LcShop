package com.luanchuan.lcshop.http;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Wanghuiqiang 18838828864 on 2018/5/30.
 */
//Retrofit模式讲解（为简化各种异步请求而生，网络访问、上传等操作）：
// 第1步：构造Retrofit模式访问接口Get_Good_Json
// 第2步：Retrofit retrofit=new Retrofit.builder(){}....  构建retrofit对象
// 第3步：因为Get_Good_Json是接口，所以用retrofit来构造一个代理接口对象，命名为movieservice
//           Get_Good_Json movieservice=retrofit.creat(Get_Good_Json.class)    获得代理对象
// 第4步：接口代理对象movieservice调用接口内方法  会返回一个被观察者（此处观察者模式设计）
// 第5步：【观察者模式为】被观察者.subscribe(观察者)
// 第6步：现在构造观察者 new Subcriber<String>{....  OnNext(s)，相应被观察者传过来的数据}

// 第1步：构造Retrofit模式访问接口Get_Good_Json
public interface Get_Good_Json {
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //通用接口类型："全站领券API接口" 、"大牌推荐"、"每日必拍"、"TOP100人气榜" 均为实时有效数据！
    //请求示例：请求了一个全站的、按销量、从高到低、第一页的商品内容
    // 	http://api.tkjidi.com/getGoodsLink?appkey=7bc56a1a59cd14b7257b1ca3ded90eda&type=www_lingquan&sort=sales&sort_type=desc&page=1
    //请求示例：请求了一个全站的、搜索关键词、从高到低、第一页的keyword=裙子的内容
    // 	http://api.tkjidi.com/getGoodsLink?appkey=7bc56a1a59cd14b7257b1ca3ded90eda&type=so&sort=sales&sort_type=desc&keyword=裙子&page=1
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //baseurl：http://api.tkjidi.com/
    //【type:可以查询的内容（必填内容）】
    // www_lingquan：本站领券；
    // android_quan：安卓领券；//暂时不会用
    // ios_quan：ios领券；//暂时不会用
    // top100：人气领券；
    // dapai：大牌推荐领券；
    // bipai：每日比拍领券；
    // qq_qun_ling：qq群发的API；
    // video：看货列表；
    // jhs：巨划算产品；
    // classify：本站分类；
    // so：本站搜索  type=so&keyword=XXX   一起使用，搜索关键词type必须是so
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //【cid:可以查询的内容 int类型】		分类参数（1本站指女装）(type为classify，必填)
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //【sort:可以查询的内容	string类型】	sales:销量； coupon:优惠券； price:商品价格； real_price:到手价
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //【sort_type:可以查询的内容	string类型】	asc:从低到高排序； desc:从高到低排序 （sort不为空时必填）
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //解释下内容Observable是Rx的被观察者，如果Retrofit调用了get_Quanzhan_Good_Json方法，就会返回一个被观察者，构建观察者模式
    //全站领券访问接口（变量只有一个 page=1  page=2 等等...）
    //http://api.tkjidi.com/getGoodsLink?appkey=7bc56a1a59cd14b7257b1ca3ded90eda&type=www_lingquan&sort=sales&sort_type=desc&page=1
    @GET("getGoodsLink?appkey=7bc56a1a59cd14b7257b1ca3ded90eda&type=www_lingquan&sort=sales&sort_type=desc&page=1")
    Observable<String> get_Quanzhan_Good_JsonString(@Query("page") int page);

    //全站领券访问接口（变量只有一个 page=1 页面只有3个页面等等...）
    //http://api.tkjidi.com/getGoodsLink?appkey=7bc56a1a59cd14b7257b1ca3ded90eda&type=top100&sort=sales&sort_type=desc&page=1
    @GET("getGoodsLink?appkey=7bc56a1a59cd14b7257b1ca3ded90eda&type=top100&sort=sales&sort_type=desc&page=1")
    Observable<String> get_Top100_Good_Json(@Query("page") int page);

    //轻淘客<1>——商品列表获取 API  cat参数为商品分类
    /*  {
    "er_code" : 10000,
    "er_msg"  : "请求成功",
    "data":
    {
        "total": "" //商品总数 int
        "list":[
            {
                "goods_id" : "522914459446"  //商品ID string
                "goods_pic" : "http://img.alicdn.com/imgextra/i4/TB1u18HLpXXXXXbXpXX_!!0-item_pic.jpg" //商品主图 string
                "goods_title" : "云奈2016秋季新品韩版高腰抓痕牛仔裤女"   //商品标题 string
                "goods_short_title" : "韩版高腰抓痕牛仔裤女"   //商品短标题 string
                "goods_cat" : 7   //商品分类 int 2母婴 3美妆 4居家 5鞋包配饰 6美食 7文体 8家电数码 10女装 11内衣 12男装 9其它
                "goods_price" : 249    //商品售价 float
                "goods_sales" : 1349   //商品销量 int
                "goods_introduce" : "字迹清晰，归纳调理，简洁全面，物美价廉，内容丰富，值得信赖的选择。"   //商品文案描述 string
                "is_tmall" : 1    //是否是天猫int 1是 其他否
                "commission" : 90.5    //佣金 float
                "commission_type" : 1   //佣金类型 int 1定向计划2高拥3通用4营销计划
                "commission_link" : "http://pub.alimama.com/myunion.htm?#!/promo/self/campaign?
                         campaignId=38403567&shopkeeperId=99183620&userNumberId=2203671411"    //计划链接 string
                "coupon_is_check" : "1"    //是否校验后的券 string 0 未验证 1 已验证有效性
                "coupon_type" : "1"    //券类型 string  0 未知  1 商品单品  2 店铺
                "seller_id" : "683728440"    //卖家ID string
                "coupon_id" : "f2a48d16b81647718f997481713333d8"    //券ID string，（tip：无券阿里券的券id为空字符串）
                "coupon_price" : 10    //券价格 float
                "coupon_number" : 5000    //券剩余数 int
                "coupon_limit" : -1    //券限领数 int -1表示无限制
                "coupon_over" : 200    //券已领数 int
                "coupon_condition" : 30.5    //券使用条件 float
                "coupon_start_time" : "2017-04-28 00:00:00"    //券开始时间 string
                "coupon_end_time" : "2017-05-28 23:59:59"    //券结束时间 string
                "is_ju" : "0"    //是否取聚划算 int  0 否  1 是
                "is_tqg" : "0"    //是否取淘抢购 int  0 否  1 是
                "is_ali" : 1    //是否取阿里券 int 现在都为阿里券
            }
        ]
    }
}
     */
    //http://openapi.qingtaoke.com/qingsoulist?sort=2&app_key=LdMnLUGl&v=1.0&cat=0&min_price=1&max_price=9999&new=0&is_ju=0&is_tqg=0
    @GET("qingsoulist?sort=2&app_key=LdMnLUGl&v=1.0&cat=0&min_price=1&max_price=100&new=0&is_ju=0&is_tqg=0&page=1")
    Observable<String> get_Qtk_Good_ListJson(@Query("is_tqg") int page);
    //轻淘客商品搜索
    //http://openapi.qingtaoke.com/search?s_type=1&key_word=商品名字&app_key=LdMnLUGl&page=1&v=1.0&cat=0&min_price=1&max_price=1000&sort=1&is_ju=0&is_tqg=0&is_ali=0
    @GET("search?s_type=1&key_word=商品名字&app_key=LdMnLUGl&page=1&v=1.0&cat=0&min_price=1&max_price=1000&sort=1&is_ju=0&is_tqg=0&is_ali=0")
    Observable<String> get_Qtk_Good_Json(@Query("page") int page,@Query("key_word") String name);


    //轻淘客<2>——销量爆款 API 每2小时更新一次
    /*

  {
    "er_code" : 10000,
    "er_msg"  : "请求成功",
    "data":
    [
        {
             "goods_id" : "522914459446"  //商品ID string
             "goods_pic" : "http://img.alicdn.com/imgextra/i4/TB1u18HLpXXXXXbXpXX_!!0-item_pic.jpg" //商品主图 string
             "goods_title" : "云奈2016秋季新品韩版高腰抓痕牛仔裤女"   //商品标题 string
             "goods_short_title" : "韩版高腰抓痕牛仔裤女"   //商品短标题 string
             "goods_cat" : 7   //商品分类 int 2母婴 3美妆 4居家 5鞋包配饰 6美食 7文体 8家电数码 10女装 11内衣 12男装 9其它
             "goods_price" : 249    //商品售价 float
             "goods_sales" : 1349   //商品销量 int
             "goods_introduce" : "字迹清晰，归纳调理，简洁全面，物美价廉，内容丰富，值得信赖的选择。"   //商品文案描述 string
             "is_tmall" : 1    //是否是天猫int 1是 其他否
             "commission" : 90.5    //佣金 float
             "commission_type" : 1   //佣金类型 int 1定向计划2高拥3通用4营销计划
             "commission_link" : "http://pub.alimama.com/myunion.htm?#!/promo/self/campaign?
                      campaignId=38403567&shopkeeperId=99183620&userNumberId=2203671411"    //计划链接 string
             "coupon_is_check" : "1"    //是否校验后的券 string 0 未验证 1 已验证有效性
             "coupon_type" : "1"    //券类型 string  0 未知  1 商品单品  2 店铺
             "seller_id" : "683728440"    //卖家ID string
             "coupon_id" : "f2a48d16b81647718f997481713333d8"    //券ID string （tip：无券阿里券的券id为空字符串）
             "coupon_price" : 10    //券价格 float
             "coupon_number" : 5000    //券剩余数 int
             "coupon_limit" : -1    //券限领数 int -1表示无限制
             "coupon_over" : 200    //券已领数 int
             "coupon_condition" : 30.5    //券使用条件 float
             "coupon_start_time" : "2017-04-28 00:00:00"    //券开始时间 string
             "coupon_end_time" : "2017-05-28 23:59:59"    //券结束时间 string
             "is_ali" : 1    //是否取阿里券 int  现在都是阿里券
        }

    ]
}
     */
    //http://openapi.qingtaoke.com/baokuan?app_key=LdMnLUGl&v=1.0
    @GET("baokuan?app_key=LdMnLUGl&v=1.0")
    Observable<String> get_Qtk_Hot_Good_Json();
}

