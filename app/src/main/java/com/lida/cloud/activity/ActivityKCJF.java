package com.lida.cloud.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.fragment.FragmentInIntegral;
import com.lida.cloud.fragment.FragmentOutIntegral;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库存积分
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityKCJF extends BaseFragmentActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static TextView tvKCJF;
    public static String selid;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.bg1)
    View bg1;
    @BindView(R.id.bg2)
    View bg2;

    private List<Fragment> fragments = new ArrayList<>();
    private FragmentInIntegral fragmentInIntegral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcjf);
        ButterKnife.bind(this);
        tvKCJF = (TextView) findViewById(R.id.tvKCJF);
        selid = mBundle.getString("selid");
        topbar.setTitle("库存积分");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        fragmentInIntegral = new FragmentInIntegral();
        fragments.add(fragmentInIntegral);
        fragments.add(new FragmentOutIntegral());
        viewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setButton(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setButton(int i) {
        if (i == 0) {
            btn1.setTextColor(Color.parseColor("#FA2220"));
            btn2.setTextColor(Color.parseColor("#363636"));
            bg1.setBackgroundColor(Color.parseColor("#FA2220"));
            bg2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if (i == 1) {
            btn2.setTextColor(Color.parseColor("#FA2220"));
            btn1.setTextColor(Color.parseColor("#363636"));
            bg2.setBackgroundColor(Color.parseColor("#FA2220"));
            bg1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @OnClick({R.id.btnBuy,R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBuy:
                Bundle bundle = new Bundle();
                bundle.putString("selid", selid);
                UIHelper.jumpForResult(_activity, ActivityBuyJF.class, bundle, 1001);
                break;
            case R.id.btn1:
                setButton(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.btn2:
                setButton(1);
                viewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            fragmentInIntegral.onResume();
        }
    }
}
