package site.woorifisa.codingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PrepareCodingtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrepareCodingtestApplication.class, args);
    }

}
