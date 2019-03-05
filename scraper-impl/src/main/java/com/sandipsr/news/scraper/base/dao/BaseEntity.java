package com.sandipsr.news.scraper.base.dao;

import java.io.Serializable;
import java.util.Date;

public interface BaseEntity extends Serializable{
	
	public Date   createdOn();
	public Date   updatedOn();
	public String updatedBy();
	
}
