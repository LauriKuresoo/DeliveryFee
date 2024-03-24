package com.example.fooddelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@org.springframework.stereotype.Component
public class WeatherId implements Serializable {
        private String stationName;
        private Timestamp timestamp;
}
