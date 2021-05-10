package com.kudelich.server.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.kudelich.server.repository")
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("com.kudelich.server")
public class DatabaseConfig {
    @Resource
    private Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty("db.entity.package"));

        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    private Properties getHibernateProperties() {
        try {
            Properties properties = new Properties();
            InputStream stream = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(stream);

            return properties;
        } catch (IOException ex) {
            throw new IllegalArgumentException("Can't find hibernate/properties", ex);
        }
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(environment.getRequiredProperty("db.url"));
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));

        dataSource.setInitialSize(Integer.parseInt(environment.getRequiredProperty("db.initialSize")));
        dataSource.setMinIdle(Integer.parseInt(environment.getRequiredProperty("db.minIdle")));
        dataSource.setMaxIdle(Integer.parseInt(environment.getRequiredProperty("db.maxIdle")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(environment.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(environment.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(environment.getRequiredProperty("db.testOnBorrow")));
        dataSource.setValidationQuery(environment.getRequiredProperty("db.validationQuery"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());

        return manager;
    }
}
