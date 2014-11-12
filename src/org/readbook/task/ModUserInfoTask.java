package org.readbook.task;

import org.readbook.entity.BaseRequest;
import org.readbook.entity.User;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.os.Handler;
import android.os.Message;

/**
#获取用户信息 Setting/getUserInfo
###功能
-------
1. 获取用户基本信息，QQ账号，和支付宝信息
 */
public class ModUserInfoTask extends BaseTask {

	public ModUserInfoTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			User user = User.getCurrentUser(User.class);
			user.setMobilePhoneNumber(super.request.getPhoneNumber());
			user.save();
			user.refresh();
			User u = User.getCurrentUser(User.class);
			if (u != null) {
				if (handler != null) {
					Message msg = handler.obtainMessage();
					msg.obj = u;
					handler.sendMessage(msg);
				}
				LogUtil.logD(
						LogUtil.TAG,
						"------GetUserInfoTask receiver-------"
								+ u.getUsername());
			} else {
				if (handler != null) {
					Message msg = handler.obtainMessage();
					msg.obj = "null object";
					msg.what = -1;
					handler.sendMessage(msg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(handler != null){
				Message msg = handler.obtainMessage();
				msg.obj = Constants.net_error;
				msg.what = -1;
				handler.sendMessage(msg);
			}
		}
		return null;
	}
}
