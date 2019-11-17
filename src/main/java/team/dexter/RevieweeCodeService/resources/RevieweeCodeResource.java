package team.dexter.RevieweeCodeService.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeDto;
import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeRequestDto;
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
	public List<RevieweeCode> getRevieweeCode(RevieweeCodeRequestDto revieweeCodeRequestDto) {
		return revieweeCodeService.getRevieweeCodeList(revieweeCodeRequestDto);
	}
}
