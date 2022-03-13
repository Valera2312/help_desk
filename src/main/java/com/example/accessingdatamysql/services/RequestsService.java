package com.example.accessingdatamysql.services;

import com.example.accessingdatamysql.models.Request;
import com.example.accessingdatamysql.repo.RequestRepo;
import com.example.accessingdatamysql.repo.RequestRepositoryPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RequestsService {
    @Autowired
    RequestRepo requestRepo;

    RequestRepositoryPaging requestPaging;
    @Autowired
    public RequestsService(RequestRepositoryPaging requestPaging) {
        this.requestPaging = requestPaging;
    }


    public Iterable<Request> AllRequests(){
        return requestRepo.findAll();
    }

    public Sort sorting(String sortField,String sortDirection){
         return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
    }


    public Page<Request> findPaginated(int pageNo, int pageSize, String sortField,String sortDirection){

        Pageable pageable = PageRequest.of(pageNo - 1,pageSize,sorting(sortField,sortDirection));
        return this.requestPaging.findAll(pageable);

    }

    public Page<Request> findPaginatedByUserId(int pageNo, int pageSize,int user_id,String sortField,String sortDirection){

        Pageable pageable = PageRequest.of(pageNo - 1,pageSize,sorting(sortField,sortDirection));
        return this.requestPaging.findAllByUser_id(user_id,pageable);

    }

    public void UpdatePriority(String priority,Long id){
        requestRepo.UpdatePriority(priority,id);
    }
    public void UpdateStatus(String status,Long id){
        requestRepo.UpdateStatus(status,id);
    }
    public void UpdateComment(String comment,Long id){
        requestRepo.UpdateComment(comment,id);
    }
    //public List<Request> findAllByUser_id(int user_id){
       // return requestRepo.findAllByUser_id(user_id);
  //  }


}
