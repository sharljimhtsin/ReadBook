/**
 * 
 */
package org.readbook.activity;

import java.util.List;

import org.readbook.R;
import org.readbook.clz.MyApplication;
import org.readbook.clz.adapter.ArticleListAdapter;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.DocType;
import org.readbook.task.ArticleTypeListTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * @author Administrator
 *
 */
public class ArticleTypeListActivity extends BaseActivity implements
		OnItemClickListener {

	private Handler mHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.readbook.activity.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_type_list);
		init();
		BaseRequest request = new BaseRequest();
		ArticleTypeListTask task = new ArticleTypeListTask(request, mHandler);
		task.execute();
//		startLoading();
	}

	private void init() {
		mHandler = new Handler(new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
//					stopLoading();
					buildUI((List<DocType>) msg.obj);
					break;
				case -1:
					showTip("network error");
					break;
				default:
					break;
				}
				return false;
			}
		});
	}

	private void buildUI(List<DocType> list) {
		GridView gridView = (GridView) findViewById(R.id.gridView_article_type_list);
		ArticleListAdapter adapter = new ArticleListAdapter(this, list);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);
	}

	private void showTip(String s) {
		MyApplication.getInstance().showToast(this, s);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		View v = view.findViewById(R.id.textView_article_type_list_title);
		String typeId = String.valueOf(v.getTag());
		Intent intent = new Intent(this, ArticleListActivity.class);
		intent.putExtra("typeId", typeId);
		startActivity(intent);
		finish();
	}

}
