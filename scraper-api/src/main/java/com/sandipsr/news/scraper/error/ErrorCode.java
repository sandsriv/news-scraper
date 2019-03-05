package com.sandipsr.news.scraper.error;

public class ErrorCode {
	
	private int errorCode;
	private StringBuilder errorMessage;
	
	public ErrorCode(){
		errorMessage = new StringBuilder();
	}
	
	public void logError(int code, String msg){
		this.errorCode = code;
		this.errorMessage.append(msg);
	}

	public int getErrorCode() {
		return errorCode;
	}
	public StringBuilder getErrorMessage() {
		return errorMessage;
	}
	
	public boolean isErrorLogged(){
		return (this.errorMessage.length() == 0); 
	}
	
}
