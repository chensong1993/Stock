package com.zhiyi.chinaipo.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zhiyi.chinaipo.R;
import com.zhiyi.chinaipo.base.BaseActivity;
import com.zhiyi.chinaipo.base.connectors.news.AdsConnector;
import com.zhiyi.chinaipo.models.entity.AdverticeEntity;
import com.zhiyi.chinaipo.presenters.news.AdsPresenter;
import com.zhiyi.chinaipo.ui.activity.misc.WebAdActivity;
import com.zhiyi.chinaipo.ui.widget.ad.AdsUiFresco;
import com.zhiyi.chinaipo.util.LogUtil;
import com.zhiyi.chinaipo.util.RepeatCllickUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

public class AdActivity extends BaseActivity<AdsPresenter>{
    String mAdDetail = "http://api.chinaipo.com/openscreen";
    String mAdImage = "http://api.chinaipo.com/Default-Ad.jpg";
    private int progress = 6;
    @BindView(R.id.ad_view)
    ImageView mAd;
    boolean click = true;
    ThreadPoolExecutor executor;
    ExecutorService executorService;
    @BindView(R.id.time_progress)
    TextView mAdBottons;
    // public static AdsUiFresco.UiFrescos frescos;
   // AdsUiFresco adsUiFresco;

    @Override
    protected int getLayout() {
        return R.layout.activity_ad;
    }


    @Override
    protected void initEventAndData() {
        //  frescos = this;
        //关闭侧滑退出
        setSwipeBackEnable(false);
        // mPresenter.getAds();
       /* SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String str = format.format(date);*/
        //adsTime = new AdsTime();
        // ThreadFactory threadFactory = new CustomThreadFactoryBuilder().setNamePrefix("Ad-Thread").setPriority(Thread.MAX_PRIORITY).build();
        // executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), threadFactory);
        // myHandler = new MyHandler(this);
        // AdsUiFresco adsUiFresco = new AdsUiFresco();
        UiFresco(mAdImage, mAd);
        // adsUiFresco.UiFresco(AdActivity.this, R.drawable.ic_welcome, mAd);
        //AdOnclick();
    }

    //广告跳转
  //  @Override
    public void onShowAds() {
        if (progress >= 1) {
            progress -= 1;
            if (click) {
                if (progress == 1) {
                    startActivity(new Intent(AdActivity.this, MainActivity.class));
                    finish();
                }
                mAdBottons.setText(progress + "  跳过");
            }

        }

    }

    @OnClick(R.id.time_progress)
    void AdOnclick() {

        if (!RepeatCllickUtil.isFastDoubleClick()) {
            click = false;
            startActivity(new Intent(AdActivity.this, MainActivity.class));
            finish();
        }

    }

    @OnClick(R.id.ad_view)
    void adDetail() {
        if (!RepeatCllickUtil.isFastDoubleClick()) {
            if (mAdDetail != null) {
                click = false;
                WebAdActivity.launch(new WebAdActivity.Builder()
                        .setContext(this)
                        .setTitle("推荐")
                        .setUrl(mAdDetail)
                );
                finish();
            }

        }
    }


    public void UiFresco(String url, ImageView imageView) {
        Glide.with(this).load(url).fitCenter().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).crossFade().listener(new RequestListener<String, GlideDrawable>() {

            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                startActivity(new Intent(AdActivity.this, MainActivity.class));
                finish();
                return false;
            }

            @SuppressLint("CheckResult")
            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Observable.interval(0, 1, TimeUnit.SECONDS)
                        .take(5)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                onShowAds();
                            }
                        });

                return false;
            }


        }).into(imageView);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
