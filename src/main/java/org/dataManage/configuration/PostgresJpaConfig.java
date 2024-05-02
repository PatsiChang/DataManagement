package org.dataManage.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"org.dataManage.repository.postgres"},
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager")
public class PostgresJpaConfig {

    @Bean(name = "postgresDataSourceProperties")
    @ConfigurationProperties("spring.datasource-postgres")
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "postgresDataSource")
    public DataSource postgresDataSource(@Qualifier("postgresDataSourceProperties")
                                         DataSourceProperties postgresDataSourceProperties) {
        return postgresDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder
                                                                                   postgresFactoryBuilder,
                                                                               @Qualifier("postgresDataSource")
                                                                               DataSource postgresDataSource) {
        Map<String, String> postgresJpaProperties = new HashMap<>();
//        postgresJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        postgresJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return postgresFactoryBuilder
            .dataSource(postgresDataSource)
            .packages("org.dataManage.entity")
            .persistenceUnit("postgresDataSource")
            .properties(postgresJpaProperties)
            .build();
    }

    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(@Qualifier("postgresEntityManagerFactory")
                                                                 EntityManagerFactory postgresManagerFactory) {
        return new JpaTransactionManager(postgresManagerFactory);
    }
}
