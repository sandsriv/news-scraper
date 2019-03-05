package com.sandipsr.news.scraper.base.criterias;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author sandipsr
 * This class represents the filter criteria to be appliead on the search requested data.
 * "criteraname" represents the field or attribute name for the Resource class.
 * "values" could be resultsets after the criteria is met. 
 */
public class FilterCriteria {
	
	private String criterianame;
	private String operator;
	private Object value;
	private List<Object> valueList;
	
	public FilterCriteria(){
		
	}
	
	public FilterCriteria(String criterianame, String value) {
		super();
		this.criterianame = criterianame;
		this.value = value;
		this.operator = "=";
	}
	public String getCriterianame() {
		return criterianame;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public List<Object> getValueList() {
		return valueList;
	}
	public void setValueList(List<Object> valueList) {
		this.valueList = valueList;
	}
	public void setCriterianame(String criterianame) {
		this.criterianame = criterianame;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
