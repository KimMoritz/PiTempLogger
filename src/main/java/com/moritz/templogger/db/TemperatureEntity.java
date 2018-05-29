package com.moritz.templogger.db;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class TemperatureEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true)
    private Long id;
    @Column()
    private Timestamp time;
    @Column()
    private Double temperature;

    public Long getId() {
        return id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
