package org.example.config;

import org.example.data.VehiclesInventory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"org.example.entity","org.example.services","org.example.data"})
public class AppConfig {

    @Bean("factoryoneInventory")
    public VehiclesInventory factoryoneInventory(){
        return inventory();
    }

    @Bean("factorysecondInventory")
    public VehiclesInventory factorysecondInventory(){
        return inventory();
    }

    @Bean
    @Scope("prototype")
    VehiclesInventory inventory(){
        return new VehiclesInventory();
    }


}
