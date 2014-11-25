package org.readbook.task;

import java.util.List;

import org.json.JSONObject;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.DocType;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Handler;
import android.os.Message;

/**
 * #获取可做任务列表 Task/getAvailable
 * 
 * ###功能 ------- 1. 返回可做任务数，可赚总金额 2. 返回可做任务信息列表 3. 返回分享文本和app下载地址 4. 返回顶部信息
 */
public class ArticleTypeListTask extends BaseTask {

	public ArticleTypeListTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			setRequestParams();
			String resultJson = httpHelper.httpGet(Constants.Host.index,
					super.map);
			LogUtil.logD(LogUtil.TAG,
					"------TaskListAvailableTask receiver-------" + resultJson);
			JSONObject dataObject = new JSONObject(resultJson);
			if (dataObject.getInt("status") == 1) {
				String data = dataObject.getString("data");
				Gson gson = new Gson();
				List<DocType> list = gson.fromJson(data,
						new TypeToken<List<DocType>>() {
						}.getType());
				if (list.size() > 0) {
					Message msg = new Message();
					msg.what = 0;
					msg.obj = list;
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
