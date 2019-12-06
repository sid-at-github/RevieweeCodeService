package team.dexter.RevieweeCodeService.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.dexter.CodeReviewerCommons.dtos.RevieweeCodeRequestDto;
import team.dexter.RevieweeCodeService.adapters.RevieweeCodeAdapter;
import team.dexter.RevieweeCodeService.daos.RevieweeCodeDao;
import team.dexter.RevieweeCodeService.daos.RevieweeCodeDocumentDao;
import team.dexter.RevieweeCodeService.documents.RevieweeCodeDocument;
import team.dexter.RevieweeCodeService.exceptions.InternalServerError;
import team.dexter.RevieweeCodeService.models.Feedback;
import team.dexter.RevieweeCodeService.models.RevieweeCode;

@Service
public class RevieweeCodeService {

	@Autowired
	private RevieweeCodeDao revieweeCodeDao;

	@Autowired
	private RevieweeCodeDocumentDao revieweeCodeDocumentDao;

	public void createRevieweeCode(RevieweeCode revieweeCode) {
		try {
			revieweeCodeDao.save(revieweeCode);
			revieweeCodeDocumentDao.save(RevieweeCodeAdapter.getRevieweeCodeDocument(revieweeCode));
		} catch (Exception e) {
			throw new InternalServerError();
		}
	}

	public List<RevieweeCode> getRevieweeCodeList(RevieweeCodeRequestDto revieweeCodeRequestDto) {
		List<RevieweeCode> revieweeCodeList = new ArrayList<>();
		try {
			List<RevieweeCodeDocument> documentList = revieweeCodeDocumentDao
					.findByTags(revieweeCodeRequestDto.getTag());
			List<String> revieweeCodeIdList = documentList.stream().map(document -> document.getRevieweeCodeId())
					.collect(Collectors.toList());
			revieweeCodeList = revieweeCodeDao.findByIdIn(revieweeCodeIdList);
		} catch (Exception e) {
			throw new InternalServerError();
		}
		return revieweeCodeList;
	}
	
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
