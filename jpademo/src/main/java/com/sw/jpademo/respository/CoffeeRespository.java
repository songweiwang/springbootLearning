package com.sw.jpademo.respository;

import com.sw.jpademo.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component(value = "coffeeRespository")
public interface CoffeeRespository extends JpaRepository<Coffee, Long> {
}
