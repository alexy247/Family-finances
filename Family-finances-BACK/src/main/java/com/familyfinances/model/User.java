package com.familyfinances.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Используя дефолтное значение из таблицы пользователь создается
    @ManyToOne
    @JoinColumn(name="id_role", nullable=false)
    private Role role;

    @ManyToMany(mappedBy = "users")
    private Set<Room> rooms;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Finance> finances;

    @JsonProperty
    private String name;

    @JsonProperty
    private String date;

    @JsonProperty
    private String password;

    private String access_token;

    public User() {}

    public User(Role role, String name, String date, String password, String access_token) {
        this.role = role;
        this.name = name;
        this.date = date;
        this.password = password;
        this.access_token = access_token;
    }

    public void setRooms(Room room) {
        this.rooms = Collections.singleton(room);
    }

    public void setFinances(Finance finance) {
        this.finances = Collections.singleton(finance);
    }

    public Role getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return false;
    }
}
