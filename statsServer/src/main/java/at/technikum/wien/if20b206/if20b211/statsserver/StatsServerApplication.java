package at.technikum.wien.if20b206.if20b211.statsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StatsServerApplication {

    // TODO CONFIG aus dem Config server laden geht noch nicht config server ansich aber schon
    public static void main(String[] args) {
        SpringApplication.run(StatsServerApplication.class, args);
    }

}
