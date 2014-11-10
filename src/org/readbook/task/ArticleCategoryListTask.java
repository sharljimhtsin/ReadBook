package org.readbook.task;

import java.util.List;

import org.json.JSONObject;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.DocCategory;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * #获取可做任务列表 Task/getAvailable

###功能
-------
1. 返回可做任务数，可赚总金额
2. 返回可做任务信息列表
3. 返回分享文本和app下载地址
4. 返回顶部信息
 */
public class ArticleCategoryListTask extends BaseTask {

	public ArticleCategoryListTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			setRequestParams();
			String resultJson = httpHelper.httpPost(Constants.Host.getAvailable, map);
			LogUtil.logD(LogUtil.TAG, "------TaskListAvailableTask receiver-------" + resultJson);
			JSONObject dataObject = new JSONObject(resultJson);
			if (dataObject.getInt("status") == 0) {
				JSONObject jsonObject = dataObject.getJSONObject("data");
				String data = jsonObject.getString("data");
				if(data.length() > 5){
					Gson gson = new Gson();
					DocCategory response = gson.fromJson(jsonObject.toString(),
							new TypeToken<List<DocCategory>>() {
							}.getType());
					Message msg = new Message();
					msg.what = 0;
					Bundle bundle = new Bundle();
					bundle.putString("topmsg", response.getTitle());
					msg.obj = response.getDescription();
					msg.setData(bundle);
					handler.sendMessage(msg);
				}else{
					Message msg = handler.obtainMessage();
					msg.obj = dataObject.getString("info");
					msg.what = -1;
					handler.sendMessage(msg);
				}
			} else {
				Message msg = handler.obtainMessage();
				msg.obj = dataObject.getString("info");
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