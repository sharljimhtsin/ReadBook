/**
 * 
 */
package org.readbook.entity;

import java.util.ArrayList;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * @author Administrator
 *
 */
@AVClassName("Content")
public class Article extends AVObject {
	private String title;
	private String description;
	private String url;
	private String content;
	private int author;
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
}
