package com.luanchuan.lcshop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.luanchuan.lcshop.http.Get_Good_Json;
import com.luanchuan.lcshop.fragment.BookFragment;
import com.luanchuan.lcshop.fragment.FavoriteFragment;
import com.luanchuan.lcshop.fragment.HomeFragment;
import com.luanchuan.lcshop.fragment.MusicFragment;
import com.luanchuan.lcshop.util.ToStringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends FragmentActivity implements BottomNavigationBar.OnTabSelectedListener {
    Context mContext;
    private int lastSelectedPosition;
    private int mHomeMessage;
    private BottomNavigationBar bottomNavigationBar;
    private BadgeItem mHomeNumberBadgeItem,mMusicNumberBadgeItem;
    private HomeFragment mHomeFragment;
    private BookFragment mBookFragment;
    private MusicFragment mMusicFragment;
    private FavoriteFragment mFavoriteFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setDefaultFragment(savedInstanceState);
        //1寻找button


    }
    //加入这个方法可以让用户回到程序保持原来状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {


        super.onSaveInstanceState(outState);
    }
    private void initView(){

        /*
         *添加标签的消息数量
         */
        mHomeNumberBadgeItem = new BadgeItem()
                .setBorderWidth(2)
                .setBackgroundColor(Color.RED)
                .setText("22")
                .setHideOnSelect(false); // TODO 控制便签被点击时 消失 | false不消失


        /*
         *添加标签的消息数量
         */
        mMusicNumberBadgeItem = new BadgeItem()
                .setBorderWidth(2)
                .setBackgroundColor(Color.RED)
                .setText("99+")
                .setHideOnSelect(false);


        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        // TODO 设置模式
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED);
        // TODO 设置背景色样式
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);


        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "首页").setActiveColorResource(R.color.white).setBadgeItem(mHomeNumberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "9块9").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "收藏").setActiveColorResource(R.color.white).setBadgeItem(mMusicNumberBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "我的").setActiveColorResource(R.color.white))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
        // TODO 设置 BadgeItem 默认隐藏 注意 这句代码在添加 BottomNavigationItem 之后
        mHomeNumberBadgeItem.hide();
    }
    private void setDefaultFragment(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();


        if (savedInstanceState !=null) {
            mFragmentManager.popBackStackImmediate(null, 1);//弹出所有fragment
        }else {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            mHomeFragment = HomeFragment.newInstance("HomeFragment");
            transaction.add(R.id.tb, mHomeFragment);
            transaction.commit();

        }



    }

    public void quanZhanLingQuan(int page){
        getNewimg();
        // 第2步：Retrofit retrofit=new Retrofit.builder(){}....  构建retrofit对象
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(new ToStringConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://api.tkjidi.com/")
                .build();
// 第3步：因为Get_Good_Json是接口，所以用retrofit来构造一个代理接口对象，命名为movieservice
        Get_Good_Json movieservice;
        movieservice=retrofit.create(Get_Good_Json.class);
        movieservice.get_Quanzhan_Good_JsonString(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                            }
                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(String s) {
//                                Toast.makeText(mContext, "good", Toast.LENGTH_SHORT).show();
//                                textView=findViewById(R.id.testtext);
//                                textView.setText(s);
                            }
                        }

                );
    }





    public void getNewimg(){
        //1定义观察者，相应变动内容
        Subscriber<String> subscribera=new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(String s) {
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
            }
        };
        //2定义被观察者，变化内容提供者
        Observable<String > observable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("abc");
            }
        });

        //3订阅绑定
        observable.subscribe(subscribera);
    }

    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;

        //开启事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragment(transaction);

        /**
         * fragment 用 add + show + hide 方式
         * 只有第一次切换会创建fragment，再次切换不创建
         *
         * fragment 用 replace 方式
         * 每次切换都会重新创建
         *
         */
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance("HomeFragment");
                    transaction.add(R.id.tb, mHomeFragment);
                }else{
                    transaction.show(mHomeFragment);
                }
//                transaction.replace(R.id.tb, mHomeFragment);
                break;
            case 1:
                if (mBookFragment == null) {
                    mBookFragment = BookFragment.newInstance("mBookFragment");
                    transaction.add(R.id.tb, mBookFragment);
                }else{
                    transaction.show(mBookFragment);
                }
//                transaction.replace(R.id.tb, mBookFragment);
                break;
            case 2:
                if (mMusicFragment == null) {
                    mMusicFragment = MusicFragment.newInstance("mMusicFragment");
                    transaction.add(R.id.tb, mMusicFragment);
                    mMusicNumberBadgeItem.hide();
                }else{
                    transaction.show(mMusicFragment);
                    mMusicNumberBadgeItem.hide();
                }
//                transaction.replace(R.id.tb, mMusicFragment);
                break;
            case 3:
                if (mFavoriteFragment == null) {
                    mFavoriteFragment = FavoriteFragment.newInstance("mFavoriteFragment");
                    transaction.add(R.id.tb, mFavoriteFragment);
                }else{
                    transaction.show(mFavoriteFragment);
                }
//                transaction.replace(R.id.tb, mFavoriteFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }
    /**
     * 隐藏当前fragment
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction){
        if (mHomeFragment != null){
            transaction.hide(mHomeFragment);
        }
        if (mBookFragment != null){
            transaction.hide(mBookFragment);
        }
        if (mMusicFragment != null){
            transaction.hide(mMusicFragment);
        }
        if (mFavoriteFragment != null){
            transaction.hide(mFavoriteFragment);
        }
    }
    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    public class ImgAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView!=null){
                view=convertView;
            }else {
                view = View.inflate(mContext, R.layout.layout, null);
            }

            Drawable drawable = mContext.getResources().getDrawable(R.drawable.timg1);
            ImageView imageView=view.findViewById(R.id.iv_icon);
            imageView.setImageDrawable(drawable);
            return view;
        }
    }
    public void addMessage(){
        mHomeMessage ++ ;
        mHomeNumberBadgeItem.setText(mHomeMessage + "");
        mHomeNumberBadgeItem.show();
    }



}
