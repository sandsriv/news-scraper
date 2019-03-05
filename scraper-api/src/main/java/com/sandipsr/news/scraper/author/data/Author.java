package com.sandipsr.news.scraper.author.data;

import java.util.ArrayList;
import java.util.List;

import com.sandipsr.news.scraper.articles.data.Articles;
import com.sandipsr.news.scraper.base.annotation.SearchFields;
import com.sandipsr.news.scraper.base.data.BaseResourse;

/**
 * 
 * @author sandipsr
 *	
 */
public class Author implements BaseResourse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6581706653200728902L;
	
	private static final String DEFAULT_AUTHOR_NAME =  "Anonymous";
	
	private Long   authorId;
	
	@SearchFields(enable=true)
	private String authorName;
	
	@SearchFields(enable=true)
	private String gender;
	
	private String[] awards;
	
	private List<Articles> articles = new ArrayList<Articles>();
	
	public Author(){
		this.authorName = DEFAULT_AUTHOR_NAME;
	}
	
	public Author(Long authorId, String authorName, String gender) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.gender = gender;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String[] getAwards() {
		return awards;
	}

	public void setAwards(String[] awards) {
		this.awards = awards;
	}
	
	
	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}
	
	public void addArticles(Articles article){
		this.articles.add(article);
	}
	
	public boolean equals(Object o){
		
		if( ! (o instanceof Author) ){
			return false;
		}
		Author authObj = (Author) o;
		if( authObj.getAuthorId() != this.getAuthorId()){
			return false;
		}
		if( authObj.getAuthorName() != this.getAuthorName()){
			return false;
		}
		if( authObj.getAuthorId() == this.getAuthorId() 
				&&
				 authObj.getAuthorName().equals(this.getAuthorName())){
			return true;
		}
		return false;
	}
	
	public int hashCode(){
		return super.hashCode();
	}
	
}
