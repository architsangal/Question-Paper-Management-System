package com.itembank.papergen.Model;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class MCQCompositeKey implements Serializable {

	@Column(nullable = false)	
	private int questionId;

	@Column(nullable = false)	
	private int version;

	@Column(nullable = false)	
	private int optionNum;

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

	public int getOptionNum() {
		return optionNum;
	}

	public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
	}

	public MCQCompositeKey() {
		super();
	}

	public MCQCompositeKey(int questionId, int version, int optionNum) {
		super();
		this.questionId = questionId;
		this.version = version;
		this.optionNum = optionNum;
	}
}
