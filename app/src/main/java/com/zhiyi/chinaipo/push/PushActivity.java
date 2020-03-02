package com.zhiyi.chinaipo.push;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.umeng.message.UmengNotifyClickActivity;
import com.umeng.message.entity.UMessage;
import com.zhiyi.chinaipo.R;
import com.zhiyi.chinaipo.ui.activity.misc.WebActivity;
import com.zhiyi.chinaipo.util.LogUtil;

import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

public class PushActivity extends UmengNotifyClickActivity {
    private static String TAG = PushActivity.class.getName();
    // private TextView mipushTextView;
    private String url;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mipush);
        Bundle bun = getIntent().getExtras();

        if (bun != null) {

            Set<String> keySet = bun.keySet();
            for (String key : keySet) {
                url = bun.getString(key);
            }
            WebActivity.launch(new WebActivity.Builder()
                    .setContext(PushActivity.this)
                    .setTitle("新闻")
                    .setType(1)
                    .setUrl(url));
            finish();
            LogUtil.i(TAG, "onCreate: " + url);
        }


    }

    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);
        String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        if (!TextUtils.isEmpty(body)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject object = new JSONObject(body);
                        UMessage msg = new UMessage(object);
                        for (Map.Entry<String, String> entry : msg.extra.entrySet()) {
                            String key = entry.getKey();
                            url = entry.getValue();
                            LogUtil.i(TAG, url);

                        }

                        WebActivity.launch(new WebActivity.Builder()
                                .setContext(PushActivity.this)
                                .setTitle("新闻")
                                .setType(1)
                                .setUrl(url));
//                            Intent intent = new Intent(MipushTestActivity.this, WebActivity.class);
//                            intent.putExtra(Constants.GOTO_WEB_URL, value);
//                            startActivity(intent);
                        finish();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });
        }

    }
}