/**
 * 
 */
package org.readbook.entity;

import java.util.List;

/**
 * @author Administrator
 *
 */

public class DocType {
	private int class_id;
	private String class_name;
	private String subTitle;
	private String url;
	private int totalCount;
	private int childrenNode;
	private List<DocCategory> sub_class;

	/**
	 * @param id
	 * @param title
	 * @param subTitle
	 * @param url
	 * @param totalCount
	 * @param childrenNode
	 * @param childrenList
	 */
	public DocType(int id, String title, String subTitle, String url,
			int totalCount, int childrenNode, List<DocCategory> childrenList) {
		super();
		this.class_id = id;
		this.class_name = title;
		this.subTitle = subTitle;
		this.url = url;
		this.totalCount = totalCount;
		this.childrenNode = childrenNode;
		this.sub_class = childrenList;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return class_name;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.class_name = title;
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle
	 *            the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
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

	/**
	 * @return the id
	 */
	public int getId() {
		return class_id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.class_id = id;
	}

	/**
	 * @return the childrenList
	 */
	public List<DocCategory> getChildrenList() {
		return sub_class;
	}

	/**
	 * @param childrenList
	 *            the childrenList to set
	 */
	public void setChildrenList(List<DocCategory> childrenList) {
		this.sub_class = childrenList;
	}
}
