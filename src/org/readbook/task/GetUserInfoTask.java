package org.readbook.task;

import org.json.JSONObject;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.User;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

/**
 * @author Administrator
 *
 */
public class GetUserInfoTask extends BaseTask {

	public GetUserInfoTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			setRequestParams();
			String resultJson = httpHelper.httpGet(Constants.Host.index,
					super.map);
			LogUtil.logD(LogUtil.TAG, "------GetUserInfoTask receiver-------"
					+ resultJson);
			JSONObject dataObject = new JSONObject(resultJson);
			if (dataObject.getInt("status") == 1) {
				String data = dataObject.getString("data");
				Gson gson = new Gson();
				User list = gson.fromJson(data, User.class);
				Message msg = new Message();
				msg.what = 0;
				msg.obj = list;
				handler.sendMessage(msg);
			} else {
				Message msg = handler.obtainMessage();
				msg.obj = "object null";
				msg.what = -1;
				handler.sendMessage(msg);
			}
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
