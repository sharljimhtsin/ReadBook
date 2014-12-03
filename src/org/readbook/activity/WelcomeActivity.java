package org.readbook.activity;

import org.readbook.R;
import org.readbook.clz.MyApplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import cn.jpush.android.api.JPushInterface;

public class WelcomeActivity extends BaseActivity {

	private long startMills;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (isFinishing()) {
				removeCallbacksAndMessages(null);
				return;
			}
			switch (msg.what) {
			case 0:
				long endMills = System.currentTimeMillis();
				long diff = endMills - startMills;
				if (diff > 2000) {
					handler.sendEmptyMessage(1);
				} else {
					handler.sendEmptyMessageDelayed(1, 2000 - diff);
				}
				break;
			case 1:
				startActivity(new Intent(WelcomeActivity.this,
						MainActivity.class));
				finish();
				break;
			case -1:
				MyApplication.getInstance().showToast(WelcomeActivity.this,
						(String) msg.obj);
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
		setContentView(R.layout.start_layout);
		MyApplication.getInstance().mLocationClient.start();
		startMills = System.currentTimeMillis();
		handler.sendEmptyMessageDelayed(1, 2000);
	}

	@Override
	public void onResume() {
		super.onResume();
		JPushInterface.onResume(mContext);
	}

	@Override
	public void onPause() {
		super.onPause();
		JPushInterface.onPause(mContext);
	}
}
