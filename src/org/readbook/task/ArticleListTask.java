package org.readbook.task;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.readbook.entity.Article;
import org.readbook.entity.BaseRequest;
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
public class ArticleListTask extends BaseTask {

	public ArticleListTask(BaseRequest request, Handler handler) {
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
			Article article = new Article(0, "aaaaaaaa", "bbbbbbb",
					"cccccccccc", "dddddddddd", "eeeeeeeeeee", "fffffffff",
					"gggggggggggggg", 2, 3, 4, 3, 2, 1, 3, null, null);
			List<Article> l = new ArrayList<Article>();
			l.add(article);
			l.add(article);
			l.add(article);
			l.add(article);
			l.add(article);
			l.add(article);
			l.add(article);
			Response response = new Response(1, l);
			String resultJson = g.toJson(response);
			// build json string for test END

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
					msg.what = 1;
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
