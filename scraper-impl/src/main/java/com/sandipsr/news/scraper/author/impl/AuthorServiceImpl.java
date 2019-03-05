package com.sandipsr.news.scraper.author.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sandipsr.news.scraper.articles.dao.ArticlesEntity;
import com.sandipsr.news.scraper.articles.data.Articles;
import com.sandipsr.news.scraper.author.api.AuthorService;
import com.sandipsr.news.scraper.author.dao.AuthorDao;
import com.sandipsr.news.scraper.author.dao.AuthorEntity;
import com.sandipsr.news.scraper.author.dao.AuthorRepository;
import com.sandipsr.news.scraper.author.data.Author;
import com.sandipsr.news.scraper.base.criterias.FilterCriteria;
import com.sandipsr.news.scraper.base.criterias.SearchCriteria;
import com.sandipsr.news.scraper.error.ErrorCode;

/**
 * <h1>Manages the Author Service Management </h1>
 * 
 * 
 * @author  sandipsr
 * @version 1.0
 * @since   2019
 */


@Service
public class AuthorServiceImpl implements AuthorService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6238532435492556458L;
	
	@Autowired
	@Qualifier("authorRepository")
	private AuthorRepository authorRepository;
	
	@Autowired
	private AuthorDao authorDao;
	
	@Override
	public List<String> getSearchFields() {
		return authorDao.getConfiguredSearchFields(AuthorEntity.class);
	}

	public String getServiceDetails() {
		return null;
	}

	public String getSupportedAPIs() {
		return null;
	}

	
	/**
	 * <p> This method is used to create a new Author instance in the system. </p>
	 * @param author represents a Person having id,name,gender and owns articles.
	 * @return the newly added author instance in the system.
	 * @since 2019. 
	 */
	@Transactional
	public Author create(Author author,ErrorCode errorObj) {
		if(author == null){
			errorObj.logError(1, "Author instance is null.");
			return null;
		}
		AuthorEntity entity = new AuthorEntity(author.getAuthorName(), author.getGender());
		setAuditData(entity);
		Long Id 			=  (authorRepository.save(entity)).getId();
		
		AuthorEntity savedEn = authorRepository.findById(Id).get();
		
		author = new Author(Id, savedEn.getName(), savedEn.getGender());
		return author;
	}
	
	/**
	 * <p> This method is used to retrieve Author/s instances from the system. </p>
	 * @param searchCriteria This represents the list of filter criteria that can be applied
	 *        in the backend system (DB).
	 * @return Returns the matched resultSet after applying the criterias.     
	 * @since 2019. 
	 */
	
	@Transactional
	public List<Author> query(SearchCriteria searchCriteria,ErrorCode errorObj) {
		List<Author> authors = new ArrayList<Author>();
		if( searchCriteria != null && searchCriteria.allCriterias().size() == 0){
			
			Iterable<AuthorEntity> authorsItr =  authorRepository.findAll();
			if(authorsItr != null){
				for(AuthorEntity savedEn : authorsItr){
					authors.add(new Author(savedEn.getId(), savedEn.getName(), savedEn.getGender()));
				}
			}
		}else{
			System.out.println("INSIDE ELSE()");
			StringBuilder sb = new StringBuilder();
			List<String> searchTexts = new ArrayList<String>();
			int count=0;
			for (FilterCriteria fc : searchCriteria.allCriterias()){
				String searchText = (String) fc.getValue();
				searchTexts.add(searchText);
			}
			System.out.println("Search TExt Passed : "+ searchTexts);
			
			//List<AuthorEntity> authorsEns = authorRepository.findByNameString(searchTexts[0], searchTexts[1]);
			List<AuthorEntity> authorsEns = authorDao.findByNameByMultiTextSearch(searchCriteria.allCriterias());
			
			System.out.println("Result: "+ authorsEns.size());
			for(AuthorEntity savedEn : authorsEns){
				Author author = new Author(savedEn.getId(), savedEn.getName(), savedEn.getGender());
				authors.add(author);
				
				for(ArticlesEntity ae: savedEn.getArticles()){
					Articles article = new Articles(ae.getTitle(),ae.getType());
					article.setDescription(ae.getDecription());
					author.addArticles(article);
				}
			}
		}
		return authors;
	}
	
	/**
	 * This is used for audit purpose.
	 * @param entity
	 */
	private void setAuditData(AuthorEntity entity){
		entity.setCreatedOn(new Date());
		entity.setUpdatedOn(new Date());
		entity.setUpdatedBy("sandipsr");
	}
}
