/**
 * 
 */
package org.readbook.activity;

import org.readbook.R;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author Administrator
 *
 */
public class ArticleContentActivity extends BaseActivity {

	private WebView mWebView;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.readbook.activity.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUI();
		prepareWebview();
	}

	private void prepareWebview() {
		String url = getIntent().getStringExtra("url");
		mWebView.setWebViewClient(new WebViewClient());
		mWebView.loadUrl(url);
	}

	private void initUI() {
		setContentView(R.layout.activity_article_content);
		mWebView = (WebView) findViewById(R.id.webView_article_content);
	}

}
