package com.project.leisure.shj.rating;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;




@Getter
@Setter
@Entity
@Table(name = "ratings2")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Integer stars;

    @Column(nullable = false)
    private String review;

//    @Column
//    @CreatedDate
//    private LocalDate createAt;
  
//    @Column(name = "created_at")
//    private ZonedDateTime createdAt;
 // 필드명을 "created_at"으로 수정
//    @Column(name = "create_at")
//    private LocalDateTime createdAt;
    @Column(name = "create_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
    
    @Column(name = "report")
    private String report;
}
   
  

