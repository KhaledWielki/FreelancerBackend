package com.freelancerworld.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by AdamR on 2017-10-31.
 */

@Entity
@Table(name = "order")
public class Order {
    /**
     * TODO
     * Dodać pozostałe relacje zamówieńa i gettery,settery!
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private long id;

    @Column(name = "title")
    @NotEmpty(message = "please add your order's title")
    private String title;

    @Column(name = "payment")
    @NotEmpty(message = "please add price range that you want to pay")
    private String payment;

    @Column(name = "deposit")
    private double deposit;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "profession_order", joinColumns = @JoinColumn(name = "profession_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Profession> professions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "profession_address", joinColumns = @JoinColumn(name = "profession_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addresses;



}
