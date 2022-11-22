package com.pelumi.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pelumi.demo.dto.StockDTO;
import com.pelumi.demo.model.Stocks;
import com.pelumi.demo.service.StocksService;
import com.pelumi.demo.service.impl.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(value = StocksControllerTest.class)
class StocksControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StocksService stockService;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
        modelMapper = new ModelMapper();
    }


    @Test
    void createStock() throws Exception{
        StockDTO stocks = new StockDTO(1L,"Washing Machine",150000);

        String inputJson = this.mapToJson(stocks);
        String uri = "http://localhost:9090/api/stocks";


        Mockito.when(stockService.createStock(Mockito.any(StockDTO.class))).thenReturn(modelMapper.map(stocks, Stocks.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertEquals(outputInJson,inputJson);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(object);

    }

    @Test
    void getOneStockById() throws Exception {

        Stocks stocks = new Stocks(1L,"Washing Machine",150000);
        Mockito.when(stockService.getOneStock(Mockito.anyLong())).thenReturn(Optional.of(stocks));

        String URI = "http://localhost:9090/api/stocks/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(stocks);
        String outputInJson = result.getResponse().getContentAsString();
        assertEquals(outputInJson,expectedJson);

    }

    @Test
    void allStocks() throws Exception {

        List<Stocks>  stocksList= List.of(
                new Stocks(1L,"Washing Machine",150000),
                new Stocks(2L,"Blender",15000)
        );

        Mockito.when(stockService.getAllStocks(1)).thenReturn(stocksList);

        String URI = "http://localhost:9090/api/stocks";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(stocksList);
        String outputInJson = result.getResponse().getContentAsString();
        assertEquals(outputInJson,expectedJson);

    }

    @Test
    void updateStockPrice() throws Exception {
        StockDTO stocks = new StockDTO(1L,"Washing Machine",150000);

        String inputJson = this.mapToJson(stocks);
        String uri = "http://localhost:9090/api/stocks/1";


        Mockito.when(stockService.createStock(Mockito.any(StockDTO.class))).thenReturn(modelMapper.map(stocks, Stocks.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(uri)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertEquals(outputInJson,inputJson);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}