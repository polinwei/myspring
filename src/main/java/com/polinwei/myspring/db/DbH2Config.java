package com.polinwei.myspring.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "emfH2",
        transactionManagerRef = "tmH2",
        basePackages = {"com.polinwei.myspring.authentication.jwt.security.dao","com.polinwei.myspring.authentication.jwt.service"}
)
public class DbH2Config {
    static final Logger logger = LoggerFactory.getLogger(DbH2Config.class);

    @Bean(name = "dsH2")
    @ConfigurationProperties(prefix = "dbh2.datasource")
    public DataSource dataSource() { return DataSourceBuilder.create().build(); }

    @Bean(name = "emfH2")
    public LocalContainerEntityManagerFactoryBean emfH2() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaProperties(hibernateProperties());

        factoryBean.setPackagesToScan("com.polinwei.myspring.authentication.jwt.model");
        factoryBean.setPersistenceUnitName("puH2");
        return factoryBean;
    }
    @Bean(name = "tmH2")
    public PlatformTransactionManager transactionManager(
            @Qualifier("emfH2") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        return hibernateProperties;
    }
}
