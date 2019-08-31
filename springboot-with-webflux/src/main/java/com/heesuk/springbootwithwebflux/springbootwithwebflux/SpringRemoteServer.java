package com.heesuk.springbootwithwebflux.springbootwithwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringRemoteServer {

  @GetMapping("/v1/remote-hello")
  public String getRemoteHello() throws InterruptedException {
    /*
      Assume database IO sleep 1 second
     */
    Thread.sleep(1000);
    return "HELLO! FROM remote service";
  }

  public static void main(String[] args) {
    System.setProperty("server.port", "8081");
    System.setProperty("server.tomcat.max-threads", "1000");
    SpringApplication.run(SpringRemoteServer.class, args);
  }

}
