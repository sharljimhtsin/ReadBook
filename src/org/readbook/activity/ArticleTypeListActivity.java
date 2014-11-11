/**
 * 
 */
package org.readbook.activity;

import org.readbook.entity.BaseRequest;
import org.readbook.task.ArticleTypeListTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

/**
 * @author Administrator
 *
 */
public class ArticleTypeListActivity extends BaseActivity {

	private Handler mHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.readbook.activity.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		BaseRequest request = new BaseRequest();
		ArticleTypeListTask task = new ArticleTypeListTask(request, mHandler);
		task.execute();
	}

	private void init() {
		mHandler = new Handler(new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				switch (msg.what) {
				case 1:

					break;

				default:
					break;
				}
				return false;
			}
		});
	}

}
