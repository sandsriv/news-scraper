package com.sandipsr.news.scraper.base.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sandipsr.news.scraper.articles.dao.ArticlesEntity;
import com.sandipsr.news.scraper.author.dao.AuthorEntity;
import com.sandipsr.news.scraper.base.annotation.SearchFields;
import com.sandipsr.news.scraper.base.criterias.FilterCriteria;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao{
	
	private static final Integer START_INDEX = 0;
	private static final Integer END_INDEX   = 5;
	
	/**
	 * <p> This method is used to create Query String with named parameter as Filter criteria. </p>
	 * @param queryStr Base Query String
	 * @param searchCriterias List of Criterias to be added in Where Clause.
	 * @param columns list of column names for entities. 
	 * @return the complete query with where clause.
	 * @since 2019. 
	 */
	public String createQueryStrWithCritera(String queryStr, List<FilterCriteria> searchCriterias, List<String> columns){
		StringBuilder querySB = new StringBuilder(queryStr);
		addWhereClauses(querySB, searchCriterias, columns);
		
		System.out.println("QUERIES befor executing : "+ querySB.toString());
		return querySB.toString();
	}
	
	
	private void addWhereClauses(StringBuilder sb, List<FilterCriteria> searchCriterias, List<String> columns){
		if(searchCriterias != null && searchCriterias.size() > 0){
			
			int criteriaLength = searchCriterias.size();
			int count = 0;
			boolean whereClauseAdded = false;
			
			for(FilterCriteria filter: searchCriterias){
				if(columns.contains(filter.getCriterianame())){
					
					if(!whereClauseAdded){
						sb.append(" WHERE ");
						whereClauseAdded = true;
					}
					
					if(count >= 1){
						sb.append(" OR ");
					}
					
					String exprParamname = (String) filter.getValue() ; 
					if(exprParamname!= null && exprParamname.length() > 5){
						sb.append(filter.getCriterianame() + " like ('%"+ exprParamname.substring(START_INDEX, END_INDEX )+"%')");	
					}else{
						sb.append(filter.getCriterianame() + " like ('%"+ exprParamname +"%')");
					}
					count++;
					
					if(criteriaLength-- > 1){
						//sb.append(" OR ");
					}	
				}
				
			}//For
			
			//Check if OR exists at last without any where clause;
//			int lastIndex = sb.lastIndexOf("OR");
//			if ( sb.charAt(lastIndex +2)+"" == "" ||  (sb.charAt(lastIndex +2)+"").isEmpty()){
//				sb.replace( (lastIndex-2) , sb.length(), sb.toString());
//				System.out.println("Replaced String-- "+ sb.toString());
//			}
		}
	}
	
	/**
	 * This class will return the list of fields which will be used for filtering the records.
	 * @return list of SearchFields
	 */
	public List<String> getConfiguredSearchFields(Class claz){
		List<String> fields =  new ArrayList<String>();
		
		Class annotClaz =   claz;  //AuthorEntity.class;
		for(Field f : annotClaz.getDeclaredFields()){
			SearchFields sf = f.getAnnotation(SearchFields.class);
			if(sf != null && sf.enable()){
				String field = f.getName();
				fields.add(field);
			}
		}
		System.out.println("Search Fields applicable: "+ fields);
		return fields;
	}
	
	public static void main(String[] args) {
		new BaseDaoImpl().getConfiguredSearchFields(AuthorEntity.class);
		new BaseDaoImpl().getConfiguredSearchFields(ArticlesEntity.class);
	}
}
