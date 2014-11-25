/**
 * 
 */
package org.readbook.entity;

/**
 * @author Administrator
 *
 */

public class DocType {
	private String title;
	private String subTitle;
	private String url;
	private int totalCount;
	private int childrenNode;

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

}
