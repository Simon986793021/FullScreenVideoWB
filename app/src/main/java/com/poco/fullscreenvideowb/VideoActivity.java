package com.poco.fullscreenvideowb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Simon
 * @time 2018/9/4
 * @note VideoActivity
 */
public class VideoActivity extends Activity implements FullScreenWebView.FullScreenListener{

    @BindView(R.id.fl_webview)
    FrameLayout frameLayout;
    @BindView(R.id.webview_container)
    FrameLayout webContainer;

    public static final String VIDEO_URL = "video_url";
    private FullScreenWebView webView;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        getLastIntent();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView == null)
            return;
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView == null)
            return;
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView == null)
            return;
        frameLayout.removeAllViews();
        webView.clearHistory();
        webView.clearCache(true);
        webView.destroy();
    }

    private void getLastIntent() {
        Intent intent = getIntent();
        if (intent == null)
            return;
        url = intent.getStringExtra(VIDEO_URL);
    }

    private void init() {
        webView = new FullScreenWebView(getApplicationContext());//防止内存泄露
        webView.setListener(this);
        frameLayout.addView(webView);
        webView.loadUrl(url);
    }

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {

    }

    @Override
    public void onHideCustomView(WebChromeClient.CustomViewCallback callback) {

    }
}
