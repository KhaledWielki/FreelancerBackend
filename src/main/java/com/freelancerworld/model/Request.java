package com.freelancerworld.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Created by AdamR on 2017-10-31.
 */

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "min_payment")
    private int minPayment;

    @Column(name = "max_payment")
    private int maxPayment;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private int active;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "request_taker_id")
    private int requestTakerId;

    @Column(name = "mark")
    private int mark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "requestsContractors")
    private Set<User> contractors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinPayment() {
        return minPayment;
    }

    public void setMinPayment(int minPayment) {
        this.minPayment = minPayment;
    }

    public int getMaxPayment() {
        return maxPayment;
    }

    public void setMaxPayment(int maxPayment) {
        this.maxPayment = maxPayment;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getRequestTakerId() {
        return requestTakerId;
    }

    public void setRequestTakerId(int requestTakerId) {
        this.requestTakerId = requestTakerId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @JsonBackReference
    public Set<User> getContractors() {
        return contractors;
    }

    public void setContractors(Set<User> contractors) {
        this.contractors = contractors;
    }
}