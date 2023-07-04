package com.itembank.papergen.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.papergen.Entity.QuestionVersion;
import com.itembank.papergen.Model.QuestionIdVersion;

public interface QuestionVersionRepo extends JpaRepository<QuestionVersion, QuestionIdVersion>{

	public List<QuestionVersion> findByQuesIdVersion_QuestionId(Integer id); 

}
