package org.readbook.activity;

import org.readbook.clz.MyApplication;
import org.readbook.entity.BaseRequest;
import org.readbook.task.BaseTask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WelcomeActivity extends BaseActivity {
	
	private long startMills;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if(isFinishing()){
				removeCallbacksAndMessages(null);
				return;
			}
			switch (msg.what) {
			case 0:
				long endMills = System.currentTimeMillis();
				long diff = endMills - startMills;
				if(diff > 2000){
					handler.sendEmptyMessage(1);
				}else{
					handler.sendEmptyMessageDelayed(1, 2000-diff);
				}
				break;
			case 1:
				startActivity(new Intent(WelcomeActivity.this, ArticleTypeListActivity.class));
				finish();
				break;
			case -1:
				MyApplication.getInstance().showToast(WelcomeActivity.this, (String) msg.obj);
				handler.sendEmptyMessageDelayed(2, 2000);
				break;
			case 2:
				finish();
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(0);
		MyApplication.getInstance().mLocationClient.start();
		BaseRequest request = new BaseRequest();
		BaseTask indexTask = new BaseTask(request, handler);
		indexTask.execute();
		startMills = System.currentTimeMillis();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
}
