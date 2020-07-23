package com.fake.dataproducer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    var start = LocalDateTime.now();

    Map<String, Object> stubValues = new HashMap<>();
    for (int i = 1; i <= 100; i++) {
      stubValues.put("field" + i, System.currentTimeMillis());
    }

    while (true) {

      kafkaTemplate.send("test_kafka", stubValues);

      if (Duration.between(start, LocalDateTime.now()).toSeconds() >= 60) {
        System.out.println("1 minute gone");
        //return;
      }

      Thread.sleep(110);
    }
  }
}
