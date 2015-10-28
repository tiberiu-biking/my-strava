package com.tpo.strava.persistence;

import com.tpo.strava.persistence.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by Tiberiu on 23/10/15.
 */
@SpringBootApplication
public class PersistenceApplication {

    @Autowired
    private ActivityRepository activityRepository;

    @Bean
    public String demo(){
        activityRepository.findAll();
        return "sadas";
    }

    public static void main(String[] args) {
        SpringApplication.run(PersistenceApplication.class);
    }

}
