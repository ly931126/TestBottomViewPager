package com.kelin.mvvmlight.bindingadapter.webview;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by kelin on 16-4-29.
 */
public class ViewBindingAdapter {
    @BindingAdapter({"render"})
    public static void loadHtml(WebView webView, final String html) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        }
    }

    @BindingAdapter({"loadHtml"})
    public static void loadHtmlContent(WebView webView, final String url) {
        if (!TextUtils.isEmpty(url)) {
            WebSettings webSettings = webView.getSettings();
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setBlockNetworkImage(false);
            webSettings.setAllowFileAccess(true);
            webSettings.setSupportZoom(true);
            webView.setBackgroundColor(Color.TRANSPARENT);
            webView.setHorizontalScrollBarEnabled(false);
            webView.setVerticalScrollBarEnabled(true);
            webView.loadUrl(url);
        }
    }
}
