package com.sselva.test.rssfeed.ui

import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.webkit.WebView
import android.webkit.WebViewClient
import com.sselva.test.rssfeed.R.layout.activity_webview
import kotlinx.android.synthetic.main.activity_webview.*


class WebViewActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_webview)

        supportActionBar!!.hide()

        mSwipeRefreshLayout.setOnRefreshListener(this)

        // Use this to fit page to webview space
        //mWebView.getSettings().setLoadWithOverviewMode(true);
        //mWebView.getSettings().setUseWideViewPort(true);
        //mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.webViewClient = MyWebViewClient()

        var url: String? = null
        val intent = intent
        if (intent != null) {
            url = intent.getStringExtra(EXTRA_WEB_URL)
        }
        mWebView.loadUrl(url)
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            mToolbarTitle.text = view.title
            if (mSwipeRefreshLayout.isRefreshing) {
                mSwipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onRefresh() {
        mWebView.reload()
    }

    override fun onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {

        val EXTRA_WEB_URL = "extra_web_url"
    }
}
