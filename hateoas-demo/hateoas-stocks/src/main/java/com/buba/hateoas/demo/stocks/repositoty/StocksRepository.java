package com.buba.hateoas.demo.stocks.repositoty;

import com.buba.hateoas.demo.stocks.entity.StocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "/stocks")
public interface StocksRepository extends JpaRepository<StocksEntity,Long> {
    public StocksEntity findByName(@Param("name") String name);
    List<StocksEntity> findByNameInOrderById(@Param("list") List<String> list);
}
