package org.readbook;

import org.readbook.activity.ArticleListActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.readbook.activity.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabHost tabHost = getTabHost();
		tabHost.setup();
		tabHost.addTab(tabHost.newTabSpec("index").setIndicator("首页")
				.setContent(new Intent(this, ArticleListActivity.class)));
	}
}
