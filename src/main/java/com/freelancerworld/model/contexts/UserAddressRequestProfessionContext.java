package com.freelancerworld.model.contexts;

import com.freelancerworld.model.Address;
import com.freelancerworld.model.Profession;
import com.freelancerworld.model.Request;
import com.freelancerworld.model.User;

public class UserAddressRequestProfessionContext {
    private User user;
    private Address address;
    private Request request;
    private Profession profession;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
}
