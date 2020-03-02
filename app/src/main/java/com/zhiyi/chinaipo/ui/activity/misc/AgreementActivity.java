package com.zhiyi.chinaipo.ui.activity.misc;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.umeng.analytics.MobclickAgent;
import com.zhiyi.chinaipo.R;
import com.zhiyi.chinaipo.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author: chensong
 * @Date: 2020/1/21 11:51
 * 用户协议
 */
public class AgreementActivity extends SimpleActivity {

    @BindView(R.id.ll_privacy)
    LinearLayout mLlPrivacy;
    AgentWeb mAgentWeb;

    @Override
    protected int getLayout() {
        return R.layout.activity_agreenment;
    }

    @Override
    protected void initEventAndData() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLlPrivacy, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(ContextCompat.getColor(this, R.color.colorPrimary), 2)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go("http://www.chinaipo.com/docs/agreement.html");
    }

    @OnClick({R.id.backKey_TermsOfServiceFragment})
    void click(View v) {
        switch (v.getId()) {
            case R.id.backKey_TermsOfServiceFragment:
                finish();
                break;
        }
    }

    private WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //     LogUtil.i("Info", "BaseWebActivity onPageStarted");
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            LogUtil.i("Info","progress:"+newProgress);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
