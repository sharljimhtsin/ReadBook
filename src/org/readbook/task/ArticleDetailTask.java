package org.readbook.task;

import org.readbook.entity.Article;
import org.readbook.entity.BaseRequest;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.os.Handler;
import android.os.Message;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

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
			AVQuery<Article> q = AVObject.getQuery(Article.class);
			Article result = q.get(super.request.getArticleId());
			if (result != null) {
				Message msg = new Message();
				msg.what = 0;
				msg.obj = result;
				handler.sendMessage(msg);
				LogUtil.logD(
						LogUtil.TAG,
						"------TaskDetailTask receiver-------"
								+ result.toString());
			} else {
				Message msg = handler.obtainMessage();
				msg.obj = "object null";
				msg.what = -1;
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
