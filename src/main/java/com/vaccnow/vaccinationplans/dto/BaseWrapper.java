package com.vaccnow.vaccinationplans.dto;

import javax.persistence.Inheritance;

import org.springframework.http.HttpStatus;

@Inheritance
public class BaseWrapper {

	private Object response;

	private ResponseMessage responseMessage;

	private Pagination pagination;

	public BaseWrapper() {
		this.responseMessage = new ResponseMessage(HttpStatus.OK.toString(), "OK");
	}

	public BaseWrapper(Object response, ResponseMessage responseMessage) {
		this.response = response;
		this.responseMessage = responseMessage;
	}

	public BaseWrapper(Object response, Pagination pagination) {
		this.response = response;
		this.pagination = pagination;
		this.responseMessage = new ResponseMessage(HttpStatus.OK.toString(), "OK");
	}


	public BaseWrapper(Object response) {
		this.response = response;
		this.responseMessage = new ResponseMessage(HttpStatus.OK.toString(), "OK");
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
