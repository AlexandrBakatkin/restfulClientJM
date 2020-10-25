package com.bakatkin.SpringBootCRUD.web.repository;

import com.bakatkin.SpringBootCRUD.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    @Query("FROM user u JOIN FETCH u.roles r where u.name = :name")
    User findByUserName(String name);
}
