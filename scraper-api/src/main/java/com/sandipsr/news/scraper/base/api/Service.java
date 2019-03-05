package com.sandipsr.news.scraper.base.api;

import java.io.Serializable;
import java.util.List;

public interface Service extends Serializable{
	
	public List<String> getSearchFields();
	
	public String getServiceDetails();
	
	public String getSupportedAPIs();
	
	
}
