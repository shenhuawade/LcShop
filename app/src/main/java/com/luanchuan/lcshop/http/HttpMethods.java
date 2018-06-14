package com.luanchuan.lcshop.http;

import android.os.Environment;

import com.luanchuan.lcshop.io.OkHttp3Utils;
import com.luanchuan.lcshop.util.ToStringConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by good on 2018/6/4.
 */

public class HttpMethods {
    private static OkHttpClient okHttpClient = null;
    public static String BASE_URL = "http://openapi.qingtaoke.com/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private Get_Good_Json movieService;

    //构造方法私有
    private HttpMethods() {


        //手动创建一个OkHttpClient并设置超时时间
        //设置安全加载内容getokhttpclient()，保护加载流程


        retrofit = new Retrofit.Builder()
                .client(getokhttpclient())
                .addConverterFactory(new ToStringConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(Get_Good_Json.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }


    public static OkHttpClient getokhttpclient() {
        if (okHttpClient == null) {
            //加同步安全
            synchronized (OkHttp3Utils.class) {
                if (okHttpClient == null) {
                    //判空 为空创建实例
                    // okHttpClient = new OkHttpClient();
/**
 * 和OkHttp2.x有区别的是不能通过OkHttpClient直接设置超时时间和缓存了，而是通过OkHttpClient.Builder来设置，
 * 通过builder配置好OkHttpClient后用builder.build()来返回OkHttpClient，
 * 所以我们通常不会调用new OkHttpClient()来得到OkHttpClient，而是通过builder.build()：
 */
                    //  File sdcache = getExternalCacheDir();
                    File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
                    int cacheSize = 10 * 1024 * 1024;
                    okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).cache(new Cache(sdcache.getAbsoluteFile(), cacheSize)).build();

                }
            }

        }

        return okHttpClient;
    }



    /**
     * 用于获取豆瓣电影Top250的数据
     * @param subscriber 由调用者传过来的观察者对象
    // @param start 起始位置
     * @param page 获取长度
     */
    public void getTopMovie(Subscriber<String> subscriber, int page,String name1){
        movieService.get_Qtk_Good_Json(page,name1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}