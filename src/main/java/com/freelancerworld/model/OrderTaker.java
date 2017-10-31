package com.freelancerworld.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

/**
 * Created by AdamR on 2017-10-31.
 */

@Entity
@Table(name = "order_taker")
public class OrderTaker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_taker_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
