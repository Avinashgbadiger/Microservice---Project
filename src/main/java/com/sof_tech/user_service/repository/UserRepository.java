package com.sof_tech.user_service.repository;

import com.sof_tech.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,String> {
}
