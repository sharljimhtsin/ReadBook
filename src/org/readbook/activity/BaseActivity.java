package org.readbook.activity;

import java.util.Timer;
import java.util.TimerTask;

import org.readbook.R;
import org.readbook.utils.ActivityStackManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

public class BaseActivity extends Activity {

	public int screenWidth;
	protected int screenHeight;
	public ImageLoader imageLoader = ImageLoader.getInstance();

	private Timer timer = null;
	private TimerTask timeTask = null;
	private boolean isExit = false; // 标记是否要退出
	private ProgressDialog progressDialog;
	public Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
//		getWindow().setBackgroundDrawable(null);
//		getWindow().setFormat(PixelFormat.RGBA_8888);
		//设置全屏  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		ActivityStackManager.getInstance().pushActivity(this);
		initScreen();
	}

	protected void initTitleView(boolean backIsVisible, String title) {
		ImageButton imageButton = (ImageButton) findViewById(R.id.back_btn);
		if (!backIsVisible) {
			imageButton.setVisibility(View.INVISIBLE);
		} else {
			imageButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					onBack();
				}
			});
		}
		TextView textView = (TextView) findViewById(R.id.title);
		textView.setText(title);
	}
	
	protected void initRightButton(String text) {
		Button button = (Button) findViewById(R.id.right_btn);
		if(TextUtils.isEmpty(text)){
			button.setVisibility(View.INVISIBLE);
		}else{
			button.setVisibility(View.VISIBLE);
			button.setText(text);
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					onRightButtonClick();
				}
			});
		}
	}
	
	protected void onBack() {
		finish();
	}
	
	protected void onRightButtonClick() {
		
	}

	public void onResume() {
		super.onResume();
	}
	
	public void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityStackManager.getInstance().popActivity(this);
	}
	
	public void closeKeyBoard() {
		if(hasWindowFocus()){
			((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);	
		}
	}
	
	/**
	 * 开始加载数据
	 */
	public void startLoading(){
		View errorView = findViewById(R.id.error_layout);
		errorView.setVisibility(View.GONE);
		View loadingView = findViewById(R.id.loadingLayout);
		loadingView.setVisibility(View.VISIBLE);
	}
	
	public void stopLoading(){
		View loadingView = findViewById(R.id.loadingLayout);
		loadingView.setVisibility(View.GONE);
		View errorView = findViewById(R.id.error_layout);
		errorView.setVisibility(View.GONE);
	}
	
	/**
	 * 加载数据结束
	 */
	public void stopLoading(String errorMsg, String buttonStr, OnClickListener listener) {
		View errorView = findViewById(R.id.error_layout);
		errorView.setVisibility(View.VISIBLE);
		View loadingView = findViewById(R.id.loadingLayout);
		loadingView.setVisibility(View.GONE);
		TextView errorTextView = (TextView) findViewById(R.id.error_message);
		errorTextView.setText(errorMsg);
		Button button = (Button) findViewById(R.id.button);
		if(listener != null){
			button.setVisibility(View.VISIBLE);
			button.setText(buttonStr);
			button.setOnClickListener(listener);
		}else{
			button.setVisibility(View.GONE);
		}
	}
	
	public void showProgressDialog() {
		if(progressDialog == null){
			progressDialog = new ProgressDialog(this, R.style.Dialog_Theme);
			progressDialog.setMessage("数据加载中...");
		}
		progressDialog.show();
	}
	
	public void showProgressDialog(String msg) {
		if(progressDialog == null){
			progressDialog = new ProgressDialog(this, R.style.Dialog_Theme);
		}
		progressDialog.setMessage(msg);
		progressDialog.show();
	}
	
	public void showProgressDialog(String msg, boolean cancelable) {
		if(progressDialog == null){
			progressDialog = new ProgressDialog(this, R.style.Dialog_Theme);
		}
		progressDialog.setCancelable(cancelable);
		progressDialog.setMessage(msg);
		progressDialog.show();
	}
	
	public void dismissProgressDialog() {
		if(progressDialog != null && progressDialog.isShowing()){
			progressDialog.dismiss();
		}
	}
	
	public void initTimer(){
		timer = new Timer();
	}
	
	public void onExit() {
		if (isExit) {
			finish();
		} else {
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			isExit = true;
			timeTask = new TimerTask() {

				@Override
				public void run() {
					isExit = false;
				}
			};
			timer.schedule(timeTask, 2000);
		}
	}
	
	private void initScreen() {
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenWidth = metric.widthPixels; // 屏幕宽度（像素）
		screenHeight = metric.heightPixels; // 屏幕高度（像素）
		// PengSettings.density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
	}
}
