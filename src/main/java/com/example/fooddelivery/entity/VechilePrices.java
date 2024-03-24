package com.example.fooddelivery.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vechile_prices")
public class VechilePrices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String cityname;
    private String vechile;
    private double price;


    public VechilePrices( String cityname, String vechile, double price){
        this.cityname = cityname;
        this.vechile = vechile;
        this.price = price;
    }
}
