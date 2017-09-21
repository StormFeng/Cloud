package com.lida.cloud.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityNoticeDetail;
import com.lida.cloud.activity.ActivityProvince;
import com.lida.cloud.activity.ActivitySearchResult;
import com.lida.cloud.activity.ActivityShopDetail;
import com.lida.cloud.adapter.AdapterViewpager;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.BannerBean;
import com.lida.cloud.bean.NewsBean;
import com.lida.cloud.bean.PubBean;
import com.lida.cloud.bean.RecommendShopBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.InnerGridView;
import com.lida.cloud.widght.MarqueeTextView;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.Banner;
import com.midian.base.widget.PhotoPicker.PhotoPreview;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 * Created by WeiQingFeng on 2017/5/4.
 */

public class FragmentHome extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.marqueeTextView)
    MarqueeTextView marqueeTextView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;

    private List<String> images = new ArrayList<>();
    private List<String> news = new ArrayList<>();
    private List<String> newsID = new ArrayList<>();
    private List<View> views = new ArrayList<>();
    private List<ImageView> ivs = new ArrayList<>();

    private List<View> viewData = new ArrayList<>();

    private ArrayList<String> pics = new ArrayList<>();//页面中间5张图片

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, v);
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(Banner.CENTER);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        ivs.add(iv1);
        ivs.add(iv2);
        ivs.add(iv3);
        ivs.add(iv4);
        ivs.add(iv5);
        for (int i = 0; i < ivs.size(); i++) {
            final int finalI = i;
            ivs.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoPreview.builder()
                            .setPhotos(pics)
                            .setCurrentItem(finalI)
                            .setShowDeleteButton(false)
                            .start(_activity);
                }
            });
        }
        AppUtil.getCloudApiClient(ac).banner(ac.id, callback);
        AppUtil.getCloudApiClient(ac).news(callback);
        AppUtil.getCloudApiClient(ac).pub(callback);
        AppUtil.getCloudApiClient(ac).getRecommend(callback);
        return v;
    }

    private void initMarqueeView() {
        for (int i = 0; i < news.size(); i++) {
            TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.item_marqueeview, null);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            tv.setText(news.get(i));
            views.add(tv);
        }
        marqueeTextView.setViews(views);
        marqueeTextView.setOnItemClickListener(new MarqueeTextView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Bundle bundle = new Bundle();
                bundle.putString("newID", newsID.get(position));
                UIHelper.jump(_activity, ActivityNoticeDetail.class, bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initMagicIndicator(){
        CircleNavigator circleNavigator = new CircleNavigator(_activity);
        circleNavigator.setCircleCount(viewData.size());
        circleNavigator.setCircleColor(Color.RED);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                viewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    ApiCallback callback = new BaseApiCallback() {
        @Override
        public void onApiSuccess(NetResult res, String tag) {
            if (res.isOK()) {
                if ("banner".equals(tag)) {
                    BannerBean bannerBean = (BannerBean) res;
                    for (int i = 0; i < bannerBean.getData().size(); i++) {
                        images.add(Constant.IMGBASE + bannerBean.getData().get(i).getBnimage());
                    }
                    banner.setImages(images.toArray());
                }
                if ("news".equals(tag)) {
                    NewsBean newsBean = (NewsBean) res;
                    for (int i = 0; i < newsBean.getData().size(); i++) {
                        news.add(newsBean.getData().get(i).getNew_title());
                        newsID.add(newsBean.getData().get(i).getNewid());
                    }
                    initMarqueeView();
                }
                if ("pub".equals(tag)) {
                    PubBean pubBean = (PubBean) res;
                    for (int i = 0; i < pubBean.getData().size(); i++) {
                        pics.add(Constant.IMGBASE + pubBean.getData().get(i).getPubimage());
                        ac.setImage(ivs.get(i), Constant.IMGBASE + pubBean.getData().get(i).getPubimage());
                    }
                }
                if ("getRecommend".equals(tag)) {
                    final RecommendShopBean bean = (RecommendShopBean) res;
                    for (int i = 0; i < bean.getData().size(); i++) {
                        View view = LayoutInflater.from(_activity).inflate(R.layout.temp, null);
                        TextView tvName = (TextView) view.findViewById(R.id.tvName);
                        TextView tvNum = (TextView) view.findViewById(R.id.tvNum);
                        ImageView iv = (ImageView) view.findViewById(R.id.iv);
                        ac.setImage(iv,Constant.IMGSHOP + bean.getData().get(i).getSelimage());
                        tvName.setText(bean.getData().get(i).getSelshopname());
                        tvNum.setText(bean.getData().get(i).getSelback() + "%");
                        final int finalI = i;
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("id",bean.getData().get(finalI).getSelid());
                                UIHelper.jump(_activity, ActivityShopDetail.class, bundle);
                            }
                        });
                        viewData.add(view);
                    }
                    try {
                        initMagicIndicator();
                        viewPager.setAdapter(new AdapterViewpager(_activity, viewData));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    class GvAdapter extends BaseAdapter {

        private List<String> pics;

        public GvAdapter(List<String> pics) {
            this.pics = pics;
        }

        @Override
        public int getCount() {
            return pics.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(_activity).inflate(R.layout.item_homegridview, null);
            ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
            ac.setImage(iv, Constant.IMGSHOP + pics.get(position));
            return convertView;
        }
    }


    @OnClick({R.id.tvSearch, R.id.tvPosition})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                UIHelper.jump(_activity, ActivitySearchResult.class);
                break;
            case R.id.tvPosition:
                UIHelper.jump(_activity, ActivityProvince.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!"".equals(ac.getProperty("district"))) {
            tvPosition.setText(ac.getProperty("district"));
        }
    }
}
