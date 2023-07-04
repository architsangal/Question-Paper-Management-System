package com.itembank.papergen.Model;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class QuestionIdVersion implements Serializable {
	
	@Column(nullable = false)	
	private int questionId;

	@Column(nullable = false)	
	private int version;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public QuestionIdVersion() {
		super();
	}

	public QuestionIdVersion(int questionId, int version) {
		super();
		this.questionId = questionId;
		this.version = version;
	}

}
