package com.pelumi.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pelumi.demo.model.Stocks;
import com.pelumi.demo.repository.jpa.StockJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
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
            TypeReference<List<Stocks>> typeReference = new TypeReference<>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/Stocks.json");
            try {
                List<Stocks> stocksList = mapper.readValue(inputStream, typeReference);
                List<Stocks> dbStocks = stockRepository.findAll();
                HashSet<Stocks> finalStocks= new HashSet<>(stocksList);

                for(Stocks stocks: finalStocks){
                    if(!dbStocks.contains(stocks))
                        /*If the stocks from json are
                    not in database add the new Objects else leave the db as it is */
                    {
                        dbStocks.add(stocks);
                    }

                }

                stockRepository.saveAll(dbStocks);


            } catch (IOException e) {
                throw new RuntimeException("Cannot save Stock " + e.getMessage());
            }


        };
    }

}
