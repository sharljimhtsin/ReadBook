/**
 * 
 */
package org.readbook.entity;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * @author Administrator
 *
 */
@AVClassName("Comment")
public class Comment extends AVObject {
	private String title;
	private String content;
	private int author;
	private int article;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the author
	 */
	public int getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(int author) {
		this.author = author;
	}

	/**
	 * @return the article
	 */
	public int getArticle() {
		return article;
	}

	/**
	 * @param article
	 *            the article to set
	 */
	public void setArticle(int article) {
		this.article = article;
	}

}
