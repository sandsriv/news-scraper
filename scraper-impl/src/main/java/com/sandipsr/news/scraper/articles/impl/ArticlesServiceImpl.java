package com.sandipsr.news.scraper.articles.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sandipsr.news.scraper.articles.api.ArticlesService;
import com.sandipsr.news.scraper.articles.dao.ArticlesDao;
import com.sandipsr.news.scraper.articles.dao.ArticlesEntity;
import com.sandipsr.news.scraper.articles.dao.ArticlesRepository;
import com.sandipsr.news.scraper.articles.data.Articles;
import com.sandipsr.news.scraper.author.dao.AuthorEntity;
import com.sandipsr.news.scraper.author.data.Author;
import com.sandipsr.news.scraper.base.criterias.FilterCriteria;
import com.sandipsr.news.scraper.base.criterias.SearchCriteria;
import com.sandipsr.news.scraper.error.ErrorCode;

@Service
public class ArticlesServiceImpl implements ArticlesService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 708544037540958062L;

	@Autowired
	@Qualifier("articlesRepository")
	private ArticlesRepository articlesRepository;
	
	@Autowired
	private ArticlesDao articlesDao;
	
	@Override
	public List<String> getSearchFields() {
		return articlesDao.getConfiguredSearchFields(ArticlesEntity.class);
	}
	
	public String getServiceDetails() {
		return null;
	}

	public String getSupportedAPIs() {
		return null;
	}
	
	/**
	 * <p> This method is used to create a new Article instance in the system. </p>
	 * @param article represents a instance having title,description,type and associated author(s).
	 * @return the newly added article instance in the system.
	 * @since 2019. 
	 */
	@Transactional
	public Articles create(Articles article,ErrorCode errorObj) {
		if(article == null){
			errorObj.logError(1, "Article instance is null.");
			return null;
		}
		ArticlesEntity entity = new ArticlesEntity(article.getTitle(), article.getType());
		entity.setDecription(article.getDescription());
		
		setAuditData(entity);
		Long Id 			=  (articlesRepository.save(entity)).getArticleId();
		
		ArticlesEntity savedEn = articlesRepository.findById(Id).get();
		
		article = new Articles(savedEn.getTitle(), savedEn.getType());
		article.setDescription(savedEn.getDecription());
		return article;
	}
	
	/**
	 * <p> This method is used to retrieve All Articles based on the "searchText" from the system. </p>
	 * @param searchCriteria This represents the list of filter criteria that can be applied
	 *        in the backend system (DB).
	 * @return Returns the matched resultSet after applying the criterias.     
	 * @since 2019. 
	 */
	@Transactional
	public List<Articles> query(SearchCriteria searchCriteria, ErrorCode errorObj) {
		List<Articles> articles = new ArrayList<Articles>();
		if( searchCriteria != null && searchCriteria.allCriterias().size() == 0){
			
			Iterable<ArticlesEntity> artclesItr =  articlesRepository.findAll();
			if(artclesItr != null){
				for(ArticlesEntity savedEn : artclesItr){
					Articles ar = new Articles(savedEn.getTitle(), savedEn.getType());
					ar.setDescription(savedEn.getDecription());
					articles.add(ar);
				}
			}
		}else{
			System.out.println(this.getClass().getSimpleName()+ " :: "+  "INSIDE ELSE()");
			
			StringBuilder sb = new StringBuilder();
			List<String> searchTexts = new ArrayList<String>();
			int count=0;
			for (FilterCriteria fc : searchCriteria.allCriterias()){
				String searchText = (String) fc.getValue();
				searchTexts.add(fc.getCriterianame()+"-"+ searchText);
			}
			System.out.println(this.getClass().getSimpleName()+ " :: "+  "Search Text Passed : "+ searchTexts);
			List<ArticlesEntity> articlesEns = articlesDao.findArticlesByMultiTextSearch(searchCriteria.allCriterias());
			
			System.out.println("Result: "+ articlesEns.size());
			for(ArticlesEntity savedEn : articlesEns){
				System.out.println("###################################################");
				
				System.out.println("Title: "+ savedEn.getTitle());
				System.out.println("Desc: "+ savedEn.getDecription());
				System.out.println("Type: "+ savedEn.getType());
				System.out.println("Authors: " + savedEn.getAuthors());
				System.out.println("###################################################");
				
				Articles ar = new Articles(savedEn.getTitle(), savedEn.getType());
				ar.setDescription(savedEn.getDecription());
				for(AuthorEntity auen : savedEn.getAuthors()){
					ar.addAuthor(new Author(auen.getId(), auen.getName(), auen.getGender()));
				}
				articles.add(ar);
			}
		}
		
		return articles;
	}
	
	/**
	 * This is used for audit purpose.
	 * @param entity
	 */
	private void setAuditData(ArticlesEntity entity){
		entity.setCreatedOn(new Date());
		entity.setUpdatedOn(new Date());
		entity.setUpdatedBy("sandipsr");
	}
}
