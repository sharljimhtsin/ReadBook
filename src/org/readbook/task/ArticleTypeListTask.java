package org.readbook.task;

import java.util.List;

import org.json.JSONObject;
import org.readbook.activity.MainActivity;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.DocType;
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
public class ArticleTypeListTask extends BaseTask {

	public ArticleTypeListTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(String... params) {
		try {
			setRequestParams();
			String resultJson = httpHelper.httpGet(Constants.Host.typeList);
			LogUtil.logD(LogUtil.TAG,
					"------ArticleTypeListTask receiver-------" + resultJson);
			JSONObject dataObject = new JSONObject(resultJson);
			if (dataObject.getInt("status") == 1) {
				String data = dataObject.getString("data");
				Gson gson = new Gson();
				List<DocType> list = gson.fromJson(data,
						new TypeToken<List<DocType>>() {
						}.getType());
				if (list.size() > 0) {
					Message msg = new Message();
					msg.what = MainActivity.Initial_Menu;
					msg.obj = list;
					handler.sendMessage(msg);
				} else {
					Message msg = handler.obtainMessage();
					msg.obj = "empty set";
					msg.what = MainActivity.No_Data;
					handler.sendMessage(msg);
				}
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
