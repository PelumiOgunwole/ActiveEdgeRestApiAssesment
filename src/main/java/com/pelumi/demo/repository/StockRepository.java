package com.pelumi.demo.repository;

import com.pelumi.demo.model.Stocks;

import java.util.List;

public interface StockRepository {
    List<Stocks> getAll(Integer pageOffset);

    Stocks getOne(Long id);

    Stocks saveOne(Stocks body);

    Stocks updateOne(Stocks body);
}
