package com.itembank.papergen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.papergen.Entity.SubjectiveQ;
import com.itembank.papergen.Model.QuestionIdVersion;

public interface SubjectiveQRepo extends JpaRepository<SubjectiveQ, QuestionIdVersion>{

}
