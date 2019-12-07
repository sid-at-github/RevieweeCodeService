package team.dexter.RevieweeCodeService.daos;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import team.dexter.RevieweeCodeService.documents.RevieweeCodeDocument;

@Repository
public interface RevieweeCodeDocumentDao extends ElasticsearchRepository<RevieweeCodeDocument, String> {

	public List<RevieweeCodeDocument> findByTags(String tags);

	public List<RevieweeCodeDocument> findByRevieweeCodeId(String revieweeCodeId);
}
