package com.sandipsr.news.scraper.author.dao;

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

import com.sandipsr.news.scraper.articles.dao.ArticlesEntity;
import com.sandipsr.news.scraper.base.annotation.SearchFields;
import com.sandipsr.news.scraper.base.dao.BaseEntity;

@Entity
@Table(name= "Authors")
public class AuthorEntity implements BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1274934569042349126L;
	
	public AuthorEntity(){
		
	}
	public AuthorEntity(String name, String gender){
		this.name 	= name;
		this.gender = gender;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTHOR_ID")
	@SearchFields(enable = false)
	private Long   Id;
	
	@SearchFields(enable = true)
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "GENDER")
	@SearchFields(enable = true)
	private String gender;
	
	@Column(name = "CREATED_ON")	
	private Date   createdOn;

	@Column(name = "UPDATED_ON")
	private Date   updatedOn;
	
	@Column(name = "UPDATED_BY")	
	private String updatedBy;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "ARTICLES_AUTHOR_REL", joinColumns = { @JoinColumn(name = "AUTHOR_ID") }, inverseJoinColumns = { @JoinColumn(name = "ARTICLE_ID") })
	private Set<ArticlesEntity> articles = new HashSet<ArticlesEntity>(0);
	
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
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Set<ArticlesEntity> getArticles() {
		return articles;
	}
	public void setArticles(Set<ArticlesEntity> articles) {
		this.articles = articles;
	}
	
}
