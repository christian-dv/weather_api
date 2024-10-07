package com.weather.weatherCB.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ClimaForecast implements Serializable {
    private String cod;
    private double message;
    private int cnt;
    private List<Forecast> list;
    private City city;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Forecast implements Serializable {
        private long dt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private int visibility;
        private double pop;
        private Sys sys;
        @JsonProperty("dt_txt")
        private String dtTxt;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Main implements Serializable{
            private double temp;
            private double feelsLike;
            private double tempMin;
            private double tempMax;
            private double pressure;
            private double seaLevel;
            private double grndLevel;
            private double humidity;
            @JsonProperty("temp_kf")
            private double tempKf;

            // Getters y Setters
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
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Clouds implements Serializable {
            private int all;
            // Getters y Setters
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Wind implements Serializable {
            private double speed;
            private int deg;
            private double gust;
            // Getters y Setters
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Sys implements Serializable {
            private String pod;
            // Getters y Setters
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class City implements Serializable {
        private int id;
        private String name;
        private Coord coord;
        private String country;
        private int population;
        private int timezone;
        private long sunrise;
        private long sunset;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Coord implements Serializable{
            private double lat;
            private double lon;
        }
    }
}

