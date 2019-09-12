package com.zhiyi.chinaipo.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding3.view.RxView;
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
import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class AdActivity extends BaseActivity<AdsPresenter> implements AdsConnector.View, AdsUiFresco.UiFrescos {
    private int progress = 6;
    @BindView(R.id.ad_view)
    ImageView mAd;
    //    @BindView(R.id.img_ad)
//    ImageView mImgAds;
    boolean click = true;
    ThreadPoolExecutor executor;
    ExecutorService executorService;
    //AdsTime adsTime;
    String mAdDetail = "http://api.chinaipo.com/openscreen";
    String mAdImage="http://api.chinaipo.com/Default-Ad.jpg";
    // MyHandler myHandler;
    @BindView(R.id.time_progress)
    TextView mAdBottons;
    public static AdsUiFresco.UiFrescos frescos;
    AdsUiFresco adsUiFresco;

    @Override
    protected int getLayout() {
        return R.layout.activity_ad;
    }


    @Override
    protected void initEventAndData() {
        frescos = this;
        //关闭侧滑退出
        setSwipeBackEnable(false);
        // mPresenter.getAds(1);
       /* SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String str = format.format(date);*/
        //adsTime = new AdsTime();
        // ThreadFactory threadFactory = new CustomThreadFactoryBuilder().setNamePrefix("Ad-Thread").setPriority(Thread.MAX_PRIORITY).build();
        // executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), threadFactory);
        // myHandler = new MyHandler(this);
        adsUiFresco = new AdsUiFresco();
        adsUiFresco.UiFresco(AdActivity.this,mAdImage, mAd);
        //AdOnclick();
    }

    //广告跳转
    @Override
    public void onShowAds() {
        if (progress >= 1) {
            progress -= 1;
//            Message message = new Message();
//            message.what = progress;
            Log.i("accept", progress + "");
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
        click = false;
        startActivity(new Intent(AdActivity.this, MainActivity.class));
        finish();
//        RxView.clicks(mAdBottons).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Consumer<Unit>() {
//            @Override
//            public void accept(Unit unit) throws Exception {
//
//            }
//        });

    }

    @OnClick(R.id.ad_view)
    void adDetail() {
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

    @Override
    public void Ads(List<AdverticeEntity> list) {
//        mAdDetail = list.get(0).getText();
//        mAdImage = list.get(0).getImg();
//        if (list.size() > 0) {
//            LogUtil.i("132", "1");
//            adsUiFresco.UiFresco(AdActivity.this, R.drawable.ic_welcome, mAd);
//        }

    }


    @Override
    public void err() {
        LogUtil.i("132", "123");
    }
    //    static class MyHandler extends Handler {
//
//        private WeakReference<AdActivity> weakReference;
//        private TextView mAdBottons;
//
//        public MyHandler(AdActivity handlerMemoryActivity) {
//            weakReference = new WeakReference<AdActivity>(handlerMemoryActivity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            AdActivity adActivity = weakReference.get();
//            mAdBottons = adActivity.findViewById(R.id.time_progress);
//            mAdBottons.setText(msg.what + "  跳过");
//
//        }


//    }


//    class AdsTime implements Runnable {
//
//        @Override
//        public void run() {
//            while (progress >= 1) {
//                // mAdProgress.setCurrentProgress(progress);
//
//                progress -= 1;
//                Message message = new Message();
//                message.what = progress;
//                if (progress > 0) {
//                   // myHandler.sendMessage(message);
//                }
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//                if (click) {
//                    if (progress == 1) {
//                        startActivity(new Intent(AdActivity.this, MainActivity.class));
//                        finish();
//                    }
//                }
//
//            }
//
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
