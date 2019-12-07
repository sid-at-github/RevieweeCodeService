package team.dexter.RevieweeCodeService.adapters;

import team.dexter.RevieweeCodeService.documents.RevieweeCodeDocument;
import team.dexter.RevieweeCodeService.models.RevieweeCode;

public class RevieweeCodeAdapter {

	public static RevieweeCodeDocument getRevieweeCodeDocument(RevieweeCode revieweeCode) {
		RevieweeCodeDocument revieweeCodeDocument = new RevieweeCodeDocument();
		revieweeCodeDocument.setRevieweeCodeId(revieweeCode.getId());
		revieweeCodeDocument.setTags(revieweeCode.getTags());
		revieweeCodeDocument.setId(revieweeCode.getId());
		return revieweeCodeDocument;
	}
}
