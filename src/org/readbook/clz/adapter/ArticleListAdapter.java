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
import android.widget.TextView;

/**
 * @author Administrator
 *
 */
public class ArticleListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private List<Article> mList;

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
			convertView = mInflater.inflate(R.layout.item_article_type_list,
					null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.textView_article_type_list_title);
			convertView.setTag(Constants.TAG_VIEW, viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag(Constants.TAG_VIEW);
		}
		Article article = mList.get(position);
		viewHolder.name.setText(article.getTitle());
		viewHolder.name.setTag(Constants.TAG_DATA, article.getUrl());
		return convertView;
	}

	private class ViewHolder {
		TextView name;
	}

}
