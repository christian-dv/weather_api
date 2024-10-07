package com.weather.weatherCB.controller;

import com.weather.weatherCB.dto.ClimaCurrent;
import com.weather.weatherCB.dto.ClimaForecast;
import com.weather.weatherCB.dto.ClimaPollution;
import com.weather.weatherCB.entity.*;
import com.weather.weatherCB.service.ClimaService;
import com.weather.weatherCB.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/clima")
@CrossOrigin(origins = "*")
public class ClimaController {

    @Autowired
    private ClimaService climaService;

    @Autowired
    private ConsultaService consultaService;

    private Authentication authentication;

    @GetMapping("/current")
    @Operation(summary = "Consultar clima actual por ciudad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found!"),
            @ApiResponse(responseCode = "401", description = "Not authorized! Invalid API key."),
            @ApiResponse(responseCode = "429", description = "Too many request!")
    })
   public ClimaCurrent obtenerClimaPorNombre(@RequestParam String ciudad, @RequestParam String apiKey) throws ParseException {
        //Guardar datos en tabla Consultas
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Consultas consultas = new Consultas();
        //Obteniendo fechaactual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaFormateada = fechaHoraActual.format(formatter);
        //Seteando datos antes de guardar
        consultas.setFechaconsulta(fechaFormateada);
        consultas.setUsuarioquery(authentication.getName());
        consultas.setRequest(ciudad);
        consultas.setResponse("currentWeather: "+ciudad);
        consultaService.save(consultas);

        return climaService.obtenerClimaPorNombre(ciudad, apiKey);
    }

    @GetMapping("/forecast")
    @Operation(summary = "Consultar clima de 5 dias por ciudad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found!"),
            @ApiResponse(responseCode = "401", description = "Not authorized! Invalid API key."),
            @ApiResponse(responseCode = "429", description = "Too many request!")
    })
    public ClimaForecast obtenerClimaCincoDias(@RequestParam String ciudad, @RequestParam String apiKey) throws ParseException {
        Coordenadas coordenadas = new Coordenadas();
        coordenadas = climaService.obtenerLatLon(ciudad, apiKey);

        //Guardar datos en tabla Consultas
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Consultas consultas = new Consultas();
        //Obteniendo fechaactual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaFormateada = fechaHoraActual.format(formatter);
        //Seteando datos antes de guardar
        consultas.setFechaconsulta(fechaFormateada);
        consultas.setUsuarioquery(authentication.getName());
        consultas.setRequest(ciudad);
        consultas.setResponse("Forecast(5 days): "+ciudad);
        consultaService.save(consultas);

        return climaService.getWeatherForecast(coordenadas, apiKey);

    }

    @GetMapping("/airpollution")
    @Operation(summary = "Consultar contaminacion del aire por ciudad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found!"),
            @ApiResponse(responseCode = "401", description = "Not authorized! Invalid API key."),
            @ApiResponse(responseCode = "429", description = "Too many request!")
    })
    public ClimaPollution obtenerClimaPollution(@RequestParam String ciudad, @RequestParam String apiKey) throws ParseException {
        Coordenadas coordenadas = new Coordenadas();
        coordenadas = climaService.obtenerLatLon(ciudad, apiKey);

        //Guardar datos en tabla Consultas
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Consultas consultas = new Consultas();
        //Obteniendo fechaactual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaFormateada = fechaHoraActual.format(formatter);
        //Seteando datos antes de guardar
        consultas.setFechaconsulta(fechaFormateada);
        consultas.setUsuarioquery(authentication.getName());
        consultas.setRequest(ciudad);
        consultas.setResponse("AirPollution: "+ciudad);
        consultaService.save(consultas);

        return climaService.getWeatherPollution(coordenadas, apiKey);

    }


}
