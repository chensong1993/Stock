package com.zhiyi.chinaipo.base.connectors.datas;


import com.zhiyi.chinaipo.base.BasePresenter;
import com.zhiyi.chinaipo.base.BaseView;
import com.zhiyi.chinaipo.models.entity.IndexSCEntity;
import com.zhiyi.chinaipo.models.entity.StasAreaEntity;
import com.zhiyi.chinaipo.models.entity.StasIndustryEntity;
import com.zhiyi.chinaipo.models.entity.StasOrgsEntity;
import com.zhiyi.chinaipo.models.entity.StockPriceEntity;

import java.util.List;

public interface IndustryConnector {

    interface View extends BaseView {

        void showIndustry(List<StasIndustryEntity> topIndustry);
        void noContent();
    }

    interface Presenter extends BasePresenter<View> {

        void getIndustries(int pageOffset);

    }
}
