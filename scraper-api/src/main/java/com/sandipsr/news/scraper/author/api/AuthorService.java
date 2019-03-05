package com.sandipsr.news.scraper.author.api;

import java.util.List;

import com.sandipsr.news.scraper.author.data.Author;
import com.sandipsr.news.scraper.base.api.Service;
import com.sandipsr.news.scraper.base.criterias.SearchCriteria;
import com.sandipsr.news.scraper.error.ErrorCode;

public interface AuthorService extends Service{
	
	public Author create(Author author, ErrorCode errorObj);
	public List<Author> query(SearchCriteria searchCriteria, ErrorCode errorObj);
	
}
