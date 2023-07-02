package com.project.leisure.shj.rating;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


@Service
@Transactional
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    public Rating saveRating(Rating rating) {
        System.out.println("Before saving: " + rating);  // Or use a logger
        Rating savedRating = ratingRepository.save(rating);
        System.out.println("After saving: " + savedRating);  // Or use a logger
        return savedRating;
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
        
        
    }
	public  Optional<Rating> findById(Long ratingId) {
		// Here we are finding the Rating by id
		return ratingRepository.findById(ratingId);
				//.orElse(null);
	}


	public void save(Rating rating) {
		// Here we are saving the Rating
		ratingRepository.save(rating);
	}

	public boolean isDuplicateReason(String report) {
		// TODO: Check if the reason is duplicate
		return false;
	}

	
}
