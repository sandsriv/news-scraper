package com.sandipsr.news.scraper.articles.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("articlesRepository")
public interface ArticlesRepository extends CrudRepository<ArticlesEntity, Long>{

}
