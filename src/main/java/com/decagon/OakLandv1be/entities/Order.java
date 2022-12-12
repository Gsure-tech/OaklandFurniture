package com.decagon.OakLandv1be.entities;


import com.decagon.OakLandv1be.enums.DeliveryStatus;
import com.decagon.OakLandv1be.enums.ModeOfDelivery;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_tbl")
public class Order extends BaseEntity{

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private ModeOfPayment modeOfPayment;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Item> items;

    private Double deliveryFee;

    @Enumerated(EnumType.STRING)
    private ModeOfDelivery modeOfDelivery;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private Double grandTotal;

    private Double discount;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Transaction transaction;


}
