package com.sandipsr.news.scraper.articles.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sandipsr.news.scraper.articles.dao.ArticlesDao;
import com.sandipsr.news.scraper.articles.dao.ArticlesEntity;
import com.sandipsr.news.scraper.articles.dao.ArticlesRepository;
import com.sandipsr.news.scraper.base.criterias.FilterCriteria;
import com.sandipsr.news.scraper.base.dao.BaseDaoImpl;

@Repository("articlesDao")
public class ArticlesDaoImpl extends BaseDaoImpl implements ArticlesDao {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Autowired
	@Qualifier("articlesRepository")
	private ArticlesRepository articlesRepository;
	
	/**
	 * @author sandipsr
	 * @since  2019
	 */
	@Override
	public List<ArticlesEntity> findArticlesByMultiTextSearch(List<FilterCriteria> searchCriterias) {
		boolean enabled = false;
		if(enabled){
			return findArticlesByMultiTextSearch(searchCriterias, enabled);
		}

		String SEARCH_AUTHORS_QUERY = "From "+ ArticlesEntity.class.getSimpleName()+" ";
		List<String> columnnames = getConfiguredSearchFields(ArticlesEntity.class);
		
		Query query = (Query) entityManager.createQuery(createQueryStrWithCritera(SEARCH_AUTHORS_QUERY, searchCriterias, columnnames));
		System.out.println("=========================================================");
		System.out.println("Query for Execution: "+ query.getQueryString());
		System.out.println("=========================================================");
		
		List<ArticlesEntity> entities =  (List<ArticlesEntity>) query.getResultList();
		return entities;
	}
	
	/**
	 * Work around if in case child entities are not loaded.
	 * @param searchCriterias
	 * @param flag
	 * @return
	 */
	public List<ArticlesEntity> findArticlesByMultiTextSearch(List<FilterCriteria> searchCriterias, boolean flag) {
		String SEARCH_AUTHORS_QUERY = "From "+ ArticlesEntity.class.getSimpleName()+" ";
		List<String> columnnames = getConfiguredSearchFields(ArticlesEntity.class);
		
		Query query = (Query) entityManager.createQuery(createQueryStrWithCritera(SEARCH_AUTHORS_QUERY, searchCriterias, columnnames));
		System.out.println("=========================================================");
		System.out.println("Query for Execution: "+ query.getQueryString());
		System.out.println("=========================================================");
		
		List<ArticlesEntity> entities =  (List<ArticlesEntity>) query.getResultList();
		List<ArticlesEntity> savedEntities = new ArrayList<ArticlesEntity>();
		
		for(ArticlesEntity en : entities){
			savedEntities.add(articlesRepository.findById(en.getArticleId()).get());
		}
		return savedEntities;

	}
}
