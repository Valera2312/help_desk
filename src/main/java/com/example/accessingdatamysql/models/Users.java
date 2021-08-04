package com.example.accessingdatamysql.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "help_desk", catalog = "")
public class Users {
    private int id;
    private String username;
    private String password;
    private int active;
    private String role;

    private Collection<Dispatcher> dispatchersById;
    private Collection<Executor> executorsById;
    private Collection<Request> requestsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Basic
    @Column(name = "role", nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && active == users.active && Objects.equals(username, users.username) && Objects.equals(password, users.password) && Objects.equals(role, users.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, active, role);
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<Dispatcher> getDispatchersById() {
        return dispatchersById;
    }

    public void setDispatchersById(Collection<Dispatcher> dispatchersById) {
        this.dispatchersById = dispatchersById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<Executor> getExecutorsById() {
        return executorsById;
    }

    public void setExecutorsById(Collection<Executor> executorsById) {
        this.executorsById = executorsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<Request> getRequestsById() {
        return requestsById;
    }

    public void setRequestsById(Collection<Request> requestsById) {
        this.requestsById = requestsById;
    }
}
