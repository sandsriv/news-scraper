package com.sandipsr.news.scraper.base.criterias;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {
	
	private List<FilterCriteria> criterias = new ArrayList<FilterCriteria>();
	
	public SearchCriteria(){
		
	}
	
	public void addCritera(FilterCriteria criteria){
		this.criterias.add(criteria);
	}
	
	public List<FilterCriteria> allCriterias(){
		return this.criterias;
	}

	public List<FilterCriteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<FilterCriteria> criterias) {
		this.criterias = criterias;
	}
	
}
