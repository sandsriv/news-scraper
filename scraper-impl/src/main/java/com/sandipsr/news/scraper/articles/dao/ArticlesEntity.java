package com.sandipsr.news.scraper.articles.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sandipsr.news.scraper.author.dao.AuthorEntity;
import com.sandipsr.news.scraper.base.annotation.SearchFields;
import com.sandipsr.news.scraper.base.dao.BaseEntity;

@Entity
@Table(name= "Articles")
public class ArticlesEntity implements BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595492609484339306L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ARTICLE_ID")
	@SearchFields(enable = false)
	private Long articleId;
	
	@Column(name = "TITLE")
	@SearchFields(enable = true)
	private String title;
	
	@Column(name = "ARTICLE_TYPE")
	@SearchFields(enable = true)
	private String type;
	
	@Column(name = "DESCRIPTION")
	@SearchFields(enable = true)
	private String decription;
	
	@Column(name = "CREATED_ON")	
	private Date   createdOn;

	@Column(name = "UPDATED_ON")
	private Date   updatedOn;
	
	@Column(name = "UPDATED_BY")	
	private String updatedBy;
	
	/*@Filters({
		 @Filter(name="authorFilter", condition=":name like age "),
	})*/
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "ARTICLES_AUTHOR_REL", joinColumns = { @JoinColumn(name = "ARTICLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHOR_ID") })
	private Set<AuthorEntity> authors = new HashSet<AuthorEntity>(0);
	
	public Set<AuthorEntity> getAuthors() {
		return this.authors;
	}
	
	public void setAuthors(Set<AuthorEntity> authors) {
		this.authors = authors;
	}

	public ArticlesEntity() {
		super();
	}

	public ArticlesEntity(String title, String type) {
		super();
		this.title = title;
		this.type = type;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public Date createdOn() {
		return getCreatedOn();
	}

	@Override
	public Date updatedOn() {
		return getUpdatedOn();
	}

	@Override
	public String updatedBy() {
		return getUpdatedBy();
	}

}
