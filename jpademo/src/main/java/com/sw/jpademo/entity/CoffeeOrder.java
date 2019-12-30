package com.sw.jpademo.entity;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "T_ORDER")
public class CoffeeOrder extends BaseEntity implements Serializable {
    private String custom;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("Id")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
