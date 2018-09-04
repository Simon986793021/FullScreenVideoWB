package com.poco.fullscreenvideowb;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author Simon
 * @time 2018/9/4
 * @note full screen webview for video
 */

public class FullScreenWebView extends BaseWebView {
    private FullScreenListener listener;
    private WebChromeClient.CustomViewCallback customViewCallback;

    public FullScreenWebView(Context context) {
        super(context);
        initWebView();
    }


    public FullScreenWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebView();
    }

    public FullScreenWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebView();
    }

    public void setListener(FullScreenListener listener) {
        this.listener = listener;
    }

    private void initWebView() {
        setWebViewClient(new WebViewClient() {

            //返回true，表示自己处理，返回false，表示webview处理
            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //部分机型会返回对应Intent用来打开对应APP，造成无法打开对应网页问题，此处屏蔽
                Log.i("SIMON", "shouldOverrideUrlLoading: " + request.isRedirect() + request.getUrl().toString());
                if (!request.getUrl().toString().contains("http")) {
                    return true;
                }
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }
        });


        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
                Log.i("SIMON", "onShowCustomView: ");
                customViewCallback = callback;
                if (listener == null)
                    return;
                listener.onShowCustomView(view, callback);
            }

            @Override
            public void onHideCustomView() {
                super.onHideCustomView();
                if (listener == null)
                    return;
                listener.onHideCustomView(customViewCallback);
                Log.i("SIMON", "onHideCustomView: ");
            }
        });

    }


    public interface FullScreenListener {
        /**
         * @param view
         * @param callback
         */
        void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);

        /**
         * @param callback
         */
        void onHideCustomView(WebChromeClient.CustomViewCallback callback);
    }
}
