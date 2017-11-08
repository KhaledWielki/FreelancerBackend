package com.freelancerworld.model;

import java.util.Set;

public class UserProfessionContext {
    private User user;
    private Set<Profession> professions;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Profession> getProfessions() {
        return professions;
    }

    public void setProfession(Set<Profession> professions) {
        this.professions = professions;
    }
}
