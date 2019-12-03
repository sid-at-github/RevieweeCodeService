package team.dexter.RevieweeCodeService.models;

import java.io.Serializable;

public class Feedback implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9009782098794550891L;
	private String feebackBy;
	private String comment;

	public String getFeebackBy() {
		return feebackBy;
	}

	public void setFeebackBy(String feebackBy) {
		this.feebackBy = feebackBy;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
