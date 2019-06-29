/**
 * 
 */
package com.shail.musicfinder.musicFinder.services;

import java.io.Serializable;


public class GenericResponse implements java.io.Serializable {

	private boolean success;
	private int errCode;
	private int subErrCode;
	private Serializable data;
	private String errMsg;

	public GenericResponse() {
	}

	public GenericResponse(boolean isSuccess) {
		this.success = isSuccess;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean isSuccess) {
		this.success = isSuccess;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public int getSubErrCode() {
		return subErrCode;
	}

	public void setSubErrCode(int subErrCode) {
		this.subErrCode = subErrCode;
	}

	public Serializable getData() {
		return data;
	}

	public void setData(Serializable data) {
		this.data = data;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
