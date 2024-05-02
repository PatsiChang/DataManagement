package org.dataManage.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"org.dataManage.repository.h2"},
    entityManagerFactoryRef = "h2EntityManagerFactory",
    transactionManagerRef = "h2TransactionManager")
public class H2JpaConfig {

    //    @Primary
    @Bean(name = "h2DataSourceProperties")
    @ConfigurationProperties("spring.datasource-h2")
    public DataSourceProperties h2DataSourceProperties() {
        return new DataSourceProperties();
    }

    //    @Primary
    @Bean(name = "h2DataSource")
//    @ConfigurationProperties("spring.datasource.h2.configuration")
    public DataSource h2DataSource(@Qualifier("h2DataSourceProperties") DataSourceProperties h2DataSourceProperties) {
        return h2DataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    //    @Primary
    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(EntityManagerFactoryBuilder h2FactoryBuilder,
                                                                         @Qualifier("h2DataSource")
                                                                         DataSource h2DataSource) {
        Map<String, String> primaryJpaProperties = new HashMap<>();
//        primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        primaryJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return h2FactoryBuilder.dataSource(h2DataSource)
            .packages("org.dataManage.entity")
            .persistenceUnit("h2DataSource")
            .properties(primaryJpaProperties)
            .build();
    }

    //    @Primary
    @Bean(name = "h2TransactionManager")
    public PlatformTransactionManager h2TransactionManager(@Qualifier("h2EntityManagerFactory")
                                                           EntityManagerFactory h2ManagerFactory) {
        return new JpaTransactionManager(h2ManagerFactory);
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}
