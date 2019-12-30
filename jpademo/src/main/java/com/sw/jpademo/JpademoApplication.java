package com.sw.jpademo;

import com.sw.jpademo.entity.Coffee;
import com.sw.jpademo.entity.CoffeeOrder;
import com.sw.jpademo.entity.OrderState;
import com.sw.jpademo.respository.CoffeeOrderRespository;
import com.sw.jpademo.respository.CoffeeRespository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class JpademoApplication implements ApplicationRunner {


    @Autowired
    private CoffeeOrderRespository coffeeOrderRespository;

    @Autowired
    private CoffeeRespository coffeeRespository;

    public static void main(String[] args) {
        SpringApplication.run(JpademoApplication.class, args);

    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        initOrders();
        findOrders();
    }

    private void findOrders() {
        coffeeOrderRespository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(c -> log.info("Loading {}", c));


        List<CoffeeOrder> list = coffeeOrderRespository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc: {}", getJoinedOrderId(list));
        log.info("==================");
        list = coffeeOrderRespository.findByCustomOrderById("LILEI");
        log.info("findByCustomOrderById {}", getJoinedOrderId(list));
        list.forEach(o->{log.info("Order {}",o.getId());
            o.getItems().forEach(i -> log.info("coffee {}", i.getName()));});
        log.info("==================");

        list = coffeeOrderRespository.findByItems_Name("coffee1");
        log.info("findByItems_Name: {}",getJoinedOrderId(list));
        log.info("==================");

    }

    private String getJoinedOrderId(List<CoffeeOrder> list) {
        return list.stream().map(o -> o.getId().toString()).collect(Collectors.joining(","));
    }

    private void initOrders() {
        Coffee coffee1 = Coffee.builder().
                name("coffee1").
                price(Money.of(CurrencyUnit.of("CNY"), 20)).
                build();
        log.info("Coffee1:{}", coffee1);
        coffeeRespository.save(coffee1);

        Coffee coffee2 = Coffee.builder().name("coffee2").price(Money.of(CurrencyUnit.of("CNY"), 10)).build();

        log.info("Coffee2:{}", coffee2);
        coffeeRespository.save(coffee2);

        CoffeeOrder order1 = CoffeeOrder.builder().custom("LILEI").items(Collections.singletonList(coffee1)).state(OrderState.INIT).build();
        CoffeeOrder order2 = CoffeeOrder.builder().custom("HANMM").items(Arrays.asList(coffee1, coffee2)).state(OrderState.INIT).build();
        log.info("Coffee Order1:{}", order1);
        coffeeOrderRespository.save(order1);

        log.info("Coffee Order2:{}", order2);
        coffeeOrderRespository.save(order2);
    }

}
