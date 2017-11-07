package com.freelancerworld.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by AdamR on 2017-10-29.
 */

@Entity
@Table(name = "profession")
public class Profession{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profession_id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Please add profession name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profession")
    private List<Request> requests;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
