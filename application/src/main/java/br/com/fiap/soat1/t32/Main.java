package br.com.fiap.soat1.t32;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.fiap.soat1.t32")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}