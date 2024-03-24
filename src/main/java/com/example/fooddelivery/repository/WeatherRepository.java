package com.example.fooddelivery.repository;

import com.example.fooddelivery.entity.Weather;
import com.example.fooddelivery.entity.WeatherId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface WeatherRepository extends CrudRepository<Weather, WeatherId> {

    @Query("SELECT w FROM Weather w WHERE w.stationName = :stationName " +
            "AND w.timestamp = (SELECT MAX(w2.timestamp) FROM Weather w2 WHERE w2.stationName = :stationName)")
    Weather findLatestByStationName(@Param("stationName") String stationName);

}
