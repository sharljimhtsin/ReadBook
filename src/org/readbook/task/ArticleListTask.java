package org.readbook.task;

import java.util.List;

import org.readbook.entity.Article;
import org.readbook.entity.BaseRequest;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.os.Handler;
import android.os.Message;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;

/**
 * #获取可做任务列表 Task/getAvailable

###功能
-------
1. 返回可做任务数，可赚总金额
2. 返回可做任务信息列表
3. 返回分享文本和app下载地址
4. 返回顶部信息
 */
public class ArticleListTask extends BaseTask {

	public ArticleListTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			AVQuery<Article> q = AVObject.getQuery(Article.class);
			q.setSkip((super.request.getPage() - 1)
					* super.request.getPageSize());
			q.setLimit(super.request.getPageSize());
			q.whereEqualTo("parentType", super.request.getDocTypeId());
			// q.whereEqualTo("parentCategory",
			// super.request.getDocCategoryId());
			List<Article> result = q.find();
			if (result != null) {
				if (result.size() > 0) {
					Message msg = new Message();
					msg.what = 0;
					msg.obj = result;
					handler.sendMessage(msg);
				} else {
					Message msg = handler.obtainMessage();
					msg.obj = "empty set";
					msg.what = -1;
					handler.sendMessage(msg);
				}
				LogUtil.logD(
						LogUtil.TAG,
						"------TaskListAvailableTask receiver-------"
								+ result.size());
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
