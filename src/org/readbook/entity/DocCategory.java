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
@AVClassName("Category")
public class DocCategory extends AVObject {
	private String title;
	private String description;
	private String url;
	private int totalCount;
	private int parentType;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the parentType
	 */
	public int getParentType() {
		return parentType;
	}

	/**
	 * @param parentType
	 *            the parentType to set
	 */
	public void setParentType(int parentType) {
		this.parentType = parentType;
	}
}
