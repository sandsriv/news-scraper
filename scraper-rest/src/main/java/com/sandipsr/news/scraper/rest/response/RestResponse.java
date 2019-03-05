package com.sandipsr.news.scraper.rest.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sandipsr.news.scraper.base.data.BaseResourse;

/**
 * <a>This represnts the response object for a Rest Service Endpoint.
 * It wraps a {@link RestResponse} object and errorcode and error msg.</a> 
 * @author sandipsr
 *
 */
public class RestResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6786828949349397575L;
	/**
	 * <a>Resource Object </a>
	 */
	private BaseResourse resource;
	private List<? extends BaseResourse> resources = new ArrayList<>();
	private String responseCode, responseMsg;
	private Map<String,  Object> resourceMap;
	
	public RestResponse(String responseCode, String responseMsg ){
		this.responseCode = responseCode;
		this.responseMsg  = responseMsg;
	}
	
	public RestResponse( BaseResourse resource, String responseCode, String responseMsg ){
		this.resource     = resource;
		this.responseCode = responseCode;
		this.responseMsg  = responseMsg;
	}
	
	public RestResponse( List<? extends BaseResourse> resources, String responseCode, String responseMsg ){
		this.resources     = resources;
		this.responseCode = responseCode;
		this.responseMsg  = responseMsg;
	}
	
	public RestResponse( Map<String,  Object> resources, String responseCode, String responseMsg ){
		this.resourceMap     = resources;
		this.responseCode = responseCode;
		this.responseMsg  = responseMsg;
	}
	
	public BaseResourse getResource() {
		return resource;
	}

	public void setResource(BaseResourse resource) {
		this.resource = resource;
	}

	public List<? extends BaseResourse> getResources() {
		return resources;
	}

	public void setResources(List<? extends BaseResourse> resources) {
		this.resources = resources;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public Map<String, Object> getResourceMap() {
		return resourceMap;
	}

	public void setResourceMap(Map<String, Object> resourceMap) {
		this.resourceMap = resourceMap;
	}
	
}
