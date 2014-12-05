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
		Article article = mList.get(position);
		ViewHolder_No_Pic viewHolder;
		viewHolder = getViewHolderByArticle(article);
		convertView = viewHolder.view;
		// set url to tag for further doing
		convertView.setTag(Constants.TAG_DATA, article.getUrl());
		// bind data to UI
		if (viewHolder instanceof ViewHolder_No_Pic) {
			viewHolder.bindData(article);
		} else if (viewHolder instanceof ViewHolder) {
			((ViewHolder) viewHolder).bindData(article);
		} else {
			((ViewHolder_Multi_Pic) viewHolder).bindData(article);
		}
		return convertView;
	}

	private ViewHolder_No_Pic getViewHolderByArticle(Article article) {
		if (article.getParentType() == 1) {
			// No picture
			return new ViewHolder_No_Pic();
		} else {
			if (article.getImageCounts() > 1) {
				// multi-picture
				return new ViewHolder_Multi_Pic();
			} else {
				// single picture
				return new ViewHolder();
			}
		}
	}

	private class ViewHolder_No_Pic {
		View view;
		TextView content;

		/**
		 * 
		 */
		public ViewHolder_No_Pic() {
			view = mInflater.inflate(R.layout.item_article_list_no_pic, null);
			content = (TextView) view
					.findViewById(R.id.textView_article_list_content);
		}

		public void bindData(Article article) {
			content.setText(article.getContent());
		}

	}

	private class ViewHolder extends ViewHolder_No_Pic {
		TextView name;
		ImageView icon;

		/**
		 * 
		 */
		public ViewHolder() {
			view = mInflater.inflate(R.layout.item_article_list, null);
			name = (TextView) view
					.findViewById(R.id.textView_article_list_title);
			icon = (ImageView) view
					.findViewById(R.id.imageView_article_list_icon);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.readbook.clz.adapter.ArticleListAdapter.ViewHolder_No_Pic#bindData
		 * (org.readbook.entity.Article)
		 */
		@Override
		public void bindData(Article article) {
			name.setText(article.getTitle());
			String[] urls = article.getImageUrls().split(",");
			ImageLoader.getInstance().displayImage(urls[0], icon);
		}
	}

	private class ViewHolder_Multi_Pic extends ViewHolder {
		ImageView icon2;
		ImageView icon3;

		/**
		 * 
		 */
		public ViewHolder_Multi_Pic() {
			view = mInflater
					.inflate(R.layout.item_article_list_multi_pic, null);
			name = (TextView) view
					.findViewById(R.id.textView_article_list_title);
			icon = (ImageView) view
					.findViewById(R.id.imageView_article_list_icon);
			icon2 = (ImageView) view
					.findViewById(R.id.imageView_article_list_icon2);
			icon3 = (ImageView) view
					.findViewById(R.id.imageView_article_list_icon3);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.readbook.clz.adapter.ArticleListAdapter.ViewHolder#bindData(org
		 * .readbook.entity.Article)
		 */
		@Override
		public void bindData(Article article) {
			name.setText(article.getTitle());
			String[] urls = article.getImageUrls().split(",");
			ImageLoader.getInstance().displayImage(urls[0], icon);
			ImageLoader.getInstance().displayImage(urls[1], icon2);
			ImageLoader.getInstance().displayImage(urls[2], icon3);
		}
	}

}
