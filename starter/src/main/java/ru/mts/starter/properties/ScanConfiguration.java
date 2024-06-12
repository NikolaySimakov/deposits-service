package ru.mts.starter.properties;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("ru.mts.starter")
@EnableJpaRepositories(basePackages = "ru.mts.starter.dao")
@EntityScan(basePackages = "ru.mts.starter.entity")
@EnableTransactionManagement
public class ScanConfiguration {

}
