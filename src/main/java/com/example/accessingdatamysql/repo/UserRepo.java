package com.example.accessingdatamysql.repo;

import com.example.accessingdatamysql.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Users, Integer> {
    @Query("select e from Users e where e.username = :username")

    Users findByUsername(@Param("username") String username);


}