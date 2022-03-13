package com.example.accessingdatamysql.repo;

import com.example.accessingdatamysql.models.Executor;
import com.example.accessingdatamysql.models.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExecutorsRepo extends CrudRepository<Executor,Integer> {

}
