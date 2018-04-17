package pizzaNation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import pizzaNation.app.contoller.error.ExceptionController;

/**
 * Created by George-Lenovo on 13/03/2018.
 */
@SpringBootApplication
@EnableScheduling
public class PizzaNationApp {

    public static void main(String[] args) {
        SpringApplication.run(PizzaNationApp.class, args);
    }
}
