package com.buba.hateoas.demo.order.repositoty;

import com.buba.hateoas.demo.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import java.util.List;

@RepositoryRestResource(path = "/order")
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    /**
     * 根据用户查找全部订单
     * @param user
     * @return
     */
    public List<OrderEntity> findByUser(@Param("user") String user);
}
