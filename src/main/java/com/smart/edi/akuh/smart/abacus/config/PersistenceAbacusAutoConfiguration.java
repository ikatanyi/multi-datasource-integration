package com.smart.edi.akuh.smart.abacus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * By default, the persistence-multiple-db.properties file is read for 
 * non auto configuration in PersistenceProductConfiguration. 
 * <p>
 * If we need to use persistence-multiple-db-boot.properties and auto configuration 
 * then uncomment the below @Configuration class and comment out PersistenceProductConfiguration. 
 */
@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(basePackages = "com.smart.edi.akuh.smart.abacus.*.repository", entityManagerFactoryRef = "abacusEntityManager", transactionManagerRef = "abacusTransactionManager")
@Profile("!tc")
public class PersistenceAbacusAutoConfiguration {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean abacusEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(abacusDataSource());
        em.setPackagesToScan("com.smart.edi.akuh.smart.abacus.*.model");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show-sql"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        em.setJpaPropertyMap(properties);
        

        return em;
    }
    
    @Bean
    @Primary
    @ConfigurationProperties(prefix="datasource.smart")
    public DataSource abacusDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager abacusTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(abacusEntityManager().getObject());
        return transactionManager;
    }
    
    protected Map<String, Object> jpaProperties() {
    Map<String, Object> props = new HashMap<>();
    
    return props;
}

}
