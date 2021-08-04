package com.example.accessingdatamysql.repo;

import com.example.accessingdatamysql.models.Request;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.UniqueConstraint;
import java.util.List;
import java.util.Optional;


@Repository
public interface RequestRepo extends CrudRepository<Request, Long> {

    List<Request> findAllByUser_id(int user_id);

    @Transactional
    @Modifying
    @Query("update Request r set r.priority = :priority where r.id = :id")
    void UpdatePriority( @Param("priority") String priority,
                         @Param("id") Long id);

    Request findById(long id);
}