package com.itembank.item.Entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.itembank.item.Model.QuestionIdVersion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionVersion {

    @EmbeddedId
    private QuestionIdVersion quesIdVersion;
	
	@Column(nullable = false)	
	private int type;

	public QuestionIdVersion getQuesIdVersion() {
		return quesIdVersion;
	}

	public void setQuesIdVersion(QuestionIdVersion quesIdVersion) {
		this.quesIdVersion = quesIdVersion;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public QuestionVersion() {
		super();
	}

	public QuestionVersion(QuestionIdVersion quesIdVersion, int type) {
		super();
		this.quesIdVersion = quesIdVersion;
		this.type = type;
	}
}
