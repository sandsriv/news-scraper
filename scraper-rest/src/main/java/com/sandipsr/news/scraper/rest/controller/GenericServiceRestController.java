package com.sandipsr.news.scraper.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandipsr.news.scraper.articles.api.ArticlesService;
import com.sandipsr.news.scraper.articles.data.Articles;
import com.sandipsr.news.scraper.author.api.AuthorService;
import com.sandipsr.news.scraper.author.data.Author;
import com.sandipsr.news.scraper.base.criterias.FilterCriteria;
import com.sandipsr.news.scraper.base.criterias.SearchCriteria;
import com.sandipsr.news.scraper.error.ErrorCode;
import com.sandipsr.news.scraper.rest.response.RestResponse;

/**
 * <a>This class is used to search articles and authors details from the system using comma seperated plain texts.</a>
 * @author sandipsr
 * @since  2019
 * 
 */
@RestController
public class GenericServiceRestController extends BaseController{
	
	private static final String QUERY_NEWS_URL          = "/searchNews";
	private static final String QUERY_NEWS_DATA_URL     = "/searchData";
	
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	ArticlesService articleService;
	
	@RequestMapping(method=RequestMethod.POST, value= QUERY_NEWS_URL , consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public RestResponse queryNews(@RequestBody(required=false)  SearchCriteria criteria){
		if(criteria == null){
			criteria = new SearchCriteria();
		}

		return queryNewsAPI(criteria); 
	}
	
	@RequestMapping(method=RequestMethod.GET, value= QUERY_NEWS_DATA_URL , consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public RestResponse queryNewsPlainText(@RequestParam("newsdata") String search){
		SearchCriteria criteria = new SearchCriteria();
		
		List<String> authorFields  = authorService.getSearchFields();
		List<String> articleFields = articleService.getSearchFields();
		
		System.out.println("AUTHORFIELDS:= "+ authorFields);
		System.out.println("ARTICLEFIELDS:= "+ articleFields);
		
		if(search != null){
			String[] searchText1Array = search.split(",");
			if(searchText1Array == null){
				searchText1Array = search.split(",");	
			}

			for(String c: searchText1Array){
				for(String field : authorFields){
					FilterCriteria fc = new FilterCriteria(field, c);	
					criteria.addCritera(fc);
				}
			}
			
			for(String c: searchText1Array){
				for(String field : articleFields){
					FilterCriteria fc = new FilterCriteria(field, c);
					criteria.addCritera(fc);
				}
			}
		}
		return queryNewsAPI(criteria);
	}
	
	/**
	 * Query implementation for Search NEWs
	 * @param criteria
	 * @return
	 */
	private RestResponse queryNewsAPI(SearchCriteria criteria){
		Map<String,  Object> returnMAP = new HashMap<String, Object>(); 
		ErrorCode errorObj = new ErrorCode();
		try{
			List<Author> authors = authorService.query(criteria, errorObj);
			//resources.add(authors);
			returnMAP.put("AUTHORS", authors);
			
		}catch(Exception e){
			System.err.println("Error in Search for Authors");
		}
		
		try{
			List<Articles> articles = articleService.query(criteria, errorObj);
			returnMAP.put("ARTICLES", articles);
		}catch(Exception e){
			System.err.println("Error in Search for Articles");
		}
		
		RestResponse response = new RestResponse(returnMAP, "0", "");
		setSuccessResponse(response);
		
		System.out.println("Returning Response "+ response);
		return response;
	}
}
