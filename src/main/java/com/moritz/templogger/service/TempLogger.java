package com.moritz.templogger.service;


import com.moritz.templogger.db.TemperatureEntity;
import com.moritz.templogger.db.TemperatureRepository;
import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TempLogger {
    @Autowired
    TemperatureRepository temperatureRepository;

    @PostConstruct
    public void getTemperature() throws InterruptedException {

        while (true){
        W1Master w1Master = new W1Master();

        System.out.println(w1Master);

            for (TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)) {
                System.out.printf("%-20s %3.1f°C %3.1f°F\n", device.getName(), device.getTemperature(),
                        device.getTemperature(TemperatureScale.CELSIUS));
                Double d = device.getTemperature(TemperatureScale.CELSIUS);

                TemperatureEntity temperatureEntity = new TemperatureEntity();
                temperatureEntity.setTemperature(d);
                temperatureEntity.setTime(Timestamp.valueOf(LocalDateTime.now()));

                temperatureRepository.save(temperatureEntity);

                System.out.println(d);

            }
            Thread.sleep(60*1000L);
        }

    }
}
