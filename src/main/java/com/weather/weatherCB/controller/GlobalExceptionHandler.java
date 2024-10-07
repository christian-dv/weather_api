package com.weather.weatherCB.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weatherCB.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ErrorMessage> handleNotFound(HttpClientErrorException.NotFound ex) {
        String responseBody = ex.getResponseBodyAsString();

        try {
            // Analizar el JSON de respuesta
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String codigo = jsonNode.path("cod").asText(); // Extraer el código
            String mensaje = jsonNode.path("message").asText(); // Extraer el mensaje
            // Crear la respuesta de error
            ErrorMessage errorResponse = new ErrorMessage(codigo, mensaje);
            // Devolver el objeto JSON con estado 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (Exception e) {
            // En caso de error al analizar, devolver un error genérico
            ErrorMessage errorResponse = new ErrorMessage("404", "Error al procesar la respuesta de error.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ErrorMessage> handleUnauthorized(HttpClientErrorException.Unauthorized ex) {
        String responseBody = ex.getResponseBodyAsString();

        try {
            // Analizar el JSON de respuesta
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String codigo = jsonNode.path("cod").asText(); // Extraer el código
            String mensaje = jsonNode.path("message").asText(); // Extraer el mensaje
            // Crear la respuesta de error
            ErrorMessage errorResponse = new ErrorMessage(codigo, mensaje);
            // Devolver el objeto JSON con estado 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

        } catch (Exception e) {
            // En caso de error al analizar, devolver un error genérico
            ErrorMessage errorResponse = new ErrorMessage("401", "Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

}
