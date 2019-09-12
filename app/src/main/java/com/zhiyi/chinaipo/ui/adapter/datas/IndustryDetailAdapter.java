package com.zhiyi.chinaipo.ui.adapter.datas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiyi.chinaipo.R;
import com.zhiyi.chinaipo.app.Constants;
import com.zhiyi.chinaipo.models.entity.StasIndustryEntity;
import com.zhiyi.chinaipo.ui.activity.tables.TableMultiActivity;
import com.zhiyi.chinaipo.util.NumericUtil;

import java.util.List;

//import com.zhiyi.chinaipo.ui.activity.slideabletable.TableMultiActivity;
//import com.zhiyi.chinaipo.util.ConversionUnitUtil;

/**
 * 数据中心的行情统计样式
 */
public class IndustryDetailAdapter extends RecyclerView.Adapter<IndustryDetailAdapter.GuapaiViewHolder> {
    private Context mContext;
    private List<StasIndustryEntity> mList;
    private int viewType;

    public IndustryDetailAdapter(Context context, List<StasIndustryEntity> mList1, int viewType) {
        this.mContext = context;
        this.mList = mList1;
        this.viewType = viewType;
    }

    @Override
    public IndustryDetailAdapter.GuapaiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_data_hangyetongji_details, parent, false);
        return new GuapaiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GuapaiViewHolder holder, int position) {
        holder.setItem(mList.get(position));
        holder.refreshView();
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TableMultiActivity.launch(new TableMultiActivity.Builder()
                        .setContext(mContext)
                        .setExtKey("industry")
                        .setTitle(mList.get(position).getIndu_name_2())
                        .setExtValue("" + mList.get(position).getIndu_code_2())
                        .setTabTitle(0)
                );
            //    Log.i("getIndu_code_2", ""+ mList.get(position).getIndu_code_2());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (viewType == Constants.STOCK_DATA_LIST_CONTENT_ALL) {
            return mList == null ? 0 : mList.size();
        }
        return mList.size() < 4 ? mList.size() : 3;
    }

    class GuapaiViewHolder extends RecyclerView.ViewHolder {

        TextView mName, mCompanies, mShares, mValue, mIncome, mIncomeChange, mProfit, mProfitChange, mDebt, mDebtRatio, mNetAsset, mNetAssetRatio, mMoneyFlow, mSingleYield;
        View mContainerView;
        StasIndustryEntity mItem;

        public GuapaiViewHolder(View v) {
            super(v);
            mContainerView = v;
            //行业名称
            mName = (TextView) v.findViewById(R.id.tv_industry_name);
            //多少家
            mCompanies = (TextView) v.findViewById(R.id.tv_industry_companies);
            //股本
            mShares = (TextView) v.findViewById(R.id.tv_data_guben_value);
            //总资产
            mValue = (TextView) v.findViewById(R.id.tv_data_zongzichan_value);
            //营收
            mIncome = (TextView) v.findViewById(R.id.tv_data_yingshou_value);
            //营收同比增长
            mIncomeChange = (TextView) v.findViewById(R.id.tv_data_yingshoutongbi_value);
            //净利润
            mProfit = (TextView) v.findViewById(R.id.tv_data_jinglirun_value);
            //净利润同比增长
            mProfitChange = (TextView) v.findViewById(R.id.tv_data_jingliruntongbi_value);
            //负债
            mDebt = v.findViewById(R.id.tv_data_fuzhai_value);
            //负债率
            mDebtRatio = v.findViewById(R.id.tv_data_fuzhai_value);
            //净资产
            mNetAsset = v.findViewById(R.id.tv_data_jingzichan_value);
            //净资产收益率
            mNetAssetRatio = v.findViewById(R.id.tv_data_jingzichanlv_value);
            //现金流
            mMoneyFlow = v.findViewById(R.id.tv_data_xianjinliu_value);
            //每股收益
            mSingleYield = v.findViewById(R.id.tv_data_meigushouyi_value);
        }

        public void setItem(StasIndustryEntity mItem) {
            this.mItem = mItem;
        }

        void refreshView() {
            mName.setText(mItem.getIndu_name_2());
            mCompanies.setText(String.valueOf(mItem.getTotal_company()) + "家");
            mShares.setText(NumericUtil.getStockValue(mItem.getTotal_shares()));
            mValue.setText(NumericUtil.getStockValue(mItem.getTotal_values()));
            mIncome.setText(NumericUtil.getStockValue(mItem.getTotal_incomes()));
            mIncomeChange.setText(NumericUtil.getPercentDirect(mItem.getIncome_change()));
            mProfit.setText(NumericUtil.getStockValue(mItem.getTotal_profit()));
            mProfitChange.setText(NumericUtil.getPercentDirect(mItem.getProfit_change()));
            mDebt.setText(NumericUtil.getStockValue(""));
            mDebtRatio.setText(NumericUtil.getStockValue(""));
            mNetAsset.setText(NumericUtil.getStockValue(""));
            mNetAssetRatio.setText(NumericUtil.getStockValue(""));
            mMoneyFlow.setText(NumericUtil.getStockValue(""));
            mSingleYield.setText(NumericUtil.getStockValue(""));

        }
    }

    public void add(List<StasIndustryEntity> mEntityList) {
        mList = mEntityList;
        notifyDataSetChanged();
    }

    public void addAll(List<StasIndustryEntity> mEntityList) {
        mList.addAll(mEntityList);
        notifyDataSetChanged();
    }
}
