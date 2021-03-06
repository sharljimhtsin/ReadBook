package org.readbook.activity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.readbook.R;
import org.readbook.clz.MyApplication;
import org.readbook.clz.adapter.ArticleListAdapter;
import org.readbook.clz.adapter.MyPagerAdapter;
import org.readbook.entity.Article;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.DocCategory;
import org.readbook.entity.DocType;
import org.readbook.res.Constants;
import org.readbook.task.ArticleListTask;
import org.readbook.task.ArticleTypeListTask;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HeaderViewListAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.costum.android.widget.PullAndLoadListView;
import com.costum.android.widget.PullAndLoadListView.OnLoadMoreListener;
import com.costum.android.widget.PullToRefreshListView.OnRefreshListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends BaseActivity implements OnPageChangeListener,
		OnItemClickListener, OnClickListener, OnRefreshListener,
		OnLoadMoreListener {

	private HorizontalScrollView mTypeScrollView;
	private HorizontalScrollView mCategoryScrollView;
	private ViewPager mViewPager;
	private PullAndLoadListView mCurrentListView;
	private Handler mHandler;
	private SlidingMenu mSlidingMenu;
	private LinkedList<DocCategory> mCategoryHolder = new LinkedList<DocCategory>();

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

	/**
	 * @author Administrator
	 *
	 */
	private enum Method {
		Initial, LoadMore, Refresh
	}

	/**
	 * @param category
	 * @param method
	 *            0 for initial,1 for load more,2 for refresh
	 */
	private void queryList(DocCategory category, Method method) {
		BaseRequest request = new BaseRequest();
		request.setDocCategoryId(String.valueOf(category.getId()));
		ArticleListTask task = new ArticleListTask(request, mHandler);
		switch (method) {
		case Initial:
			task.execute("0");
			break;
		case LoadMore:
			HeaderViewListAdapter adapter = (HeaderViewListAdapter) mCurrentListView
					.getAdapter();
			Article article = ((ArticleListAdapter) adapter.getWrappedAdapter())
					.getLastest();
			request.setEndTime(article.getCreateTime());
			task.execute("1");
			break;
		case Refresh:
			task.execute("2");
			break;
		default:
			break;
		}
	}

	public static final int Logic_Error = -3;
	public static final int Net_Error = -2;
	public static final int No_Data = -1;
	public static final int Initial_Menu = 0;
	public static final int Initial_List = 1;
	public static final int Refresh_List = 2;
	public static final int Do_LoadMore = 3;
	public static final int Do_Refresh = 4;
	public static final int Do_NoMoreData = 5;

	private void prepareHandler() {
		mHandler = new Handler(new Callback() {

			@SuppressWarnings("unchecked")
			@Override
			public boolean handleMessage(Message msg) {
				switch (msg.what) {
				case Logic_Error:
					String info1 = String.valueOf(msg.obj);
					showTip(info1);
					break;
				case Net_Error:
					String info2 = String.valueOf(msg.obj);
					showTip(info2);
					break;
				case No_Data:
					String info3 = String.valueOf(msg.obj);
					showTip(info3);
					break;
				case Initial_Menu:
					List<DocType> list = (List<DocType>) msg.obj;
					bindMenu(list);
					// build subMenu/viewPager with default first one
					refreshCategoryHolder(list.get(0).getChildrenList());
					bindSubmenu();
					buildViewPager();
					break;
				case Initial_List:
					List<Article> list2 = (List<Article>) msg.obj;
					bindList(list2);
					break;
				case Refresh_List:
					List<DocCategory> list3 = (List<DocCategory>) msg.obj;
					refreshCategoryHolder(list3);
					bindSubmenu();
					buildViewPager();
					break;
				case Do_LoadMore:
					List<Article> list4 = (List<Article>) msg.obj;
					doLoadMore(list4);
					break;
				case Do_Refresh:
					List<Article> list5 = (List<Article>) msg.obj;
					doRefresh(list5);
					break;
				case Do_NoMoreData:
					String info = String.valueOf(msg.obj);
					doNoMoreData(info);
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
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mSlidingMenu.setFadeDegree(0.35f);
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		mSlidingMenu.setMenu(R.layout.title_layout);
	}

	private void bindMenu(List<DocType> list) {
		LinearLayout layout = (LinearLayout) mTypeScrollView.getChildAt(0);
		layout.removeAllViews(); // clear first
		for (DocType docType : list) {
			TextView textView = new TextView(mContext);
			textView.setText(docType.getTitle());
			textView.setTextSize(20);
			textView.setHeight(150);
			textView.setWidth(150);
			textView.setBackgroundColor(Color.RED);
			textView.setGravity(Gravity.CENTER_VERTICAL);
			textView.setTag(docType.getChildrenList());
			textView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Message message = mHandler.obtainMessage(Refresh_List,
							v.getTag());
					mHandler.sendMessage(message);
				}
			});
			layout.addView(textView);
		}
	}

	private void refreshCategoryHolder(List<DocCategory> list) {
		mCategoryHolder.clear();
		mCategoryHolder.addAll(list);
	}

	private void bindSubmenu() {
		LinearLayout layout = (LinearLayout) mCategoryScrollView.getChildAt(0);
		layout.removeAllViews(); // clear first
		for (int i = 0; i < mCategoryHolder.size(); i++) {
			DocCategory category = mCategoryHolder.get(i);
			TextView textView = new TextView(mContext);
			textView.setText(category.getTitle());
			textView.setTextSize(20);
			textView.setHeight(150);
			textView.setWidth(150);
			textView.setBackgroundColor(Color.RED);
			textView.setGravity(Gravity.CENTER_VERTICAL);
			textView.setTag(i);
			textView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mViewPager.setCurrentItem(Integer.valueOf(String.valueOf(v
							.getTag())));
				}
			});
			layout.addView(textView);
		}
	}

	private void buildViewPager() {
		List<View> viewlist = new ArrayList<View>();
		for (int i = 0; i < mCategoryHolder.size(); i++) {
			PullAndLoadListView listView = new PullAndLoadListView(mContext);
			viewlist.add(listView);
		}
		mViewPager.setAdapter(new MyPagerAdapter(viewlist));
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setCurrentItem(1);
		mViewPager.setCurrentItem(0);
	}

	private void bindList(List<Article> list) {
		mCurrentListView.setAdapter(new ArticleListAdapter(mContext, list));
		mCurrentListView.setOnItemClickListener(this);
		mCurrentListView.setOnRefreshListener(this);
		mCurrentListView.setOnLoadMoreListener(this);
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
		mCurrentListView = (PullAndLoadListView) ((MyPagerAdapter) mViewPager
				.getAdapter()).getViewByPosition(arg0);
		// do binding
		DocCategory category = mCategoryHolder.get(arg0);
		queryList(category, Method.Initial);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String url = String.valueOf(view.getTag(Constants.TAG_DATA));
		Intent intent = new Intent(mContext, ArticleContentActivity.class);
		intent.putExtra("url", url);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefresh() {
		DocCategory category = mCategoryHolder.get(mViewPager.getCurrentItem());
		queryList(category, Method.Refresh);
	}

	private void doRefresh(List<Article> list) {
		HeaderViewListAdapter adapter = (HeaderViewListAdapter) mCurrentListView
				.getAdapter();
		ArticleListAdapter adapterInner = (ArticleListAdapter) adapter
				.getWrappedAdapter();
		adapterInner.resetItems(list);
		adapterInner.notifyDataSetChanged();
		mCurrentListView.onRefreshComplete();
	}

	@Override
	public void onLoadMore() {
		DocCategory category = mCategoryHolder.get(mViewPager.getCurrentItem());
		queryList(category, Method.LoadMore);
	}

	private void doLoadMore(List<Article> list) {
		HeaderViewListAdapter adapter = (HeaderViewListAdapter) mCurrentListView
				.getAdapter();
		ArticleListAdapter adapterInner = (ArticleListAdapter) adapter
				.getWrappedAdapter();
		adapterInner.appendItems(list);
		adapterInner.notifyDataSetChanged();
		mCurrentListView.onLoadMoreComplete();
	}

	private void doNoMoreData(String info) {
		mCurrentListView.onLoadMoreComplete();
		showTip(info);
	}

	private void showTip(String info) {
		MyApplication.getInstance().showToast(this, info);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		System.exit(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		System.exit(0);
	}
}
