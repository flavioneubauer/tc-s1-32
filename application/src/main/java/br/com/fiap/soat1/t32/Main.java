package br.com.fiap.soat1.t32;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "br.com.fiap.soat1.t32")
@EnableFeignClients
@OpenAPIDefinition(info = @Info(
        title = "${project.name}",
        version = "${project.version}",
        description = "${project.description}"
))
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}