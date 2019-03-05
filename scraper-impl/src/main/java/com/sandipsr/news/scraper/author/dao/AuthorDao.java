package com.sandipsr.news.scraper.author.dao;

import java.util.List;

import com.sandipsr.news.scraper.base.criterias.FilterCriteria;
import com.sandipsr.news.scraper.base.dao.BaseDao;

public interface AuthorDao extends BaseDao{

	public List<AuthorEntity> findByNameByMultiTextSearch(List<FilterCriteria> searchCriterias);
}
