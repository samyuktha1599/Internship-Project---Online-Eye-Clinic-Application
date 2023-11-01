package com.g6.onlineeyecare.spectacles.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.g6.onlineeyecare.spectacles.dto.Spectacles;

@Repository
public interface ISpectaclesRepository extends JpaRepository<Spectacles, Integer>{

	@Query("Select a from Spectacles a where a.spectaclesRating=:rating")
	List<Spectacles> viewSpectaclesByRating(@Param("rating")int rating);
	
	@Query("Select a from Spectacles a where a.spectaclesModel=:model")
	List<Spectacles> viewByModel(@Param("model")String model);
	
	@Query("Select a from Spectacles a where a.spectaclesCost BETWEEN :range1 and :range2")
	List<Spectacles> viewByCostRange(@Param("range1")double cost1,@Param("range2")double cost2);
	
	@Query("Select a from Spectacles a ORDER BY a.spectaclesCost desc")
    List<Spectacles> viewByCostHighToLow();
	
	@Query("Select a from Spectacles a ORDER BY a.spectaclesCost ")
    List<Spectacles> viewByCostLowToHigh();
}
