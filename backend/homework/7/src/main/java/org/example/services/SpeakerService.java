package org.example.services;


import org.example.entity.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@ComponentScan(basePackages = "org.example.entity")
public class SpeakerService {

    Random random = new Random();

    @Bean
    Speaker generateSpeaker(){
        int check = random.nextInt(2) + 1;
        String brand="";
        if(check==1){
            brand = "SONY";
        }else{
            brand= "BOSE";
        }
        int price =  random.nextInt(5_000+1_000);
        Speaker speaker = new Speaker(price,brand);
        return speaker;
    }


}
