package org.readbook.task;

import org.readbook.entity.BaseRequest;
import org.readbook.res.Constants;

import android.os.Handler;
import android.os.Message;

/**
 * @author Administrator
 *
 */
public class RegUserInfoTask extends BaseTask {

	public RegUserInfoTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(String... params) {
		try {
			// User user = new User();
			// user.setAge(super.request.getAge());
			// user.signUp();
			// User u = User.getCurrentUser(User.class);
			// if (u != null) {
			// if (handler != null) {
			// Message msg = handler.obtainMessage();
			// msg.obj = u;
			// handler.sendMessage(msg);
			// }
			// LogUtil.logD(
			// LogUtil.TAG,
			// "------GetUserInfoTask receiver-------"
			// + u.getUsername());
			// } else {
			// if (handler != null) {
			// Message msg = handler.obtainMessage();
			// msg.obj = "null object";
			// msg.what = -1;
			// handler.sendMessage(msg);
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
			if (handler != null) {
				Message msg = handler.obtainMessage();
				msg.obj = Constants.net_error;
				msg.what = -1;
				handler.sendMessage(msg);
			}
		}
		return null;
	}
}
