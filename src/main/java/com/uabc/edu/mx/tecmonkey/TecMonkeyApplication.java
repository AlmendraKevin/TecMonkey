package com.uabc.edu.mx.tecmonkey;

import com.uabc.edu.mx.tecmonkey.repository.VentaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = VentaRepository.class)
public class TecMonkeyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecMonkeyApplication.class, args);
    }

}
