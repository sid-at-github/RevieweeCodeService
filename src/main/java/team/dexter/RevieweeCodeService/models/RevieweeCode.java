package team.dexter.RevieweeCodeService.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "revieweeCode")
public class RevieweeCode {

	private String username;
	private String title;
	private String code;
	private List<String> tags;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
