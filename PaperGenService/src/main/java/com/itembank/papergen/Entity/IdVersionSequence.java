package com.itembank.papergen.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdVersionSequence {

	@Id
	private int questionId;

	@Column
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

	public IdVersionSequence() {
		super();
	}

	public IdVersionSequence(int questionId, int version) {
		super();
		this.questionId = questionId;
		this.version = version;
	}
}
