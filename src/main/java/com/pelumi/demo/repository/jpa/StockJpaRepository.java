package com.pelumi.demo.repository.jpa;


import com.pelumi.demo.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockJpaRepository extends JpaRepository<Stocks, Long> {
}
