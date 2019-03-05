package com.sandipsr.news.scraper.author.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Specifies methods used to perform CRUD operation on Author entity.  
 * @author sandipsr
 * @since  2019
 *
 */
//@Repository("authorRepository")
@Repository
@Qualifier("authorRepository")
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long>{
	
	/**
	 * Find the Author using the SearchText for the field ->name. which is stored in the DB.
	 * @param authorNameStr
	 * @param authorGenderStr
	 * @return
	 */
	//@Query("SELECT auth FROM AuthorEntity auth WHERE LOWER(auth.name) like LOWER(:nameStr)")
	//@Query("SELECT auth FROM AuthorEntity auth WHERE LOWER(auth.name) like (:nameStr)")
	//@Query("SELECT auth FROM AuthorEntity auth WHERE auth.name REGEXP (:nameStr)")
	
	@Query("SELECT auth FROM AuthorEntity auth WHERE LOWER(auth.name) like LOWER(CONCAT('%',:searchNameText, '%')) OR " +
            "LOWER(auth.gender) LIKE LOWER(CONCAT('%',:sarchGenderText, '%'))")
	public List<AuthorEntity> findByNameString(@Param("searchNameText") String authorNameStr, @Param("sarchGenderText") String authorGenderStr);
	
	@Query("SELECT auth FROM AuthorEntity auth WHERE LOWER(auth.name) like LOWER(CONCAT('%',:searchNameText, '%')) OR " +
            "LOWER(auth.gender) LIKE LOWER(CONCAT('%',:sarchGenderText, '%'))")
	public List<AuthorEntity> findByNameByMultiTextSearch(@Param("searchNameText") String authorNameStr, @Param("sarchGenderText") String authorGenderStr);
}
