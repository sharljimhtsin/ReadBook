/**
 * 
 */
package org.readbook.activity;

import org.readbook.entity.BaseRequest;
import org.readbook.task.ArticleListTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

/**
 * @author Administrator
 *
 */
public class ArticleListActivity extends BaseActivity {

	private Handler mHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.readbook.activity.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int typeId = getIntent().getIntExtra("typeId", 0);
		init();
		BaseRequest request = new BaseRequest();
		request.setDocTypeId(typeId);
		ArticleListTask task = new ArticleListTask(request, mHandler);
		task.execute();
		// startLoading();
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
