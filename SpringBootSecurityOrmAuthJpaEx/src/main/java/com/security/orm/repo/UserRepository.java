package com.security.orm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.orm.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
