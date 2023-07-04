package com.itembank.papergen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.papergen.Entity.IdVersionSequence;

public interface IdVersionSeqRepo extends JpaRepository<IdVersionSequence, Integer>{

}
