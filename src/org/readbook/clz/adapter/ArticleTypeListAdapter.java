/**
 * 
 */
package org.readbook.clz.adapter;

import java.util.List;

import org.readbook.R;
import org.readbook.entity.DocType;

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
public class ArticleTypeListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private List<DocType> mList;

	/**
	 * @param mContext
	 * @param mList
	 */
	public ArticleTypeListAdapter(Context mContext, List<DocType> mList) {
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
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		DocType docType = mList.get(position);
		viewHolder.name.setText(docType.getTitle());
		viewHolder.name.setTag(docType.getObjectId());
		return convertView;
	}

	private class ViewHolder {
		TextView name;
	}

}
