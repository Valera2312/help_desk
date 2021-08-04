package com.example.accessingdatamysql.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "request", schema = "help_desk", catalog = "")
public class Request {


    private Department departmentByDepartament;
    private RequestCaregory requestCaregoryByCategoryId;
    private Dispatcher dispatcherByDispatcherId;
    private Users usersByUserId;

    private long id;
    private String name;
    private String priority;
    private String status;
    private String initiator;



    private LocalDateTime deadline;

    private LocalDateTime dateOfRequest;

    private String comments;
    private String description;
    private String filename;
    private Users userByUserId;
    private long departament = 1;
    private long category_id = 1;
    private int dispatcher_id = 1;
    private int user_id;



    private Collection<RepairTeam> repairTeamsById;

    public Request(String name,
                   String description,
                   String initiator,
                   int user_id,
                   String deadline
                  ) throws ParseException {

        this.name = name;
        this.description = description;
        this.initiator = initiator;
        this.user_id = user_id;
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.deadline = LocalDateTime.parse(deadline);

    }

    public Request() {

    }
    public String StringdateOfRequest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.dateOfRequest.format(formatter);
    }


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "dispatcher")
    public int getDispatcher_id() {
        return dispatcher_id;
    }

    public void setDispatcher_id(int dispatcher_id) {
        this.dispatcher_id = dispatcher_id;
    }


    //@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "date_of_request")
    public LocalDateTime getDateOfRequest() {


        return dateOfRequest;
    }

    public void setDateOfRequest(LocalDateTime dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }



    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "initiator")
    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    @Column(name = "deadline")
    public String getDeadline() {

        return this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm" ));

    }
    public void setDeadline(String deadline) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.deadline = LocalDateTime.parse(deadline,formatter);
    }

    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name = "priority")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Column(name = "departament")
    public long getDepartament() {
        return departament;
    }

    public void setDepartament(long departament) {
        this.departament = departament;
    }


    @Column(name = "category_id")
    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }


    @Column(name = "user_id", nullable = false)

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "departament",referencedColumnName = "id",updatable = false,insertable = false)
    public Department getDepartmentByDepartament() {
        return departmentByDepartament;
    }

    public void setDepartmentByDepartament(Department departmentByDepartament) {
        this.departmentByDepartament = departmentByDepartament;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id",updatable = false,insertable = false)
    public RequestCaregory getRequestCaregoryByCategoryId() {
        return requestCaregoryByCategoryId;
    }

    public void setRequestCaregoryByCategoryId(RequestCaregory requestCaregoryByCategoryId) {
        this.requestCaregoryByCategoryId = requestCaregoryByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",updatable = false,insertable = false)
    public Users getUser() {
        return userByUserId;
    }

    public void setUser(Users userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "dispatcher", referencedColumnName = "id",updatable = false,insertable = false)
    public Dispatcher getDispatcherByDispatcherId() {
        return dispatcherByDispatcherId;
    }

    public void setDispatcherByDispatcherId(Dispatcher dispatcherByDispatcherId) {
        this.dispatcherByDispatcherId = dispatcherByDispatcherId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @OneToMany(mappedBy = "requestById")
    public Collection<RepairTeam> getRepairTeamsById() {
        return repairTeamsById;
    }

    public void setRepairTeamsById(Collection<RepairTeam> repairTeamsById) {
        this.repairTeamsById = repairTeamsById;
    }
}
