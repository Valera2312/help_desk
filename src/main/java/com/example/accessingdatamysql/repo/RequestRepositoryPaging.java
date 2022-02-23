package com.example.accessingdatamysql.repo;

import com.example.accessingdatamysql.models.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface RequestRepositoryPaging extends PagingAndSortingRepository<Request,Long> {

    Page<Request> findAllByUser_id(int user_id,Pageable pageable);
}
