package com.weather.weatherCB.service;

import com.weather.weatherCB.dto.ClimaCurrent;
import com.weather.weatherCB.dto.ClimaForecast;
import com.weather.weatherCB.dto.ClimaPollution;
import com.weather.weatherCB.entity.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClimaService {

    @Value("${weather.byname-api-url}")
    private String WeatherByNameUrl;

    @Value("${weather.getLanLon-api-url}")
    private String LatLonByName;

    @Value("${weather.forecast-api-url}")
    private String WeatherForecast;

    @Value("${weather.pollution-api-url}")
    private String WeatherPollution;

    @Cacheable(value = "CurrentCache")
    public ClimaCurrent obtenerClimaPorNombre(String ciudad, String apiKey) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(WeatherByNameUrl, ciudad, apiKey);
        return restTemplate.getForObject(url, ClimaCurrent.class);
    }

    @Cacheable(value = "LocationCache")
    public Coordenadas obtenerLatLon(String ciudad, String apiKey) throws ParseException {
        double lat=0,lon=0;
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(LatLonByName, ciudad, apiKey);
        String jsonResponse = restTemplate.getForObject(url, String.class);

        Coordenadas coordenadas = null;
        JSONParser parser = new JSONParser();
        Object parsedResponse = parser.parse(jsonResponse);

        if (parsedResponse instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) parsedResponse;
            // Asegurarse de que el array no esté vacío
            if (!jsonArray.isEmpty()) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(0); // Obtener el primer objeto
                lat = (double) jsonObject.get("lat");
                lon = (double) jsonObject.get("lon");
            }
        }
        return new Coordenadas(lat, lon);
    }

    @Cacheable(value = "ForecastCache")
    public ClimaForecast getWeatherForecast(Coordenadas coordenadas, String apiKey) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(WeatherForecast, coordenadas.getLatitud(), coordenadas.getLongitud(), apiKey);
        return restTemplate.getForObject(url, ClimaForecast.class);
    }

    @Cacheable(value = "PollutionCache")
    public ClimaPollution getWeatherPollution(Coordenadas coordenadas, String apiKey) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(WeatherPollution, coordenadas.getLatitud(), coordenadas.getLongitud(), apiKey);
        return restTemplate.getForObject(url, ClimaPollution.class);
    }


}
