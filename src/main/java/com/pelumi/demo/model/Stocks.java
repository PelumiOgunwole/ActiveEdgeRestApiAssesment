package com.pelumi.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stocks {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private double currentPrice;

    @CreationTimestamp
    @Column(name = "created_on", nullable = false)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "updated_on", nullable = false)
    private Date lastUpdate;

    public Stocks(long l, String s, double c) {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stocks stocks)) return false;
        return getName().equals(stocks.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
