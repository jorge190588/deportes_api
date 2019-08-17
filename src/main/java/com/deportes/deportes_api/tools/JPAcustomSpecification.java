package com.deportes.deportes_api.tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("serial")
public class JPAcustomSpecification<T> {
	Logger logger = Logger.getLogger(JPAcustomSpecification.class);
	
	
	public Specification<T> getSpecification(final JSONArray searchCriteriaArray, final JSONArray orderCriteriaArray) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {  
				if (orderCriteriaArray!=null) {
					List<Order> orderList = getOrderList(orderCriteriaArray, root, builder);
					query.orderBy(orderList);	
				}
				if (searchCriteriaArray!=null) {
					List<Predicate> predicates = getPredicateList(root,builder, searchCriteriaArray);
	                return builder.and(predicates.toArray(new Predicate[predicates.size()]));	
				}else
					return null;
			}
		};
	}
    
    private List<Order> getOrderList(JSONArray orderCriteria, Root<T> root, CriteriaBuilder builder){
    	List<Order> orderList = new LinkedList<>();
    	JSONObject filterElement;
 		String attribute="",option="";
 		try {
			for(int index=0; index < orderCriteria.length(); index++) {
				
	        	filterElement = (JSONObject) orderCriteria.get(index);
	        	attribute = filterElement.get("id").toString();
				option = filterElement.get("option").toString();
				if (option.equals("asc"))
					orderList.add(builder.asc(root.get(attribute)));
				else 			 
					orderList.add(builder.desc(root.get(attribute)));
	        }	
		}catch(Exception ex) {
			logger.error(ex);
		}
    	return orderList;
    }
    
  
    
    private List<Predicate> getPredicateList(Root<?> root, CriteriaBuilder criteriaBuilder, JSONArray searchCriteriaArray){
        List<Predicate> predicates = new ArrayList<>();
        JSONObject filterElement;
		String attribute="",option="",value="";
		
		try {
			for(int index=0; index < searchCriteriaArray.length(); index++) {
	        	filterElement = (JSONObject) searchCriteriaArray.get(index);
	        	attribute = filterElement.get("id").toString();
				option = filterElement.get("option").toString();
				value = filterElement.get("value").toString();
	        	predicates.add(getPredicate(root,criteriaBuilder,option,attribute,value));
	        }	
		}catch(Exception ex) {
			logger.error(ex);
		}
        
        return predicates;
    }
    
    private Predicate getPredicate(Root<?> root, CriteriaBuilder criteriaBuilder,String option, String attribute, String value) {
    	Predicate predicate = null;
    	switch (option) {
        case "Igual":
        	predicate= criteriaBuilder.and(criteriaBuilder.equal(root.get(attribute), value));
            break;
        case "Contiene":
        	predicate=criteriaBuilder.and(criteriaBuilder.like(root.<String>get(attribute), "%"+value+"%"));
        	break;
        case "Inicia":
        	predicate=criteriaBuilder.and(criteriaBuilder.like(root.<String>get(attribute), "%"+value));
        	break;
        case "Finaliza":
        	predicate=criteriaBuilder.and(criteriaBuilder.like(root.<String>get(attribute), value+"%"));
        	break;
        case "Mayor":
        	predicate= criteriaBuilder.and(criteriaBuilder.greaterThan(root.<Integer>get(attribute), Integer.valueOf(value) ));
        	break;
        case "MayorIgual":
        	predicate= criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.<Integer>get(attribute), Integer.valueOf(value) ));
        	break;
        case "Menor":
        	predicate= criteriaBuilder.and(criteriaBuilder.lessThan(root.<Integer>get(attribute), Integer.valueOf(value) ));
        	break;
        case "MenorIgual":
        	predicate= criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.<Integer>get(attribute), Integer.valueOf(value) ));
        	break;
        default:
        	predicate= criteriaBuilder.and(criteriaBuilder.equal(root.get(attribute), value));
            logger.error("Error: Invalid Option: "+option.toString());
            break;
	    }
    	return predicate;
    }
    

}
