package com.heesuk.springbootwithwebflux.springbootwithwebflux;

import com.heesuk.springbootwithwebflux.springbootwithwebflux.repository.Book;
import com.heesuk.springbootwithwebflux.springbootwithwebflux.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@Slf4j
@RestController
public class SpringbootWithWebfluxApplication {

  @Autowired
  BookRepository bookRepository;

  @GetMapping("/v1/hello")
  public Mono<String> getHelloRoute() {
    return Mono.just("HELLO").log();
  }

  @GetMapping("/v1/books")
  public Mono<Book> getHelloBookRoute() {
    Book newBook = new Book();
    newBook.setName("TEST");
    return Mono.just(bookRepository.save(newBook));
  }

  public Mono<Void> sleep() throws InterruptedException {

  return Mono.just("TEST").then().doOnSuccess(x -> {
    try {
      Thread.sleep(3000);
    } catch (Exception e) {

    }
  });
  }

  @GetMapping("/v1/test")
  public Mono<Void> test() throws InterruptedException {
    return sleep();
  }

  @GetMapping("/v1/books-with-elastic")
  public Mono<Book> getHelloBookElasticRoute() {
    Book newBook = new Book();
    newBook.setName("TEST");
    return Mono.just(bookRepository.save(newBook)).subscribeOn(Schedulers.elastic());
  }


  public static void main(String[] args) {
    System.setProperty("server.port", "8080");
    SpringApplication.run(SpringbootWithWebfluxApplication.class, args);
  }

}
