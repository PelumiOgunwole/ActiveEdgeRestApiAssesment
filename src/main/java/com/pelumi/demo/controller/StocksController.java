package com.pelumi.demo.controller;

import com.pelumi.demo.dto.StockDTO;
import com.pelumi.demo.dto.StockPriceUpdateDto;
import com.pelumi.demo.service.impl.StockServiceImpl;
import com.pelumi.demo.util.APIResponse;
import com.pelumi.demo.util.Responder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StocksController {
    private final StockServiceImpl stockService;

    @PostMapping("/stocks")
    public ResponseEntity<APIResponse> createStock(@RequestBody StockDTO stockDTO){
        return Responder.okay(stockService.createStock(stockDTO));

    }

    @GetMapping("/stocks/{stockId}")
    public ResponseEntity<APIResponse> getOneStock(@PathVariable Long stockId){
        return Responder.okay(stockService.getOneStock(stockId));
    }

    @PutMapping("/stocks/{stockId}")
    public ResponseEntity<APIResponse> updatePrice(@RequestBody StockPriceUpdateDto stockDTO, @PathVariable Long stockId){
        return Responder.okay(stockService.updateSingleStock(stockDTO,stockId));
    }

    @GetMapping("/stocks")
    @CrossOrigin
    public ResponseEntity<APIResponse> getAllStocks(@RequestParam(required = false, defaultValue = "1" , name = "page") Integer pageNumber){
        return Responder.okay(stockService.getAllStocks(pageNumber));
    }
}
