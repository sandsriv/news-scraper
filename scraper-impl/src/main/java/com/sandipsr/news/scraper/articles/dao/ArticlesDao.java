package com.sandipsr.news.scraper.articles.dao;

import java.util.List;

import com.sandipsr.news.scraper.base.criterias.FilterCriteria;
import com.sandipsr.news.scraper.base.dao.BaseDao;

public interface ArticlesDao extends BaseDao{

	public List<ArticlesEntity> findArticlesByMultiTextSearch(List<FilterCriteria> searchCriterias);
}
