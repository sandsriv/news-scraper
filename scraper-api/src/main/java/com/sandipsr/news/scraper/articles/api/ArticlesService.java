package com.sandipsr.news.scraper.articles.api;

import java.util.List;

import com.sandipsr.news.scraper.articles.data.Articles;
import com.sandipsr.news.scraper.base.api.Service;
import com.sandipsr.news.scraper.base.criterias.SearchCriteria;
import com.sandipsr.news.scraper.error.ErrorCode;

public interface ArticlesService extends Service{
	
	public List<Articles> query(SearchCriteria searchCriteria, ErrorCode errorObj);

	public Articles create(Articles article, ErrorCode errorObj);
}
