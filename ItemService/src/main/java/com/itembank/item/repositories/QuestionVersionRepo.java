package com.itembank.item.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.item.Entity.QuestionVersion;
import com.itembank.item.Model.QuestionIdVersion;

public interface QuestionVersionRepo extends JpaRepository<QuestionVersion, QuestionIdVersion>{

	public List<QuestionVersion> findByQuesIdVersion_QuestionId(Integer id); 

}
