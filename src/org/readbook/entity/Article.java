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
	private int articleId;
	private String title;
	private String subTitle;
	private String resource;
	private String content;
	private String author;
	private String postTime;
	private String lastModify;
	private int hits;
	private int totalComment;
	private int totalLike;
	private int totalDislike;
	private int totalRepost;
	private int type;
	private int parentCategory;
	private String tag;
	private int imageCounts;
	private String imageUrls;

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
		this.articleId = id;
		this.title = title;
		this.subTitle = subTitle;
		this.resource = url;
		this.content = content;
		this.author = author;
		this.postTime = createTime;
		this.lastModify = lastModify;
		this.hits = totalRead;
		this.totalComment = totalComment;
		this.totalLike = totalLike;
		this.totalDislike = totalDislike;
		this.totalRepost = totalRepost;
		this.type = parentType;
		this.parentCategory = parentCategory;
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
		return resource;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.resource = url;
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
		return postTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.postTime = createTime;
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
		return hits;
	}

	/**
	 * @param totalRead
	 *            the totalRead to set
	 */
	public void setTotalRead(int totalRead) {
		this.hits = totalRead;
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
		return type;
	}

	/**
	 * @param parentType
	 *            the parentType to set
	 */
	public void setParentType(int parentType) {
		this.type = parentType;
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
	 * @return the id
	 */
	public int getId() {
		return articleId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.articleId = id;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag
	 *            the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the imageCounts
	 */
	public int getImageCounts() {
		return imageCounts;
	}

	/**
	 * @param imageCounts
	 *            the imageCounts to set
	 */
	public void setImageCounts(int imageCounts) {
		this.imageCounts = imageCounts;
	}

	/**
	 * @return the imageUrls
	 */
	public String getImageUrls() {
		return imageUrls;
	}

	/**
	 * @param imageUrls
	 *            the imageUrls to set
	 */
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

}
