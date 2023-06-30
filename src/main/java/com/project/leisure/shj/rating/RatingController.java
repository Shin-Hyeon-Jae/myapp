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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//    @PostMapping("/save-rating")
//    public String saveRating(@ModelAttribute Rating rating, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName();
//        rating.setUsername(currentUserName);
//        
//        // createdAt 필드 설정
//        ZonedDateTime zonedDateTime = ZonedDateTime.now();  // 현재 시간
//        rating.setCreated_at(zonedDateTime);  // 'setCreatedAt'는 'Rating' 클래스에 존재해야 함
//
//        ratingService.saveRating(rating);
//
//        // Save the new rating and render the form and ratings again.
//        model.addAttribute("rating", new Rating());
//        model.addAttribute("ratings", ratingService.getAllRatings());
//        return "shj-save-rating";
//    }

//    @PostMapping("/save-rating")
//    public String saveRating(@ModelAttribute Rating rating, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUserName = authentication.getName();
//        rating.setUsername(currentUserName);
//        ratingService.saveRating(rating);
//
//        // Save the new rating and render the form and ratings again.
//        model.addAttribute("rating", new Rating());
//        model.addAttribute("ratings", ratingService.getAllRatings());
//        return "shj-save-rating";
//    }
    @PostMapping("/save-rating")
    public String saveRating(@ModelAttribute Rating rating, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        rating.setUsername(currentUserName);

        ratingService.saveRating(rating);

        // Save the new rating and render the form and ratings again.
        model.addAttribute("rating", new Rating());
        model.addAttribute("ratings", ratingService.getAllRatings());
        model.addAttribute("currentUserName", currentUserName); // 작성자 정보 추가

        return "shj-save-rating";
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public String handleValidationExceptions(
//      MethodArgumentNotValidException ex, RedirectAttributes redirectAttributes) {
//        
//        String errorMessage = ex.getBindingResult().getAllErrors().stream()
//              .map(DefaultMessageSourceResolvable::getDefaultMessage)
//              .collect(Collectors.joining(", "));
//        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
//        
//        return "redirect:/save-rating"; // 원래의 페이지 경로로 수정하세요
//    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationExceptions(
      DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
        
        String errorMessage = "모든 필드를 올바르게 입력해주세요."; // 또는 ex.getMessage() 사용 가능
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        
        return "redirect:/save-rating"; // 원래의 페이지 경로로 수정하세요
    }
//    @RestController
//    @RequestMapping("/ratings")
//    public class RatingController {
//
//        private final RatingService ratingService;
//
//        public RatingController(RatingService ratingService) {
//            this.ratingService = ratingService;
//        }

//        @PostMapping("/{id}/report")
//        public ResponseEntity<String> submitReport(@PathVariable Long id, @RequestBody String report) {
//            Rating rating = ratingService.getRatingById(id);
//            if (rating == null) {
//                return ResponseEntity.notFound().build();
//            }
//
//            ratingService.saveRatingWithReport(rating, report);
//
//            return ResponseEntity.ok("Report submitted successfully.");
//        }
//    @PostMapping("/report")
//    public ResponseEntity<String> handleReport(@RequestParam("reason") String reason) {
//        try {
//            // Rating 객체 생성
//            Rating rating = new Rating();
//            rating.setReport(reason);
//
//            // Rating 객체 저장
//            ratingRepository.save(rating);
//    @PostMapping("/report")
//    public String handleReportRequest(@RequestParam("ratingId") Long ratingId, @RequestParam("reason") String reason) {
//        Rating rating = ratingService.findById(ratingId); // 해당 게시글을 조회하여 Rating 객체를 가져옵니다.
//        rating.setReport(reason); // 'report' 필드에 신고 사유를 할당합니다.
//        ratingService.save(rating); // 변경된 Rating 객체를 저장합니다.
//        // ... return "신고가 접수되었습니다.";
//        return "신고가 접수되었습니다.";
//    } catch (Exception e) {
//        return "신고 처리 중 오류가 발생했습니다.";
//    }
//}
            // 처리 결과 반환
          //  return ResponseEntity.ok("신고가 접수되었습니다.");
         //catch (Exception e) {
            // 처리 실패 시 에러 처리
           // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("신고 처리 중 오류가 발생했습니다.");
     
//    @PostMapping("/report")
//    public String handleReportRequest(@RequestParam("ratingId") Long ratingId, @RequestParam("reason") String reason) {
//        try {
//            Rating rating = ratingService.findById(ratingId);
//            rating.setReport(reason);
//            ratingService.save(rating);
//            return "신고가 접수되었습니다.";
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("신고 처리 중 오류가 발생했습니다.");
//        }
//    @PostMapping("/report")
//    public ResponseEntity<String> handleReportRequest(@RequestParam("ratingId") Long ratingId, @RequestParam("reason") String reason) {
//        try {
//            Rating rating = ratingService.findById(ratingId);
//            rating.setReport(reason);
//            ratingService.save(rating);
//            // ...
//            
//            // 처리 결과 반환
//            return ResponseEntity.ok("신고가 접수되었습니다.");
//        } catch (Exception e) {
//            // 처리 실패 시 에러 처리
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("신고 처리 중 오류가 발생했습니다.");
//        }
//    }
//    @PostMapping("/report")
//    public String handleReportRequest(@RequestParam("ratingId") Long ratingId, @RequestParam("reason") String reason) {
//        // 중복된 reason 값을 확인하여 처리
//        if (ratingService.isDuplicateReason(reason)) {
//            // 중복된 reason 값이 이미 존재하는 경우 처리 방법을 선택합니다.
//            // 예를 들어, 오류 메시지를 반환하거나 처리를 거부할 수 있습니다.
//            return "Duplicate reason is not allowed.";
//        }
//
//        Rating rating = ratingService.findById(ratingId);
//        rating.setReport(reason);
//        ratingService.save(rating);
//        
//        // 처리 결과 반환
//        return "신고가 접수되었습니다.";
//    }
//    @PostMapping("/report")
//    public ResponseEntity<String> handleReportRequest(@RequestParam("ratingId") Long ratingId, @RequestParam("reason") String reason) {
//        Rating rating = ratingService.findById(ratingId); // 해당 게시글을 조회하여 Rating 객체를 가져옵니다.
//
//        if (rating != null) {
//            rating.setReport(reason); // 'report' 필드에 신고 사유를 할당합니다.
//            ratingService.save(rating); // 변경된 Rating 객체를 저장합니다.
//            // 성공적으로 처리되었음을 클라이언트에 응답합니다.
//            return ResponseEntity.ok("신고가 접수되었습니다.");
//        } else {
//            // 해당 ratingId에 해당하는 Rating 객체가 없는 경우 처리 실패로 처리합니다.
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 게시글을 찾을 수 없습니다.");
//        }
//    }
//    @PostMapping("/report")
//    public ResponseEntity<String> handleReportRequest(@RequestParam("ratingId") Long ratingId, @RequestParam("report") String report) {
//        Rating rating = ratingService.findById(ratingId); // 해당 게시글을 조회하여 Rating 객체를 가져옵니다.
//
//        if (rating == null) {
//            rating = new Rating(); // 새로운 Rating 객체를 생성합니다.
//            rating.setId(ratingId); // 새로운 Rating 객체에 ratingId를 설정합니다.
//        }
//
//        rating.setReport(report); // 'report' 필드에 신고 사유를 할당합니다.
//        ratingService.save(rating); // 변경된 Rating 객체를 저장합니다.
//        
//        // 성공적으로 처리되었음을 클라이언트에 응답합니다.
//        return ResponseEntity.ok("신고가 접수되었습니다.");
//    }
   

    //415에러 @RequestBody 를 @ModelAttribute로 변경 
    
    @PostMapping("/report")
    public ResponseEntity<String> handleReportRequest(@ModelAttribute ReportForm reportForm) {
    // public ResponseEntity<String> handleReportRequest(@ModelAttribute Map<String, String> payload) {
//        Long ratingId = Long.valueOf(payload.get("ratingId"));
//        String report = payload.get("report");
//
//        Rating rating = ratingService.findById(ratingId);
    	 Long ratingId = Long.valueOf(reportForm.getRatingId());
    	    String report = reportForm.getReport();

    	   // Rating rating = ratingService.findById(ratingId);
    	    Optional<Rating> optionalRating = ratingService.findById(ratingId);
        
    	    if (optionalRating.isEmpty()) {
      //  if (rating == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 게시글을 찾을 수 없습니다.");
        }

//        rating.setReport(report);
//        ratingService.save(rating);
//
//        return ResponseEntity.ok("신고가 접수되었습니다.");
//    }
    	    Rating rating = optionalRating.get(); // 추가한 코드
    	    rating.setReport(report);
    	    ratingService.save(rating);

    	    return ResponseEntity.ok("신고가 접수되었습니다.");
    	}
    } 



