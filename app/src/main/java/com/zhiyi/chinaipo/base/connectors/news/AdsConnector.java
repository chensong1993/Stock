package com.zhiyi.chinaipo.base.connectors.news;

import com.zhiyi.chinaipo.base.BasePresenter;
import com.zhiyi.chinaipo.base.BaseView;
import com.zhiyi.chinaipo.models.entity.AdverticeEntity;

import java.util.List;


public interface AdsConnector {

    interface View extends BaseView {
        void Ads(List<AdverticeEntity> list);

        void err();
    }

    interface Presenter extends BasePresenter<View> {

        void getAds(int page);
    }
}
