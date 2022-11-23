package com.pelumi.demo.repository.impl;
import com.pelumi.demo.exception.StockDuplicateException;
import com.pelumi.demo.model.Stocks;
import com.pelumi.demo.repository.StockRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class StocksRepositoryImpl implements StockRepository {

    /*
    * This class is a way of abstracting the repository
    * for security concerns and to prevent direct access to it.
    * EntityManager is used for the work of persistence and do queries to the database
    * */

    private static final int DEFAULT_PAGE_SIZE = 11;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Stocks> getAll(Integer pageOffset) {
        String queryText = "select st from Stocks st order by st.lastUpdate asc";
        TypedQuery<Stocks> stockQuery = entityManager.createQuery(queryText, Stocks.class);

        stockQuery.setFirstResult((pageOffset - 1) * DEFAULT_PAGE_SIZE);
        stockQuery.setMaxResults(DEFAULT_PAGE_SIZE);
        return stockQuery.getResultList();
    }

    @Override
    public Stocks getOne(Long id) {
        return entityManager.find(Stocks.class, id);
    }

    @Override
    public Stocks saveOne(Stocks body) {
        try {
            entityManager.persist(body);
            return body;
        }
        catch (EntityExistsException ex) {
            throw new StockDuplicateException("Selected Stock with " + body.getId() +  " already existing");
        }
    }

    @Override
    public Stocks updateOne(Stocks body) {
        return entityManager.merge(body);
    }
}
