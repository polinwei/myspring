package com.polinwei.myspring.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.polinwei.myspring.db.mongo.auth.dao"},
        mongoTemplateRef = "authMongoTemplate")
@ConfigurationProperties(prefix = "dbmongo.auth.datasource")
public class DbMongoAuthConfig extends MongoAbstractAuthConfig {

    /**
     * Implementation of the MongoTemplate factory method
     *
     * @Bean gives a name (secondaryMongoTemplate) to the created MongoTemplate instance Note that
     * this method doesn't have @Primary
     */

    @Override
    @Bean(name = "authMongoTemplate")
    public MongoTemplate getMongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
