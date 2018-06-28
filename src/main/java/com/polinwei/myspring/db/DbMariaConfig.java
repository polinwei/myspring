package com.polinwei.myspring.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        basePackages = {"com.polinwei.myspring.db.maria.dao",
            "com.polinwei.myspring.authentication.jwt.service",
            "com.polinwei.myspring.authentication.jwt.security.dao"}
)
public class DbMariaConfig {
    static final Logger logger = LoggerFactory.getLogger(DbMariaConfig.class);

    @Primary
    @Bean(name = "dsMaria")
    @ConfigurationProperties(prefix = "dbmaria.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory") // Primary 一定要預設用 entityManagerFactory
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(hibernateProperties());

        factoryBean.setPackagesToScan("com.polinwei.myspring.db.maria.model","com.polinwei.myspring.authentication.jwt.model");
        factoryBean.setPersistenceUnitName("puMaria");

        return factoryBean;
    }
    /*
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dsMaria") DataSource dataSource) {

        return builder.dataSource(dataSource)
                .packages("com.polinwei.myspring.db.maria.model")
                .persistenceUnit("puMaria")
                .build();
    }
    */

    @Primary
    @Bean(name = "transactionManager") // Primary 一定要預設用 transactionManager
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * hibernate.hbm2ddl.auto 的參數
     * validate     : 載入hibernate時，驗證創建資料庫表結構
     * create       : 每次載入hibernate，重新創建資料庫表結構，這就是導致資料庫表資料丟失的原因。
     * create-drop  : 載入hibernate時創建，退出是刪除表結構
     * update       : 載入hibernate自動更新資料庫結構
     * none         : 不作任何控管
     *
     * @return
     */
    private Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        return hibernateProperties;
    }

}
