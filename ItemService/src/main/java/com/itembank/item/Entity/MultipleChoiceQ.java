package com.itembank.item.Entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.itembank.item.Model.MCQCompositeKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQ {

    @EmbeddedId
    private MCQCompositeKey mcqKey;
	
	@Column(nullable = false)	
	private String quesText;

	@Column(nullable = false)	
	private Boolean isCorrect;

	@Column(nullable = false)	
	private String optionText;


	public MCQCompositeKey getMcqKey() {
		return mcqKey;
	}

	public void setMcqKey(MCQCompositeKey mcqKey) {
		this.mcqKey = mcqKey;
	}

	public String getQuesText() {
		return quesText;
	}

	public void setQuesText(String quesText) {
		this.quesText = quesText;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public MultipleChoiceQ() {
		super();
	}

	public MultipleChoiceQ(MCQCompositeKey mcqKey, String quesText, Boolean isCorrect, String optionText) {
		super();
		this.mcqKey = mcqKey;
		this.quesText = quesText;
		this.isCorrect = isCorrect;
		this.optionText = optionText;
	}

}
