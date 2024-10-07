package com.weather.weatherCB.Interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weatherCB.dto.Mensaje;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.HashMap;
import java.util.Map;
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS_PER_HOUR = 100;
    private static final long ONE_HOUR = 3600000; // en milisegundos

    private Map<String, UserRequestInfo> userRequests = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getRemoteAddr(); // Puedes usar otro identificador, como un token JWT
        UserRequestInfo requestInfo = userRequests.getOrDefault(userId, new UserRequestInfo());

        long currentTime = System.currentTimeMillis();

        // Limpiar solicitudes antiguas
        if (currentTime - requestInfo.getLastRequestTime() > ONE_HOUR) {
            requestInfo.reset();
        }

        if (requestInfo.getRequestCount() < MAX_REQUESTS_PER_HOUR) {
            requestInfo.incrementRequestCount();
            requestInfo.setLastRequestTime(currentTime);
            userRequests.put(userId, requestInfo);
            return true; // Permitir la solicitud
        } else {
            response.setStatus(429); // 429
            response.setContentType("application/json"); // Establecer tipo de contenido a JSON
            // Crear el objeto de respuesta de error
            Mensaje errorResponse = new Mensaje("429 Too Many Requests");
            // Escribir la respuesta en JSON
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return false; // Bloquear la solicitud
        }
    }

    private static class UserRequestInfo {
        private int requestCount;
        private long lastRequestTime;

        public int getRequestCount() {
            return requestCount;
        }

        public void incrementRequestCount() {
            this.requestCount++;
        }

        public long getLastRequestTime() {
            return lastRequestTime;
        }

        public void setLastRequestTime(long lastRequestTime) {
            this.lastRequestTime = lastRequestTime;
        }

        public void reset() {
            this.requestCount = 0;
        }
    }
}