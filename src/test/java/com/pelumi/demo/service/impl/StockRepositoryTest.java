package com.pelumi.demo.service.impl;

import com.pelumi.demo.dto.StockDTO;
import com.pelumi.demo.model.Stocks;
import com.pelumi.demo.repository.jpa.StockJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class StockServiceImplTest {


    @Autowired
    private StockJpaRepository stockRepository;

    @AfterEach
    void tearDown(){
        stockRepository.deleteAll();
    }

    @Test
    void getAllStocks() {
        List<Stocks> allStocks = stockRepository.findAll();
        assertTrue(allStocks.size()>0);

    }

    @Test
    void getOneExistingStock() {
        Optional<Stocks> singleStock = stockRepository.findById(1L);
        assertEquals(singleStock.get().getId(),1L);

    }

    @Test
    void updateSingleStockPrice() {
        Stocks stocks = stockRepository.getReferenceById(2L);
        stocks.setCurrentPrice(40000);

        Stocks updatedStock = stockRepository.save(stocks);
        assertEquals(40000,updatedStock.getCurrentPrice());

    }

    @Test
    void createStockPrice() {
        StockDTO stockDTO = new StockDTO(5L,"Extension Box",3500);
        ModelMapper mapper = new ModelMapper();
        Stocks newStockAdded = mapper.map(stockDTO, Stocks.class);
        stockRepository.save(newStockAdded);
        assertNotNull(stockRepository.findById(5L));
    }
}
