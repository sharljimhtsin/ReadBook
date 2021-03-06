package org.readbook.task;

import java.util.List;

import org.json.JSONObject;
import org.readbook.activity.MainActivity;
import org.readbook.entity.Article;
import org.readbook.entity.BaseRequest;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Administrator
 *
 */
public class ArticleListTask extends BaseTask {

	public ArticleListTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(String... params) {
		try {
			setRequestParams();
			String resultJson = httpHelper.httpGet(Constants.Host.articleList);
			LogUtil.logD(LogUtil.TAG, "------ArticleListTask receiver-------"
					+ resultJson);
			JSONObject dataObject = new JSONObject(resultJson);
			if (dataObject.getInt("status") == 1) {
				String data = dataObject.getString("data");
				Gson gson = new Gson();
				List<Article> list = gson.fromJson(data,
						new TypeToken<List<Article>>() {
						}.getType());
				if (list.size() > 0) {
					Message msg = new Message();
					if ("0".equals(params[0])) {
						msg.what = MainActivity.Initial_List;
					} else if ("1".equals(params[0])) {
						msg.what = MainActivity.Do_LoadMore;
					} else {
						msg.what = MainActivity.Do_Refresh;
					}
					msg.obj = list;
					handler.sendMessage(msg);
				} else {
					Message msg = handler.obtainMessage();
					msg.obj = "empty set";
					msg.what = MainActivity.No_Data;
					handler.sendMessage(msg);
				}
			} else if (dataObject.getInt("status") == 2) {
				String info = dataObject.getString("info");
				Message msg = handler.obtainMessage();
				msg.obj = info;
				msg.what = MainActivity.Do_NoMoreData;
				handler.sendMessage(msg);
			} else {
				String info = dataObject.getString("info");
				Message msg = handler.obtainMessage();
				msg.obj = info;
				msg.what = MainActivity.Logic_Error;
				handler.sendMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Message msg = handler.obtainMessage();
			msg.obj = Constants.net_error;
			msg.what = MainActivity.Net_Error;
			handler.sendMessage(msg);
		}
		return null;
	}
}
