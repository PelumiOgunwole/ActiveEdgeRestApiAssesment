package com.pelumi.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pelumi.demo.model.Stocks;
import com.pelumi.demo.repository.jpa.StockJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner runner(StockJpaRepository stockRepository) {
        return args ->
        {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Stocks>> typeReference = new TypeReference<List<Stocks>>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/Stocks.json");
            try {
                List<Stocks> stocksList = mapper.readValue(inputStream, typeReference);


                stockRepository.saveAll(stocksList);
                System.out.println("All Stocks Saved");


            } catch (IOException e) {
                throw new RuntimeException("Cannot save Stock " + e.getMessage());
            }


        };
    }

}
