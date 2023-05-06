package io.cjf.teststomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TeststompApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeststompApplication.class, args);
    }

}
