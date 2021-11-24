package at.technikum.wien.if20b206.if20b211.statsserver.kafka;

import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    private final String KEY_STATSCREATE = "statscreate";

    @org.springframework.kafka.annotation.KafkaListener(topics = "statscreate")
    public void listener(String data){
        System.out.println("Kafka Receive: " + data);
        System.out.println(data);
    }
}