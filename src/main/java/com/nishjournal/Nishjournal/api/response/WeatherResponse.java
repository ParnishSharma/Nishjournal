package com.nishjournal.Nishjournal.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private Current    current;

    @Getter
    @Setter
    public class Current{

        public double temp_c;

        public double temp_f;

        public int humidity;
        public int cloud;
        public double feelslike_c;
        public double feelslike_f;
        public double windchill_c;
    }
}
