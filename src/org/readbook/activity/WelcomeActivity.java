package org.readbook.activity;

import org.readbook.R;
import org.readbook.clz.MyApplication;
import org.readbook.entity.BaseRequest;
import org.readbook.task.RegDeviceInfoTask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
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
			case 3:
				String deviceId = String.valueOf(msg.obj);
				MyApplication.getInstance().setDeviceID(deviceId);
				handler.sendEmptyMessage(1);
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_layout);
		MyApplication.getInstance().mLocationClient.start();
		if (TextUtils.isEmpty(MyApplication.getInstance().getDeviceID())) {
			registerDevice();
		} else {
			handler.sendEmptyMessageDelayed(1, 2000);
		}
		startMills = System.currentTimeMillis();
	}

	private void registerDevice() {
		BaseRequest request = new BaseRequest();
		RegDeviceInfoTask task = new RegDeviceInfoTask(request, handler);
		task.execute();
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
