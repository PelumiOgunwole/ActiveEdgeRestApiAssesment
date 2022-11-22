package com.pelumi.demo.service.impl;
import com.pelumi.demo.dto.StockDTO;
import com.pelumi.demo.dto.StockPriceUpdateDto;
import com.pelumi.demo.exception.StockNotExistingException;
import com.pelumi.demo.model.Stocks;
import com.pelumi.demo.repository.StockRepository;
import com.pelumi.demo.service.StocksService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class StockServiceImpl implements StocksService {

    private final ModelMapper modelMapper;


    private final StockRepository stockRepository;

    public StockServiceImpl(ModelMapper modelMapper, StockRepository stockRepository) {
        this.modelMapper = modelMapper;
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stocks> getAllStocks(Integer pageOffset) {
        return stockRepository.getAll(pageOffset);
    }

    @Override
    public Optional getOneStock(Long stockId) {
        Optional<Stocks> stocks = Optional.ofNullable(stockRepository.getOne(stockId));
        if (!stocks.isPresent()) {
            throw new StockNotExistingException("Specified Stock not found");
        }
        return stocks;

    }

    @Override
    @Transactional
    public Stocks updateSingleStock(StockPriceUpdateDto stockPriceUpdateDto, Long stockId) {
        Stocks existingStock = stockRepository.getOne(stockId);
        if (existingStock == null) {
            throw new StockNotExistingException("Stock not existing");
        }

        if (stockPriceUpdateDto.getCurrentPrice() != 0) {
            existingStock.setCurrentPrice(stockPriceUpdateDto.getCurrentPrice());
        }

        return stockRepository.updateOne(existingStock);
    }

    @Override
    @Transactional
    public Stocks createStock(StockDTO stockDTO) {
        Stocks stocksToCreate = new Stocks();
        stocksToCreate = modelMapper.map(stockDTO,Stocks.class);
        return (Stocks) stockRepository.saveOne(stocksToCreate);
    }


}
