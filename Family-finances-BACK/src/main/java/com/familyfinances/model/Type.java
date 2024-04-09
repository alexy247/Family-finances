package com.familyfinances.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="type")
    private Set<Finance> finances;

    public Type() {}

    public int getId() {
        return id;
    }

    public boolean isIncome() {
        return name.equals("Доход");
    }
}
