package com.heesuk.springbootwithwebflux.springbootwithwebflux.repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
