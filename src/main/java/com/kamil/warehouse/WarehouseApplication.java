package com.kamil.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableJpaAuditing
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }
}




/*
 1. strzela request na contextlogin(defaultowo w springu) ze swoim usernamem i haslem
 2. pobieramy z requestu username i haslo (JWTAuthenticationFilter > attemptAuthentication)
 3. jesli dane sa puste to nie jest logowane, jesli istnieja to zostaje przekazane do UserDetailServiceImpl
 4. UDSImpl szuka w bazie danych uzytkownika i mapuje go na usera ze Spring Security (obiekt ze springa User ktory jest wymagany)
 5. Jesli uzytkownik zostanie stworzony to tworzymy token > succesfullAuthentication
 6. Jesli uzytkownik przedstawi sie tokenem to wywolujemy doFilterInternal do sprawdzenia, czy jest prawidlowy
 (jak request przyjdzie nam z headerem authentication)
*/