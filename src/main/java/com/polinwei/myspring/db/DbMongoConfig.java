package com.polinwei.myspring.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.polinwei.myspring.db.mongo.dao"},
        mongoTemplateRef = "primaryMongoTemplate")
@ConfigurationProperties(prefix = "dbmongo.datasource")
public class DbMongoConfig extends MongoAbstractConfig {



    @Override
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate getMongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
