package org.readbook.task;

import java.util.List;

import org.json.JSONObject;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.DocCategory;
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
public class ArticleCategoryListTask extends BaseTask {

	public ArticleCategoryListTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			setRequestParams();
			String resultJson = httpHelper.httpGet(Constants.Host.index,
					super.map);
			LogUtil.logD(LogUtil.TAG,
					"------ArticleCategoryListTask receiver-------"
							+ resultJson);
			JSONObject dataObject = new JSONObject(resultJson);
			if (dataObject.getInt("status") == 1) {
				String data = dataObject.getString("data");
				Gson gson = new Gson();
				List<DocCategory> list = gson.fromJson(data,
						new TypeToken<List<DocCategory>>() {
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
