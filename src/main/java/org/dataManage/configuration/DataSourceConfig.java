//package org.dataManage.configuration;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//    @Primary
//    @Bean(name = "h2DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.h2")
//    public DataSource h2DataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "postgresDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.postgres")
//    public DataSource postgresDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//}
