package com.fake.dataproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
public class TrxMapValuesSerializer implements Serializer<Map<String, Object>> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public byte[] serialize(String s, Map<String, Object> stringObjectMap) {

    byte[] bytes = new byte[0];
    try {
      bytes = objectMapper.writeValueAsBytes(stringObjectMap);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return bytes;
  }
}
