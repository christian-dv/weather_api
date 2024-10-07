package com.weather.weatherCB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherCbApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherCbApplication.class, args);
	}

}
