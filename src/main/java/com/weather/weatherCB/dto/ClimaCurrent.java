package com.weather.weatherCB.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClimaCurrent implements Serializable {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    // Getters y Setters
    // ...

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coord implements Serializable {
        private double lon;
        private double lat;

        // Getters y Setters
        // ...
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weather implements Serializable {
        private int id;
        private String main;
        private String description;
        private String icon;

        // Getters y Setters
        // ...
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main implements Serializable {
        private double temp;
        private double feelsLike; // "feels_like" en JSON
        private double tempMin;   // "temp_min" en JSON
        private double tempMax;   // "temp_max" en JSON
        private int pressure;
        private int humidity;
        private int seaLevel;     // "sea_level" en JSON
        private int grndLevel;    // "grnd_level" en JSON

        // Getters y Setters
        // ...
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind implements Serializable {
        private double speed;
        private int deg;

        // Getters y Setters
        // ...
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Clouds implements Serializable {
        private int all;

        // Getters y Setters
        // ...
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sys implements Serializable {
        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;

        // Getters y Setters
        // ...
    }
}


