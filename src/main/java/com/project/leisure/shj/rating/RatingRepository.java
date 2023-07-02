package com.project.leisure.shj.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;


@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

  //  @Modifying
    //@Query("UPDATE Rating r SET r.reason = :reason WHERE r.id = :id")
   // void saveWithReason(@Param("id") Long id, @Param("report") String report);

	

//	Rating saveWithReport(Rating rating, String report);

	

}


