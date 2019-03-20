package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByCategory(String category);

}
