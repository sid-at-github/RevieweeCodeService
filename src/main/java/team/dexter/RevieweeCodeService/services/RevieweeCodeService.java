package team.dexter.RevieweeCodeService.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeRequestDto;
import team.dexter.RevieweeCodeService.daos.RevieweeCodeDao;
import team.dexter.RevieweeCodeService.exceptions.InternalServerError;
import team.dexter.RevieweeCodeService.models.Feedback;
import team.dexter.RevieweeCodeService.models.RevieweeCode;

@Service
public class RevieweeCodeService {

	@Autowired
	private RevieweeCodeDao revieweeCodeDao;
	@Caching(
			put= { @CachePut(value= "revieweeCodeCache", key= "#revieweeCode.codeId") },
			evict= { @CacheEvict(value= "allRevieweeCodeCache", allEntries= true) }
		)
	public void createRevieweeCode(RevieweeCode revieweeCode) {
		try {
			revieweeCodeDao.save(revieweeCode);
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}

	@Cacheable(value= "allRevieweeCodeCache", unless= "#result.size() == 0")	
	public List<RevieweeCode> getRevieweeCodeList(RevieweeCodeRequestDto revieweeCodeRequestDto) {
		List<RevieweeCode> revieweeCodeList = new ArrayList<>();
		try {
			revieweeCodeList = revieweeCodeDao.findAll();
		} catch (Exception e) {
			throw new InternalServerError();
		}
		return revieweeCodeList;
	}
	
	@Cacheable(value= "revieweeCodeCache", key= "#codeId")		
	public RevieweeCode getRevieweeCode(String codeId) {
		RevieweeCode revieweeCode = revieweeCodeDao.findById(codeId).get();
		return revieweeCode;
	}

	public void giveFeedback(String codeId, Feedback feedback) {
		try {
			RevieweeCode revieweeCode = revieweeCodeDao.findById(codeId).get();
			List<Feedback> existingFeedbacks = revieweeCode.getFeedbacks();
			if (existingFeedbacks == null) {
				List<Feedback> feedbacks = new ArrayList<>();
				feedbacks.add(feedback);
				revieweeCode.setFeedbacks(feedbacks);
			} else {
				existingFeedbacks.add(feedback);
			}
			revieweeCodeDao.save(revieweeCode);
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}
}
