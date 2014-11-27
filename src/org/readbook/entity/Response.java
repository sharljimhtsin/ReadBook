/**
 * 
 */
package org.readbook.entity;

import java.util.List;

/**
 * @author Administrator
 *
 */
public class Response {
	private int status;
	private List<?> data;

	/**
	 * @param status
	 * @param data
	 */
	public Response(int status, List<?> data) {
		super();
		this.status = status;
		this.data = data;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the data
	 */
	public List<?> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<?> data) {
		this.data = data;
	}

}
