package com.project.leisure.shj.rating;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import jakarta.validation.Valid;

import lombok.Getter;
import lombok.Setter;

@Controller
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private RatingService ratingService;
	private LocalDateTime createdAt;
    
    @GetMapping("/save-rating")
    public String showFormAndRatings(Model model) {
        model.addAttribute("rating", new Rating()); // form data
        model.addAttribute("ratings", ratingService.getAllRatings()); // ratings list
        return "shj-save-rating";
    }

    @PostMapping("/save-rating")
    public String saveRating(@Valid @ModelAttribute Rating rating, BindingResult bindingResult, Model model) {

    @PostMapping("/save-rating")
    public String saveRating(@ModelAttribute Rating rating, Model model) {
>>>>>>> 78721e0f4bbb0327398b7366e6233298c0f0a019
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        rating.setUsername(currentUserName);


        if (bindingResult.hasErrors()) {
            model.addAttribute("rating", rating); // Keep entered data
            model.addAttribute("ratings", ratingService.getAllRatings());
            model.addAttribute("currentUserName", currentUserName); // 작성자 정보 추가
            return "shj-save-rating";
        }

        ratingService.saveRating(rating);

        ratingService.saveRating(rating);


        // Save the new rating and render the form and ratings again.
        model.addAttribute("rating", new Rating());
        model.addAttribute("ratings", ratingService.getAllRatings());
        model.addAttribute("currentUserName", currentUserName); // 작성자 정보 추가

        return "shj-save-rating";
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationExceptions(
      DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
        
        String errorMessage = "모든 필드를 올바르게 입력해주세요."; // 또는 ex.getMessage() 사용 가능
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        
        return "redirect:/save-rating"; // 원래의 페이지 경로로 수정하세요
    }


    
    
    @PostMapping("/report")
    public ResponseEntity<String> handleReportRequest(@ModelAttribute ReportForm reportForm) {

   
    	 Long ratingId = Long.valueOf(reportForm.getRatingId());
    	    String report = reportForm.getReport();

    	   // Rating rating = ratingService.findById(ratingId);
    	    Optional<Rating> optionalRating = ratingService.findById(ratingId);
        
    	    if (optionalRating.isEmpty()) {
      //  if (rating == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 게시글을 찾을 수 없습니다.");
        }



    	    Rating rating = optionalRating.get(); // 추가한 코드
    	    rating.setReport(report);
    	    ratingService.save(rating);

    	    return ResponseEntity.ok("신고가 접수되었습니다.");
    	}
    } 



