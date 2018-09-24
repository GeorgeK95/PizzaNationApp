package pizzaNation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PizzaNationApp {

    public static void main(String[] args) {
        SpringApplication.run(PizzaNationApp.class, args);
    }
}
