package com.itembank.papergen.Entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.itembank.papergen.Model.QuestionIdVersion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectiveQ {

	@EmbeddedId
    private QuestionIdVersion quesIdVersion;
	
	@Column(nullable = false)	
	private String quesText;

	@Column(nullable = false)	
	private String ans;

	public QuestionIdVersion getQuesIdVersion() {
		return quesIdVersion;
	}
	public void setQuesIdVersion(QuestionIdVersion quesIdVersion) {
		this.quesIdVersion = quesIdVersion;
	}
	public String getQuesText() {
		return quesText;
	}
	public void setQuesText(String quesText) {
		this.quesText = quesText;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}


    public SubjectiveQ() {
		super();
	}

	public SubjectiveQ(QuestionIdVersion quesIdVersion, String quesText, String ans) {
		super();
		this.quesIdVersion = quesIdVersion;
		this.quesText = quesText;
		this.ans = ans;
	}
}
