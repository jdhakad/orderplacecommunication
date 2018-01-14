package com;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by deepak.dhakad on 6/8/17.
 */

@SpringBootApplication(scanBasePackages = "com")
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAutoConfiguration
@EnableCaching
@EnableJpaRepositories(basePackages = "com")
public class Application extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){
        return hemf.getSessionFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
