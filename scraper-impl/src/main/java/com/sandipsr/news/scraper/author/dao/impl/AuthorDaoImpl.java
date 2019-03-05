package com.sandipsr.news.scraper.author.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sandipsr.news.scraper.author.dao.AuthorDao;
import com.sandipsr.news.scraper.author.dao.AuthorEntity;
import com.sandipsr.news.scraper.base.criterias.FilterCriteria;
import com.sandipsr.news.scraper.base.dao.BaseDaoImpl;

@Repository("authorDao")
public class AuthorDaoImpl extends BaseDaoImpl implements AuthorDao {
	
	//public static final String SEARCH_AUTHORS_QUERY = "SELECT auth FROM AuthorEntity auth WHERE LOWER(auth.name) like LOWER('%Kumar%')";
	public static  String SEARCH_AUTHORS_QUERY = "SELECT auth FROM AuthorEntity auth";
	private static final String SEARCH_TXT_KEY      = "search-param-";
	
	//@Autowired
    //private SessionFactory sessionFactory;
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<AuthorEntity> findByNameByMultiTextSearch(List<FilterCriteria> searchCriterias) {
		SEARCH_AUTHORS_QUERY = "From "+ AuthorEntity.class.getSimpleName()+" ";
		
		List<String> columnnames = getConfiguredSearchFields(AuthorEntity.class);
		
		Query query = (Query) entityManager.createQuery(createQueryStrWithCritera(SEARCH_AUTHORS_QUERY, searchCriterias, columnnames));
		System.out.println("=========================================================");
		System.out.println("Query for Execution: "+ query.getQueryString());
		System.out.println("=========================================================");
		
		return (List<AuthorEntity>) query.getResultList();
	}
	
	
}
