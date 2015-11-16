package com.example.xiangnanzhang.freetaxcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends BaseActivity {
    private WebView wv_website;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        if(intent != null){
            mUrl = intent.getStringExtra("url");
        }
        findViewById();
        setListener();
    }

    @Override
    public void findViewById(){
        wv_website = (WebView)findViewById(R.id.wv_website);
        wv_website.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                return super.shouldOverrideUrlLoading(webView, url);
            }

        });
        wv_website.loadUrl(mUrl);
    }
    @Override
    public void setListener(){}

}
