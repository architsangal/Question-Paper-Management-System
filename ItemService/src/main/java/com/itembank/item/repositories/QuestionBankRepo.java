package com.itembank.item.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.item.Entity.QuestionBank;

public interface QuestionBankRepo extends JpaRepository<QuestionBank, Integer>{

	public List<QuestionBank> findByAuthorId(Integer id); 
}
