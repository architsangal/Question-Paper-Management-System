package com.itembank.item.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.item.Entity.MultipleChoiceQ;
import com.itembank.item.Model.MCQCompositeKey;

public interface MultipleChoiceQRepo extends JpaRepository<MultipleChoiceQ, MCQCompositeKey>{

	List<MultipleChoiceQ> findByMcqKey_QuestionIdAndMcqKey_Version(int questionId, int version);

}
