package org.readbook.activity;

import java.util.List;

import org.readbook.R;
import org.readbook.entity.Article;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.DocType;
import org.readbook.task.ArticleListTask;
import org.readbook.task.ArticleTypeListTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private HorizontalScrollView mTypeScrollView;
	private HorizontalScrollView mCategoryScrollView;
	private ViewPager mViewPager;
	private Handler mHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.readbook.activity.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initUI();
		prepareHandler();
		queryMenu();
	}

	private void queryMenu() {
		BaseRequest request = new BaseRequest();
		ArticleTypeListTask task = new ArticleTypeListTask(request, mHandler);
		task.execute();
	}

	private void queryList() {
		BaseRequest request = new BaseRequest();
		ArticleListTask task = new ArticleListTask(request, mHandler);
		task.execute();
	}

	private void prepareHandler() {
		mHandler = new Handler(new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				switch (msg.what) {
				case -1:

					break;
				case 0:
					List<DocType> list = (List<DocType>) msg.obj;
					bindMenu(list);
					queryList();
					break;
				case 1:
					List<Article> list2 = (List<Article>) msg.obj;
					bindList(list2);
					break;

				default:
					break;
				}
				return false;
			}
		});
	}

	private void initUI() {
		setContentView(R.layout.activity_main);
		mTypeScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView_typeList);
		mCategoryScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView_categoryList);
		mViewPager = (ViewPager) findViewById(R.id.ViewPager_articleList);
	}

	private void bindMenu(List<DocType> list) {
		LinearLayout layout = (LinearLayout) mTypeScrollView.getChildAt(0);
		for (DocType docType : list) {
			TextView textView = new TextView(mContext);
			textView.setText(docType.getTitle());
			layout.addView(layout);
		}
		// build submenu with default first one
		layout = (LinearLayout) mCategoryScrollView.getChildAt(0);
		for (DocType docType : list) {
			TextView textView = new TextView(mContext);
			textView.setText(docType.getTitle());
			layout.addView(layout);
		}
	}

	private void bindList(List<Article> list) {

	}
}
