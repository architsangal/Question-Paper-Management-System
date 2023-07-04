package com.itembank.item.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.item.Entity.SubjectiveQ;
import com.itembank.item.Model.QuestionIdVersion;

public interface SubjectiveQRepo extends JpaRepository<SubjectiveQ, QuestionIdVersion>{

}
