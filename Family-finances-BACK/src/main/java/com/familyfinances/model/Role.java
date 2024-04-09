package com.familyfinances.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty
    private String role;

    @OneToMany(mappedBy="role")
    private Set<User> users;

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {

    }

    public String getRole() {
        return role;
    }
}
