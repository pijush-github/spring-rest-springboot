package com.learning.example.basic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.example.basic.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
