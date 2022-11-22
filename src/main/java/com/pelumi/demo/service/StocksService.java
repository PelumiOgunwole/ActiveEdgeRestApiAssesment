package com.pelumi.demo.service;
import com.pelumi.demo.dto.StockDTO;
import com.pelumi.demo.dto.StockPriceUpdateDto;
import com.pelumi.demo.model.Stocks;

import java.util.List;
import java.util.Optional;

public interface StocksService {

    List<Stocks> getAllStocks(Integer pageOffset);
    Optional<Stocks> getOneStock(Long stockId);
    Stocks updateSingleStock(StockPriceUpdateDto stockPriceUpdateDto, Long stockId);
    Stocks createStock(StockDTO stockDTO);


}
