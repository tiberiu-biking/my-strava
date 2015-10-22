package com.tpo;

import com.tpo.config.MyStravaConfig;
import org.springframework.boot.SpringApplication;

public class MyStravaParentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyStravaConfig.class, args);
    }
}
