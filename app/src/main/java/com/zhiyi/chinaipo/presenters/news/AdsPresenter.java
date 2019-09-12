package com.zhiyi.chinaipo.presenters.news;

import com.zhiyi.chinaipo.base.RxPresenter;
import com.zhiyi.chinaipo.base.connectors.news.AdsConnector;
import com.zhiyi.chinaipo.models.DataManager;
import com.zhiyi.chinaipo.models.entity.AdverticeEntity;
import com.zhiyi.chinaipo.models.entity.ApiResponse;
import com.zhiyi.chinaipo.ui.widget.CommonSubscriber;
import com.zhiyi.chinaipo.util.RxUtils;

import java.util.List;

import javax.inject.Inject;

public class AdsPresenter extends RxPresenter<AdsConnector.View> implements AdsConnector.Presenter {

    private DataManager mDataManager;

    @Inject
    public AdsPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getAds(int page) {
        addSubscribe(mDataManager.getAds(page)
                .compose(RxUtils.<ApiResponse<List<AdverticeEntity>>>rxSchedulerHelper())
                .compose(RxUtils.<List<AdverticeEntity>>handleResults())
                .subscribeWith(new CommonSubscriber<List<AdverticeEntity>>(mView) {
                    @Override
                    public void onNext(List<AdverticeEntity> AdsItem) {
                        mView.Ads(AdsItem);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.err();
                        e.printStackTrace();
                    }
                })
        );
    }
}
