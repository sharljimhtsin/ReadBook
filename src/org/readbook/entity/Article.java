/**
 * 
 */
package org.readbook.entity;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 */

public class Article {
	private int id;
	private String title;
	private String subTitle;
	private String url;
	private String content;
	private String author;
	private String createTime;
	private String lastModify;
	private int totalRead;
	private int totalComment;
	private int totalLike;
	private int totalDislike;
	private int totalRepost;
	private int parentType;
	private int parentCategory;
	private ArrayList<String> keyword;
	private ArrayList<String> pic;

	/**
	 * @param id
	 * @param title
	 * @param subTitle
	 * @param url
	 * @param content
	 * @param author
	 * @param createTime
	 * @param lastModify
	 * @param totalRead
	 * @param totalComment
	 * @param totalLike
	 * @param totalDislike
	 * @param totalRepost
	 * @param parentType
	 * @param parentCategory
	 * @param keyword
	 * @param pic
	 */
	public Article(int id, String title, String subTitle, String url,
			String content, String author, String createTime,
			String lastModify, int totalRead, int totalComment, int totalLike,
			int totalDislike, int totalRepost, int parentType,
			int parentCategory, ArrayList<String> keyword, ArrayList<String> pic) {
		super();
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.url = url;
		this.content = content;
		this.author = author;
		this.createTime = createTime;
		this.lastModify = lastModify;
		this.totalRead = totalRead;
		this.totalComment = totalComment;
		this.totalLike = totalLike;
		this.totalDislike = totalDislike;
		this.totalRepost = totalRepost;
		this.parentType = parentType;
		this.parentCategory = parentCategory;
		this.keyword = keyword;
		this.pic = pic;
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
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the lastModify
	 */
	public String getLastModify() {
		return lastModify;
	}

	/**
	 * @param lastModify
	 *            the lastModify to set
	 */
	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}

	/**
	 * @return the totalRead
	 */
	public int getTotalRead() {
		return totalRead;
	}

	/**
	 * @param totalRead
	 *            the totalRead to set
	 */
	public void setTotalRead(int totalRead) {
		this.totalRead = totalRead;
	}

	/**
	 * @return the totalComment
	 */
	public int getTotalComment() {
		return totalComment;
	}

	/**
	 * @param totalComment
	 *            the totalComment to set
	 */
	public void setTotalComment(int totalComment) {
		this.totalComment = totalComment;
	}

	/**
	 * @return the totalLike
	 */
	public int getTotalLike() {
		return totalLike;
	}

	/**
	 * @param totalLike
	 *            the totalLike to set
	 */
	public void setTotalLike(int totalLike) {
		this.totalLike = totalLike;
	}

	/**
	 * @return the totalDislike
	 */
	public int getTotalDislike() {
		return totalDislike;
	}

	/**
	 * @param totalDislike
	 *            the totalDislike to set
	 */
	public void setTotalDislike(int totalDislike) {
		this.totalDislike = totalDislike;
	}

	/**
	 * @return the totalRepost
	 */
	public int getTotalRepost() {
		return totalRepost;
	}

	/**
	 * @param totalRepost
	 *            the totalRepost to set
	 */
	public void setTotalRepost(int totalRepost) {
		this.totalRepost = totalRepost;
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

	/**
	 * @return the parentCategory
	 */
	public int getParentCategory() {
		return parentCategory;
	}

	/**
	 * @param parentCategory
	 *            the parentCategory to set
	 */
	public void setParentCategory(int parentCategory) {
		this.parentCategory = parentCategory;
	}

	/**
	 * @return the keyword
	 */
	public ArrayList<String> getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(ArrayList<String> keyword) {
		this.keyword = keyword;
	}

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
	 * @return the pic
	 */
	public ArrayList<String> getPic() {
		return pic;
	}

	/**
	 * @param pic
	 *            the pic to set
	 */
	public void setPic(ArrayList<String> pic) {
		this.pic = pic;
	}

}
