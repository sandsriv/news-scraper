package com.sandipsr.news.scraper.base.dao;

import java.util.List;

import com.sandipsr.news.scraper.base.criterias.FilterCriteria;

public interface BaseDao {

	public static final String OPERATOR_IN  = "IN";
	public static final String OPERATOR_EQ  = "EQ";
	public static final String OPERATOR_AND = "AND";
	public static final String OPERATOR_LIKE  = "LIKE";
	
	public List<String> getConfiguredSearchFields(Class claz);
	public String	createQueryStrWithCritera(String queryStr, List<FilterCriteria> searchCriterias, List<String> columns);
}
