package com.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagementsystem.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
