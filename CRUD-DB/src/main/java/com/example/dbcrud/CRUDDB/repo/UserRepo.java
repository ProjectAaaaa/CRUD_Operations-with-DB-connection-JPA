package com.example.dbcrud.CRUDDB.repo;

import com.example.dbcrud.CRUDDB.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserInfo , Integer> {
}
