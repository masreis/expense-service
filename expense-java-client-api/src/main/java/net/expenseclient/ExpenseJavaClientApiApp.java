package net.expenseclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExpenseJavaClientApiApp {

  public static void main(String[] args) {
    SpringApplication.run(ExpenseJavaClientApiApp.class, args);
  }
}
