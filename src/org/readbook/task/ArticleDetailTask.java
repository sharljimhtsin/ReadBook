package org.readbook.task;

import org.json.JSONObject;
import org.readbook.entity.Article;
import org.readbook.entity.BaseRequest;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

/**
#获取任务详情 Task/getDetail
 */
public class ArticleDetailTask extends BaseTask {

	public ArticleDetailTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			setRequestParams();
			String resultJson = httpHelper.httpPost(Constants.Host.getDetail, map);
			LogUtil.logD(LogUtil.TAG, "------TaskDetailTask receiver-------" + resultJson);
			JSONObject dataObject = new JSONObject(resultJson);
			if (dataObject.getInt("status") == 0) {
				JSONObject jsonObject = dataObject.getJSONObject("data");
				Gson gson = new Gson();
				Article detail = gson.fromJson(jsonObject.toString(),
						Article.class);
				Message msg = new Message();
				msg.what = 10;
				msg.obj = detail;
				handler.sendMessage(msg);
			} else {
				Message msg = handler.obtainMessage();
				msg.obj = dataObject.getString("info");
				msg.what = 11;
				handler.sendMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Message msg = handler.obtainMessage();
			msg.obj = Constants.net_error;
			msg.what = 11;
			handler.sendMessage(msg);
		}
		return null;
	}
}
