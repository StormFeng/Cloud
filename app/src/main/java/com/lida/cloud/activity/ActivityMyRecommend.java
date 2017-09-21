package com.lida.cloud.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.RecommendBean;
import com.lida.cloud.fragment.FragmentRecommendMem;
import com.lida.cloud.fragment.FragmentRecommendShop;
import com.lida.cloud.widght.DialogShare;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的推荐
 * Created by WeiQingFeng on 2017/5/5.
 */

public class ActivityMyRecommend extends BaseFragmentActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.llMoney)
    LinearLayout llMoney;
    @BindView(R.id.tvMark)
    TextView tvMark;
    @BindView(R.id.llMark)
    LinearLayout llMark;
    @BindView(R.id.vTab1)
    View vTab1;
    @BindView(R.id.vTab2)
    View vTab2;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tvShop)
    TextView tvShop;
    @BindView(R.id.tvMem)
    TextView tvMem;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecommend);
        ButterKnife.bind(this);
        topbar.setTitle("我的推荐");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("分享", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogShare(_activity,"加入云众利，消费不再贵","泉州云众利网络科技有限公司（以下简称：云众利）由福建本土民营企业家黄文汉先生投资创建，于2017年4月在泉州工商局注册成立（目前注册资金为800万元）","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495425393735&di=4f6f7ee60cb289f43298e649c2e01615&imgtype=0&src=http%3A%2F%2Ffile06.16sucai.com%2F2016%2F0324%2F7b07c97b5e653c45c37499236848d519.jpg",
                        ac.getProperty("shareUrl")).show();
            }
        });
        new FragmentRecommendShop();
        fragments.add(new FragmentRecommendShop());
        fragments.add(new FragmentRecommendMem());
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
        AppUtil.getCloudApiClient(ac).commend(this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            RecommendBean bean = (RecommendBean) res;
            if (bean.getData().getMoney().get(0).getM() == null) {
                tvMoney.setText("0");
            } else {
                tvMoney.setText(bean.getData().getMoney().get(0).getM());
            }
            if (bean.getData().getCredit().get(0).getC() == null) {
                tvMark.setText("0");
            } else {
                tvMark.setText(bean.getData().getCredit().get(0).getC());
            }
            tvShop.setText("推荐的商户(" + bean.getData().getShop() + ")");
            tvMem.setText("推荐的会员(" + bean.getData().getMember() + ")");
        }
    }

    @OnClick({R.id.llMoney, R.id.llMark, R.id.tvShop, R.id.tvMem})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llMoney:
                UIHelper.jump(_activity, ActivityReward.class);
                break;
            case R.id.llMark:
                UIHelper.jump(_activity, ActivityRewardMark.class);
                break;
            case R.id.tvShop:
                setButton(0);
                break;
            case R.id.tvMem:
                setButton(1);
                break;
        }
    }

    private void setButton(int i){
        if(i==0){
            vTab1.setBackgroundColor(Color.parseColor("#FA2220"));
            vTab2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else{
            vTab2.setBackgroundColor(Color.parseColor("#FA2220"));
            vTab1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        viewPager.setCurrentItem(i);
    }
}
