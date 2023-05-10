package com.weather.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String weather;
    private String icon;
    private Double temperature;
    private String text;
    private LocalDate date;

    public void setDateWeather(DateWeather dateWeather) {
        date = dateWeather.getDate();
        weather = dateWeather.getWeather();
        temperature = dateWeather.getTemperature();
        icon = dateWeather.getIcon();
    }
}
