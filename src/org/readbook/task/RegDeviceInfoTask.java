package org.readbook.task;

import org.json.JSONObject;
import org.readbook.entity.BaseRequest;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

/**
 * @author Administrator
 *
 */
public class RegDeviceInfoTask extends BaseTask {

	public RegDeviceInfoTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(String... params) {
		try {
			setRequestParams();
			String resultJson = httpHelper.httpGet(Constants.Host.regDevice);
			LogUtil.logD(LogUtil.TAG, "------RegDeviceInfoTask receiver-------"
					+ resultJson);
			JSONObject dataObject = new JSONObject(resultJson);
			if (dataObject.getInt("status") == 1) {
				String deviceId = dataObject.getJSONObject("data").getString(
						"deviceId");
				if (!TextUtils.isEmpty(deviceId)) {
					Message msg = new Message();
					msg.what = 3;
					msg.obj = deviceId;
					handler.sendMessage(msg);
				} else {
					Message msg = handler.obtainMessage();
					msg.obj = "empty set";
					msg.what = -1;
					handler.sendMessage(msg);
				}
			} else {
				Message msg = handler.obtainMessage();
				msg.obj = "set null";
				msg.what = -1;
				handler.sendMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Message msg = handler.obtainMessage();
			msg.obj = Constants.net_error;
			msg.what = -1;
			handler.sendMessage(msg);
		}
		return null;
	}
}
