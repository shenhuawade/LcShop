package com.luanchuan.lcshop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.ButterKnife;


/**
 * Created by good on 2018/6/6.
 */

public abstract class BaseFragment extends Fragment {
    private String name1,name2;
    KProgressHUD hud;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mRootView = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view,name1,name2);

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            name1 = args.getString("name1");
            name2 = args.getString("name2");
        }
    }
    /**
     * 返回初始化布局
     */
    protected abstract void init(View view,String name1,String name2);

    /**
     * 设置根布局资源id
     * @author 漆可
     * @date 2016-5-26 下午3:57:09
     * @return
     */
    protected abstract int getLayout();
    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}

