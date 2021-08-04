package com.example.accessingdatamysql.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "executor", schema = "help_desk", catalog = "")
public class Executor {
    private int id;
    private int userId;
    private String fio;
    private Users usersByUserId;
    private Collection<RepairTeam> repairTeamsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "FIO", nullable = false)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Executor executor = (Executor) o;
        return id == executor.id && userId == executor.userId && Objects.equals(fio, executor.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, fio);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,updatable = false,insertable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @OneToMany(mappedBy = "executorByIdExecutors")
    public Collection<RepairTeam> getRepairTeamsById() {
        return repairTeamsById;
    }

    public void setRepairTeamsById(Collection<RepairTeam> repairTeamsById) {
        this.repairTeamsById = repairTeamsById;
    }
}
