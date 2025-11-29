package com.nishjournal.Nishjournal.service;


import com.nishjournal.Nishjournal.Cache.AppCache;
import com.nishjournal.Nishjournal.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api-key}")
    private String API_KEY;

    @Autowired
    private AppCache appcache;


    public WeatherResponse getWeather(){
        String finalAPI=appcache.APP_CACHE.get("weather_api").replace("<API_KEY>",API_KEY);
        ResponseEntity<WeatherResponse> response= restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);//last param is deserializing coming json response into corresponding java object
        WeatherResponse body=response.getBody();
        return body;
    }
}
