package com.pelumi.demo.controller;

import com.pelumi.demo.dto.StockDTO;
import com.pelumi.demo.dto.StockPriceUpdateDto;
import com.pelumi.demo.service.impl.StockServiceImpl;
import com.pelumi.demo.util.APIResponse;
import com.pelumi.demo.util.Responder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class StocksController {
    private static final Logger logger = LoggerFactory.getLogger(StocksController.class);
    private final StockServiceImpl stockService;

    @PostMapping("/stocks")
    public ResponseEntity<APIResponse> createStock(@RequestBody StockDTO stockDTO){
        logger.info("Create a new Stock");
        return Responder.okay(stockService.createStock(stockDTO));

    }

    @GetMapping("/stocks/{stockId}")
    public ResponseEntity<APIResponse> getOneStock(@PathVariable Long stockId){
        logger.info("Get Stock by id");
        return Responder.okay(stockService.getOneStock(stockId));
    }

    @PutMapping("/stocks/{stockId}")
    public ResponseEntity<APIResponse> updatePrice(@RequestBody StockPriceUpdateDto stockDTO, @PathVariable Long stockId){
        logger.info("Update Price Of Stock");
        return Responder.okay(stockService.updateSingleStock(stockDTO,stockId));
    }

    @GetMapping("/stocks")
    @CrossOrigin
    public ResponseEntity<APIResponse> getAllStocks(@RequestParam(required = false, defaultValue = "1" , name = "page") Integer pageNumber){
        logger.info("Get All Stocks");
        return Responder.okay(stockService.getAllStocks(pageNumber));
    }
}
