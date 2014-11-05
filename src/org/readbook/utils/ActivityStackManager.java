/*
* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.readbook.utils;
import java.util.Stack;

import android.app.Activity;

/**
 * 视图切换处理栈
 * 
 * @author DingMaolin
 */
public class ActivityStackManager {

	private static Stack<Activity> activityStack;
	private static ActivityStackManager instance;

	private ActivityStackManager() {
	}

	public static ActivityStackManager getInstance() {
		if (instance == null) {
			instance = new ActivityStackManager();
			activityStack = new Stack<Activity>();
		}
		return instance;
	}

	// 退出栈顶Activity
	public void popActivity(Activity activity) {
		if (activity != null) {
			// 在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	public void popActivity(int num) {
		for (int i = 0; i < num; i++) {
			Activity activity = currentActivity();
			popActivity(activity);
		}
	}

	// 获得当前栈顶Activity
	public Activity currentActivity() {
		Activity activity = null;
		if (!activityStack.empty())
			activity = activityStack.lastElement();
		return activity;
	}

	// 将当前Activity推入栈中
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	// 退出栈中所有Activity
	public void popAllActivityExceptOne(Class<?> cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				// break;
				activityStack.remove(activity);
				continue;
			}
			popActivity(activity);
		}
	}

	/**
	 * 退出应用程序
	 */
	public void exitActivity() {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			popActivity(activity);
		}

		// Intent intent = new Intent();
		// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// intent.setClass(context, myClass);
		// context.startActivity(intent);
	}
}
