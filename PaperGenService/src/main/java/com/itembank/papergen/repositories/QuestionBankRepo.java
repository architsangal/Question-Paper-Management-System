package com.itembank.papergen.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.papergen.Entity.QuestionBank;

public interface QuestionBankRepo extends JpaRepository<QuestionBank, Integer>{

	public List<QuestionBank> findByDomain(String domain); 
	public List<QuestionBank> findByAuthorId(Integer id); 

}
