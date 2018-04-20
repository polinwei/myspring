package com.polinwei.myspring.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Data;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Data
public abstract class MongoAbstractAuthConfig {

    //Mongo DB Properties
    private String host, database, username, password;
    private int port,connectionsPerHost,minConnectionsPerHost ;

    /*
     * Method that creates MongoDbFactory
     * Common to both of the MongoDb connections
     */
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(getMongoClient(), database);
    }

    /*
     * Method that creates MongoClient
     */
    private MongoClient getMongoClient() {

        // MongoDB地址列表
        ServerAddress serverAddress = new ServerAddress(host, port);

        // Set credentials
        MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());

        //客户端配置（连接数、副本集群验证）
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(connectionsPerHost);
        //builder.minConnectionsPerHost(minConnectionsPerHost);
        MongoClientOptions mongoClientOptions = builder.build();

        return new MongoClient(serverAddress, credential, mongoClientOptions);
    }

    /*
     * Factory method to create the MongoTemplate
     */
    abstract public MongoTemplate getMongoTemplate();
}
