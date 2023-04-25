
package com.HKS.wCondition.repositories;


import com.HKS.wCondition.Entities.Weather;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author boman
 */


public interface WeatherRepository extends JpaRepository<Weather, Integer>{
    
    @Query("from Weather")
    List<Weather> findFiltered(String filt);
}
