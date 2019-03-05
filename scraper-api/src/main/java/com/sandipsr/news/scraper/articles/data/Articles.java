package com.sandipsr.news.scraper.articles.data;

import java.util.ArrayList;
import java.util.List;

import com.sandipsr.news.scraper.author.data.Author;
import com.sandipsr.news.scraper.base.data.BaseResourse;

public class Articles implements BaseResourse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4421859118386016769L;

	private String title;
	private String description;
	private String type;
	private List<Author> authors = new ArrayList<Author>();
	
	public Articles(String title, String type){
		this.title = title;
		this.type = type;
	}
	
	public Articles(String title, String type, Author author) {
		this(title,type);
		
		if(author == null){
			author = new Author();
		}
		this.authors.add(author);
	}
	
	public Articles(String title, String type, List<Author> authors) {
		this(title,type);
		
		if(authors == null){
			Author author = new Author();
			this.authors.add(author);
		}else{
			this.authors = authors;	
		}
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author author){
		this.authors.add(author);
	}
}
