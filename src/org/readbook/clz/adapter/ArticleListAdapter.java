/**
 * 
 */
package org.readbook.clz.adapter;

import java.util.List;

import org.readbook.R;
import org.readbook.entity.Article;
import org.readbook.res.Constants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author Administrator
 *
 */
public class ArticleListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private List<Article> mList;

	public void resetItems(List<Article> list) {
		mList.clear();
		mList.addAll(list);
	}

	public void appendItems(List<Article> list) {
		mList.addAll(list);
	}

	public Article getLastest() {
		return mList.get(mList.size() - 1);
	}

	/**
	 * @param mContext
	 * @param mList
	 */
	public ArticleListAdapter(Context mContext, List<Article> mList) {
		super();
		this.mContext = mContext;
		this.mList = mList;
		mInflater = LayoutInflater.from(this.mContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return mList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_article_list, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.textView_article_list_title);
			viewHolder.content = (TextView) convertView
					.findViewById(R.id.textView_article_list_content);
			viewHolder.icon = (ImageView) convertView
					.findViewById(R.id.imageView_article_list_icon);
			convertView.setTag(Constants.TAG_VIEW, viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag(Constants.TAG_VIEW);
		}
		Article article = mList.get(position);
		// set url to tag for further doing
		convertView.setTag(Constants.TAG_DATA, article.getUrl());
		// bind data to UI
		viewHolder.name.setText(article.getTitle());
		if (article.getParentType() == 1) {
			viewHolder.content.setText(article.getContent());
		}
		if (article.getImageCounts() > 0) {
			String[] urls = article.getImageUrls().split(",");
			ImageLoader.getInstance().displayImage(urls[0], viewHolder.icon);
		}
		return convertView;
	}

	private class ViewHolder {
		TextView name;
		TextView content;
		ImageView icon;
	}

}
