package com.polinwei.myspring.db.mongo.dao;

import com.polinwei.myspring.db.mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User,String> {
}
