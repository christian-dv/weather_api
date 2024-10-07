package com.weather.weatherCB.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.weather.weatherCB.dto.ClimaCurrent;
import com.weather.weatherCB.dto.ClimaForecast;
import com.weather.weatherCB.dto.ClimaPollution;
import com.weather.weatherCB.entity.Consultas;
import com.weather.weatherCB.entity.Coordenadas;
import com.weather.weatherCB.service.ClimaService;
import com.weather.weatherCB.service.ConsultaService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ClimaControllerTest {

    @InjectMocks
    private ClimaController climaController;

    @Mock
    private ClimaService climaService;

    @Mock
    private ConsultaService consultaService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");
    }

    @Test
    public void testObtenerClimaPorNombre() throws  ParseException {
        // Datos de prueba
        String ciudad = "Madrid";
        String apiKey = "testApiKey";
        ClimaCurrent climaActual = new ClimaCurrent(); // Asigna valores necesarios

        // Mockear el comportamiento del servicio
        when(climaService.obtenerClimaPorNombre(ciudad, apiKey)).thenReturn(climaActual);

        // Llamar al método
        ClimaCurrent resultado = climaController.obtenerClimaPorNombre(ciudad, apiKey);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(climaActual, resultado);

        // Verificar que se guardó la consulta
        verify(consultaService).save(any(Consultas.class));
    }

    @Test
    public void testObtenerClimaCincoDias() throws ParseException {
        String ciudad = "Madrid";
        String apiKey = "testApiKey";
        Coordenadas coordenadas = new Coordenadas(); // Asigna valores necesarios
        ClimaForecast climaForecast = new ClimaForecast(); // Asigna valores necesarios

        when(climaService.obtenerLatLon(ciudad, apiKey)).thenReturn(coordenadas);
        when(climaService.getWeatherForecast(coordenadas, apiKey)).thenReturn(climaForecast);

        ClimaForecast resultado = climaController.obtenerClimaCincoDias(ciudad, apiKey);

        assertNotNull(resultado);
        assertEquals(climaForecast, resultado);

        verify(consultaService).save(any(Consultas.class));
    }

    @Test
    public void testObtenerClimaPollution() throws ParseException {
        String ciudad = "Madrid";
        String apiKey = "testApiKey";
        Coordenadas coordenadas = new Coordenadas(); // Asigna valores necesarios
        ClimaPollution climaPollution = new ClimaPollution(); // Asigna valores necesarios

        when(climaService.obtenerLatLon(ciudad, apiKey)).thenReturn(coordenadas);
        when(climaService.getWeatherPollution(coordenadas, apiKey)).thenReturn(climaPollution);

        ClimaPollution resultado = climaController.obtenerClimaPollution(ciudad, apiKey);

        assertNotNull(resultado);
        assertEquals(climaPollution, resultado);

        verify(consultaService).save(any(Consultas.class));
    }
}

