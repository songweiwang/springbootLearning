package com.sw.jpademo.respository;

import com.sw.jpademo.entity.CoffeeOrder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "coffeeOrderRespository")
public interface CoffeeOrderRespository extends BaseRespository<CoffeeOrder,Long> {

    List<CoffeeOrder> findByCustomOrderById(String customer);

    List<CoffeeOrder> findByItems_Name(String name);
}
