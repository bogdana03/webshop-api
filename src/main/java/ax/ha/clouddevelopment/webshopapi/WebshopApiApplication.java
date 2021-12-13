package ax.ha.clouddevelopment.webshopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebshopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopApiApplication.class, args);
        System.out.printf("Navigate to http://localhost:5000 to find the API documentation of your API");
    }

}
