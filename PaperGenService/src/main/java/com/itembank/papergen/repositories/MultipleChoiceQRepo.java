package com.itembank.papergen.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.papergen.Entity.MultipleChoiceQ;
import com.itembank.papergen.Model.MCQCompositeKey;

public interface MultipleChoiceQRepo extends JpaRepository<MultipleChoiceQ, MCQCompositeKey>{

	List<MultipleChoiceQ> findByMcqKey_QuestionIdAndMcqKey_Version(int questionId, int version);

}
