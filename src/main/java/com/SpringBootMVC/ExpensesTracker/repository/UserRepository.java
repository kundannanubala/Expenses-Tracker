package com.SpringBootMVC.ExpensesTracker.repository;

import com.SpringBootMVC.ExpensesTracker.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String username);
}
