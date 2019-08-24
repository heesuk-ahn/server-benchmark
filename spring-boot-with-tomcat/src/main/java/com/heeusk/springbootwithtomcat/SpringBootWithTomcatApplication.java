package com.heeusk.springbootwithtomcat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@Slf4j
public class SpringBootWithTomcatApplication {

  @GetMapping("/v1/hello")
  public String getHelloWithBlocking() throws InterruptedException {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject("http://localhost:8083/v1/remote-hello", String.class);
  }

  @GetMapping("/v1/helloAsyncRestTemplate")
  public ListenableFuture<ResponseEntity<String>> getHelloWithNonBlock() {
    /*
      AsyncRestTemplate spring 5 deprecated. so, you should use `WebClient` from webflux.
     */
    log.info("helloAsyncRestTemplate Request");
    AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
    return asyncRestTemplate.getForEntity("http://localhost:8083/v1/remote-hello", String.class);
  }

  public static void main(String[] args) {
    System.setProperty("server.port", "8082");
    System.setProperty("server.tomcat.max-threads", "1");
    SpringApplication.run(SpringBootWithTomcatApplication.class, args);
  }

}
