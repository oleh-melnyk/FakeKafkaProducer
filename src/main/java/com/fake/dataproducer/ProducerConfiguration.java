package com.fake.dataproducer;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class ProducerConfiguration {

  private static final String KAFKA_BROKER = "localhost:9092";

  @Bean
  public ProducerFactory<String, Map<String, Object>> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigurations());
  }

  @Bean
  public Map<String, Object> producerConfigurations() {
    Map<String, Object> configurations = new HashMap<>();

    configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
    configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TrxMapValuesSerializer.class);

    return configurations;
  }

  @Bean
  public KafkaTemplate<String, Map<String, Object>> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

}
