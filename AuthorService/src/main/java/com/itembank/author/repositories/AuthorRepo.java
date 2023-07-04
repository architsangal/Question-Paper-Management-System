package com.itembank.author.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itembank.author.Entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer>{
	
	public Author findByUsername(String username);

}
