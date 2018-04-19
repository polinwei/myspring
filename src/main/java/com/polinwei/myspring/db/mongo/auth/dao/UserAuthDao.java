package com.polinwei.myspring.db.mongo.auth.dao;

import com.polinwei.myspring.db.mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAuthDao extends MongoRepository<User,String> {

    public User findByUsername(String userName);
}
