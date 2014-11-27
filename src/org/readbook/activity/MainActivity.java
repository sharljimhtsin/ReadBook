package org.readbook.activity;

import java.util.ArrayList;
import java.util.List;

import org.readbook.R;
import org.readbook.clz.adapter.ArticleListAdapter;
import org.readbook.clz.adapter.MyPagerAdapter;
import org.readbook.entity.Article;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.DocCategory;
import org.readbook.entity.DocType;
import org.readbook.task.ArticleListTask;
import org.readbook.task.ArticleTypeListTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseActivity implements OnPageChangeListener,
		OnItemClickListener {

	private HorizontalScrollView mTypeScrollView;
	private HorizontalScrollView mCategoryScrollView;
	private ViewPager mViewPager;
	private ListView mCurrentListView;
	private Handler mHandler;
	private SlidingMenu mSlidingMenu;

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
		// queryMenu();
	}

	private void queryMenu() {
		BaseRequest request = new BaseRequest();
		ArticleTypeListTask task = new ArticleTypeListTask(request, mHandler);
		task.execute();
	}

	private void queryList(DocCategory category) {
		BaseRequest request = new BaseRequest();
		request.setDocCategoryId(category.getId());
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
					buildViewPager(list.get(0).getChildrenList());
					// build article list with default first one
					queryList(list.get(0).getChildrenList().get(0));
					break;
				case 1:
					List<Article> list2 = (List<Article>) msg.obj;
					bindList(list2);
					break;

				default:
					break;
				}
				return true;
			}
		});
	}

	private void initUI() {
		setContentView(R.layout.activity_main);
		mTypeScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView_typeList);
		mCategoryScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView_categoryList);
		mViewPager = (ViewPager) findViewById(R.id.ViewPager_articleList);
		// configure the SlidingMenu
		mSlidingMenu = new SlidingMenu(mContext);
		mSlidingMenu.setMode(SlidingMenu.RIGHT);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setFadeDegree(0.35f);
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		mSlidingMenu.setMenu(R.layout.title_layout);
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

	private void buildViewPager(List<DocCategory> list) {
		List<View> viewlist = new ArrayList<View>();
		for (DocCategory category : list) {
			ListView listView = new ListView(mContext);
			listView.setTag(category);
			viewlist.add(listView);
		}
		mViewPager.setAdapter(new MyPagerAdapter(viewlist));
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(this);
	}

	private void bindList(List<Article> list) {
		mCurrentListView.setAdapter(new ArticleListAdapter(mContext, list));
		mCurrentListView.setOnItemClickListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		mCurrentListView = (ListView) ((MyPagerAdapter) mViewPager.getAdapter())
				.getViewByPosition(arg0);
		// do binding
		DocCategory category = (DocCategory) mCurrentListView.getTag();
		queryList(category);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}
}
