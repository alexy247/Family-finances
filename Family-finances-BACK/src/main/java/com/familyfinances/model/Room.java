package com.familyfinances.model;

import com.familyfinances.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(
            name = "Finances",
            joinColumns = @JoinColumn(name = "id_room"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    Set<User> users;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private Set<Finance> finances;

    @JsonProperty
    private String name;

    @JsonProperty
    private String date;

    @JsonProperty
    private String password;

    @JsonProperty
    private int budget;

    public Room(){}

    public Room(String name, String password, User user) {
        this.name = name;
        this.password = password;
        this.date = DateUtils.getDateNow();
        this.users = Collections.singleton(user);
    }

    public void setFinances(Finance finance) {
        this.finances = Collections.singleton(finance);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        Set<User> current = this.users;
        current.add(user);
        this.users = current;
    }

    public Set<Finance> getFinances() {
        return this.finances;
    }

    public String getPassword() {
        return password;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getId() {
        return id;
    }
}
