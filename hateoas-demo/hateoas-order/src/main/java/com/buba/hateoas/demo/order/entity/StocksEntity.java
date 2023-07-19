package com.buba.hateoas.demo.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "T_STOCKS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StocksEntity extends BaseEntity{

    private String name;
    private Double price;

}
