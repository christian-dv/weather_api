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
public class ClimaPollution implements Serializable {

    private Coord coord;
    private List<AirQualityData> list;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coord implements Serializable {
        private double lon;
        private double lat;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AirQualityData implements Serializable {
        private Main main;
        private Components components;
        private long dt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main implements Serializable {
        private int aqi;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Components implements Serializable {
        private double co;
        private double no;
        private double no2;
        private double o3;
        private double so2;
        private double pm2_5;
        private double pm10;
        private double nh3;
    }
}
