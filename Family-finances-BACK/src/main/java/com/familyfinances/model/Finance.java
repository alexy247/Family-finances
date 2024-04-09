package com.familyfinances.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Finances")
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="id_type", nullable=false)
    @JsonProperty
    private Type type;

    @ManyToOne
    @JoinColumn(name="id_room", nullable=false)
    @JsonIgnore
    private Room room;

    @ManyToOne
    @JoinColumn(name="id_category", nullable=false)
    @JsonProperty
    private Category category;

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    @JsonProperty
    private User user;

    @JsonProperty
    @Column(name = "amount")
    private float amount;

    @JsonProperty
    @Column(name = "comment")
    private String comment;

    @JsonProperty
    @Column(name = "date")
    private String date;

    @JsonProperty
    @Column(name = "flag")
    private int flag;

    public Finance() {}

    public Finance(Type type, Room room, Category category, User user, float amount, String comment, String date) {
        this.type = type;
        this.room = room;
        this.user = user;
        this.category = category;
        this.date = date;
        this.comment = comment;
        this.amount = amount;
    }

    public Finance(Type type, Room room, Category category, User user, float amount, String comment, String date, int flag) {
        this.type = type;
        this.room = room;
        this.user = user;
        this.category = category;
        this.date = date;
        this.comment = comment;
        this.amount = amount;
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }
}
