package team.dexter.RevieweeCodeService.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "revieweecode")
public class RevieweeCodeDocument {

	@Id
	private String id;

	private String revieweeCodeId;
	private List<String> tags;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRevieweeCodeId() {
		return revieweeCodeId;
	}

	public void setRevieweeCodeId(String revieweeCodeId) {
		this.revieweeCodeId = revieweeCodeId;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
