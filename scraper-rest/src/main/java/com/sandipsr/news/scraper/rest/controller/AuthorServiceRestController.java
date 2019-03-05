package com.sandipsr.news.scraper.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandipsr.news.scraper.author.api.AuthorService;
import com.sandipsr.news.scraper.author.data.Author;
import com.sandipsr.news.scraper.base.criterias.FilterCriteria;
import com.sandipsr.news.scraper.base.criterias.SearchCriteria;
import com.sandipsr.news.scraper.error.ErrorCode;
import com.sandipsr.news.scraper.rest.response.RestResponse;

@RestController

public class AuthorServiceRestController extends BaseController{
	
	private static final String ADD_AUTHOR_URL       = "/addAuthor";
	private static final String QUERY_AUTHOR_URL     = "/queryAuthor";
	
	
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(method=RequestMethod.POST, value= ADD_AUTHOR_URL , consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public RestResponse addAuthor(@RequestBody Author author){
		ErrorCode errorObj = new ErrorCode();
		RestResponse response = null; 
		if(author == null){
			errorObj.logError(404, "invalid author instance");
			setErrorResponse(errorObj, response);
			return response;
		}
		
		author = authorService.create(author, errorObj);
		response = new RestResponse(author, "0", "");
		setSuccessResponse(response);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST, value= QUERY_AUTHOR_URL , consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public RestResponse queryAuthors(@RequestBody(required=false)  SearchCriteria criteria){
		ErrorCode errorObj = new ErrorCode();
		if(criteria == null){
			criteria = new SearchCriteria();
		}
		
		List<Author> authors = authorService.query(criteria, errorObj);
		RestResponse response = new RestResponse(authors, "0", "");
		setSuccessResponse(response);
		return response; 
	}
	
	
	/**
	 * Testing only
	 * @return
	 */
	private String serialize(){
		SearchCriteria bean = new SearchCriteria();
		FilterCriteria fc1 = new FilterCriteria("name", "Kumar");
		FilterCriteria fc2 = new FilterCriteria("gender", "M");
		bean.addCritera(fc1);
		bean.addCritera(fc2);
		
		ObjectMapper mapper = new ObjectMapper();
		String responseJson = null;
		try {
		 // convert user object to json string,
			responseJson = mapper.writeValueAsString(bean);

		} catch (JsonGenerationException e) {
				 e.printStackTrace();
		} catch (JsonMappingException e) {
				 e.printStackTrace();
		} catch (IOException e) {
				 e.printStackTrace();
		}
		
		return responseJson;
	}
	
	public static void main(String[] args) {
		System.out.println(new AuthorServiceRestController().serialize());
	}
}
