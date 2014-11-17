/**
 * 
 */
package org.readbook.entity;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * @author Administrator
 *
 */
@AVClassName("Content")
public class Article extends AVObject {
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

	/**
	 * @return the title
	 */
	public String getTitle() {
		return getString("title");
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		put("title", title);
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return getString("url");
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		put("url", url);
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return getString("content");
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		put("content", content);
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return getString("author");
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		put("author", author);
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return getString("createTime");
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime) {
		put("createTime", createTime);
	}

	/**
	 * @return the lastModify
	 */
	public String getLastModify() {
		return getString("lastModify");
	}

	/**
	 * @param lastModify
	 *            the lastModify to set
	 */
	public void setLastModify(String lastModify) {
		put("lastModify", lastModify);
	}

	/**
	 * @return the totalRead
	 */
	public int getTotalRead() {
		return getInt("totalRead");
	}

	/**
	 * @param totalRead
	 *            the totalRead to set
	 */
	public void setTotalRead(int totalRead) {
		put("totalRead", totalRead);
	}

	/**
	 * @return the totalComment
	 */
	public int getTotalComment() {
		return getInt("totalComment");
	}

	/**
	 * @param totalComment
	 *            the totalComment to set
	 */
	public void setTotalComment(int totalComment) {
		put("totalComment", totalComment);
	}

	/**
	 * @return the totalLike
	 */
	public int getTotalLike() {
		return getInt("totalLike");
	}

	/**
	 * @param totalLike
	 *            the totalLike to set
	 */
	public void setTotalLike(int totalLike) {
		put("totalLike", totalLike);
	}

	/**
	 * @return the totalDislike
	 */
	public int getTotalDislike() {
		return getInt("totalDislike");
	}

	/**
	 * @param totalDislike
	 *            the totalDislike to set
	 */
	public void setTotalDislike(int totalDislike) {
		put("totalDislike", totalDislike);
	}

	/**
	 * @return the totalRepost
	 */
	public int getTotalRepost() {
		return getInt("totalRepost");
	}

	/**
	 * @param totalRepost
	 *            the totalRepost to set
	 */
	public void setTotalRepost(int totalRepost) {
		put("totalRepost", totalRepost);
	}

	/**
	 * @return the keyword
	 */
	public List<String> getKeyword() {
		return getList("keyword");
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(ArrayList<String> keyword) {
		put("keyword", keyword);
	}

	/**
	 * @return the parentType
	 */
	public String getParentType() {
		return getString("parentType");
	}

	/**
	 * @param parentType
	 *            the parentType to set
	 */
	public void setParentType(String parentType) {
		put("parentType", parentType);
	}

	/**
	 * @return the parentCategory
	 */
	public String getParentCategory() {
		return getString("parentCategory");
	}

	/**
	 * @param parentCategory
	 *            the parentCategory to set
	 */
	public void setParentCategory(String parentCategory) {
		put("parentCategory", parentCategory);
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return getString("subTitle");
	}

	/**
	 * @param subTitle
	 *            the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		put("subTitle", subTitle);
	}
}
