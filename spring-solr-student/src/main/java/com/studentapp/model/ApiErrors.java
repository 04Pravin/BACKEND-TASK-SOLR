package com.studentapp.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiErrors {

	LocalDateTime timeStamp;
	String message;
	HttpStatus status;
	int statusCode;
	String error;
	
	public ApiErrors(LocalDateTime timeStamp, String message, HttpStatus status, int statusCode, String error) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
		this.error = error;
	}

	/**
	 * @return the timeStamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ApiErrors [timeStamp=" + timeStamp + ", message=" + message + ", status=" + status + ", statusCode="
				+ statusCode + ", error=" + error + "]";
	}
	
	
}
