package com.dieborim.bibliotecadigital.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConvertirDatos implements IConvertirDatos {
    /**
     * ObjectMapper es una clase de la librería Jackson, que se usa para convertir entre JSON y objetos
     * Java y viceversa
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            /**
             * Y devuelve el objeto convertido del tipo que se pidió
             */
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
