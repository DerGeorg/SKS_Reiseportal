package at.technikum.wien.if20b206.if20b211.sksportzal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class SksPortzalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SksPortzalApplication.class, args);
    }

}
