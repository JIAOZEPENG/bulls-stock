package com.buba.hateoas.demo.stocks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "T_STOCKS")
@NoArgsConstructor
@AllArgsConstructor
public class StocksEntity extends BaseEntity{

    private String name;
    private Double price;

}
