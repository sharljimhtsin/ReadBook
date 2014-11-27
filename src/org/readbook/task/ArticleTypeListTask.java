package org.readbook.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.DocCategory;
import org.readbook.entity.DocType;
import org.readbook.entity.Response;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	protected Void doInBackground(Void... params) {
		try {
			// setRequestParams();
			// String resultJson = httpHelper.httpGet(Constants.Host.index,
			// super.map);

			// build json string for test START
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson g = gsonBuilder.create();
			DocCategory docCategory = new DocCategory(1, "xx", "xx", "xx", 1, 1);
			List<DocCategory> l = new ArrayList<DocCategory>();
			l.add(docCategory);
			DocType docType = new DocType(11, "", "", "", 1, 2, l);
			List<DocType> ll = new ArrayList<DocType>();
			ll.add(docType);
			Response response = new Response(1, ll);
			// build json string for test END

			String resultJson = g.toJson(response);
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
