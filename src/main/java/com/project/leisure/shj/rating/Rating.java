package com.project.leisure.shj.rating;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

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

    @NotBlank(message = "리뷰를 입력해주세요.")


    private String review;


    @Column(name = "create_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
    
    @Column(name = "report")
    private String report;
}
   
  

