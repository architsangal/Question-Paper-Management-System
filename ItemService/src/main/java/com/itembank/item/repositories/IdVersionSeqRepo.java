package com.itembank.item.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.item.Entity.IdVersionSequence;

public interface IdVersionSeqRepo extends JpaRepository<IdVersionSequence, Integer>{

}
