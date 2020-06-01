package com.uabc.edu.mx.tecmonkey.repository;

import com.uabc.edu.mx.tecmonkey.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User>findByUsername(String username);
}
