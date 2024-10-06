package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Yr4Lab2BuildingTwoInteractingMicroservices2Application {

    public static void main(String[] args) {
        SpringApplication.run(Yr4Lab2BuildingTwoInteractingMicroservices2Application.class, args);
    }

}
