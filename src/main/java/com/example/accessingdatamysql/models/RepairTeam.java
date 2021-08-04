package com.example.accessingdatamysql.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "repair_team", schema = "help_desk", catalog = "")
public class RepairTeam {
    private Integer idExecutors;
    private Long id;
    private Long idRequest;

    public RepairTeam(){

    }

    public RepairTeam(Integer idExecutors,Long idRequest) {
        this.idExecutors = idExecutors;
        this.idRequest = idRequest;
    }

    @Basic
    @Column(name = "id_request")
    public Long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Long idRequest) {
        this.idRequest = idRequest;
    }


    private Executor executorByIdExecutors;
    private Request requestById;



    @Basic
    @Column(name = "id_executors")
    public Integer getIdExecutors() {
        return idExecutors;
    }
    public void setIdExecutors(Integer idExecutors) {
        this.idExecutors = idExecutors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairTeam that = (RepairTeam) o;
        return Objects.equals(idExecutors, that.idExecutors) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExecutors, id);
    }

    @ManyToOne
    @JoinColumn(name = "id_executors", referencedColumnName = "id",updatable = false,insertable = false)
    public Executor getExecutorByIdExecutors() {
        return executorByIdExecutors;
    }

    public void setExecutorByIdExecutors(Executor executorByIdExecutors) {
        this.executorByIdExecutors = executorByIdExecutors;
    }

    @ManyToOne()
    @JoinColumn(name = "id_request", referencedColumnName = "id",insertable = false,updatable = false)
    public Request getRequestById() {
        return requestById;
    }

    public void setRequestById(Request requestById) {
        this.requestById = requestById;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
