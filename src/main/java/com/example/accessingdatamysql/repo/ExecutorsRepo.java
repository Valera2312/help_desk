package com.example.accessingdatamysql.repo;

import com.example.accessingdatamysql.models.Executor;
import org.springframework.data.repository.CrudRepository;

public interface ExecutorsRepo extends CrudRepository<Executor,Integer> {

}
