package com.sandipsr.news.scraper.rest.controller;

import com.sandipsr.news.scraper.error.ErrorCode;
import com.sandipsr.news.scraper.rest.response.RestResponse;

public class BaseController {

	public void setErrorResponse(ErrorCode errorObj, RestResponse response){
		response = new RestResponse(errorObj.getErrorCode()+"", errorObj.getErrorMessage().toString());
	}
	
	public void setSuccessResponse(RestResponse response){
		response.setResponseCode(0+"");
		response.setResponseMsg("Method executed successfully..");
	}
}
