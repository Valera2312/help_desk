package com.example.accessingdatamysql.repo;

import com.example.accessingdatamysql.models.RepairTeam;
import com.example.accessingdatamysql.models.Request;
import org.springframework.data.repository.CrudRepository;

public interface RepairTeamRepo extends CrudRepository<RepairTeam,Long> {


}
