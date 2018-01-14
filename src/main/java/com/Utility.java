package com;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * Created by deepak.dhakad on 1/12/18.
 */
@NoArgsConstructor
public class Utility {

        private static ObjectMapper objectMapper = new ObjectMapper();

        public static <T> String serialize(T object) {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (IOException var2) {
                throw new IllegalArgumentException("Cannot serialize given object");
            }
        }
        public static <T> T deserialize(String json, Class<T> clazz) {
            try {
                return objectMapper.readValue(json, clazz);
            } catch (IOException var3) {
                throw new IllegalArgumentException("Cannot deserialize given object");
            }
        }
        public static <T> T deserialize(String json, TypeReference<T> type) {
            try {
                return objectMapper.readValue(json, type);
            } catch (IOException var3) {
                throw new IllegalArgumentException("Cannot deserialize given object");
            }
        }
        static {
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        }



}
