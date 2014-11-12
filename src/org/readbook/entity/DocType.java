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
@AVClassName("DocType")
public class DocType extends AVObject {
	private int id;
	private String title;
	private String description;
	private String url;
	private int totalCount;
	private int childrenNode;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @return the childrenNode
	 */
	public int getChildrenNode() {
		return childrenNode;
	}

	/**
	 * @param childrenNode
	 *            the childrenNode to set
	 */
	public void setChildrenNode(int childrenNode) {
		this.childrenNode = childrenNode;
	}
}
