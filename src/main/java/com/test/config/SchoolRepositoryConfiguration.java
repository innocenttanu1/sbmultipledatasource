package com.test.config;

import com.test.entity.School;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.test.repository.school",
        entityManagerFactoryRef = "schoolEntityManagerFactory",
        transactionManagerRef = "schoolTransactionManager"
)
public class SchoolRepositoryConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "school.datasource")
    public DataSourceProperties schoolDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource schoolDataSource() {
        return schoolDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "schoolEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean schoolEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(schoolDataSource()).packages(School.class)
                .build();
    }

    @Bean(name = "schoolTransactionManager")
    public PlatformTransactionManager schoolTransactionManager(
            @Qualifier("schoolEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }


}
