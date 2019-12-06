package team.dexter.RevieweeCodeService.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import team.dexter.CodeReviewerCommons.dtos.FeedbackDto;
import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeDto;
import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeRequestDto;
import team.dexter.RevieweeCodeService.exceptions.InternalServerError;
import team.dexter.RevieweeCodeService.models.Feedback;
import team.dexter.RevieweeCodeService.models.RevieweeCode;
import team.dexter.RevieweeCodeService.services.RevieweeCodeService;

@RestController
public class RevieweeCodeResource {

	@Autowired
	private RevieweeCodeService revieweeCodeService;

	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping("/revieweeCode")
	public void createRevieweeCode(@RequestBody RevieweeCodeDto revieweeCodeDto) {
		RevieweeCode revieweeCode = objectMapper.convertValue(revieweeCodeDto, RevieweeCode.class);
		revieweeCodeService.createRevieweeCode(revieweeCode);
	}

	@GetMapping("/revieweeCode")
	public List<RevieweeCode> getRevieweeCodeList(RevieweeCodeRequestDto revieweeCodeRequestDto) {
		return revieweeCodeService.getRevieweeCodeList(revieweeCodeRequestDto);
	}
	
	@GetMapping("/revieweeCode/{codeId}")
	public RevieweeCode getRevieweeCode(@PathVariable String codeId) {
		return revieweeCodeService.getRevieweeCode(codeId);
	}

	@PutMapping("/revieweeCode/{codeId}")
	public void giveFeedback(@PathVariable String codeId, @RequestBody FeedbackDto feedbackDto) {
		try {
			Feedback feedback = objectMapper.convertValue(feedbackDto, Feedback.class);
			revieweeCodeService.giveFeedback(codeId, feedback);
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}

}
