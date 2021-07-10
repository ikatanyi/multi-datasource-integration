package com.smart.edi.akuh.provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
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
@EnableJpaRepositories(basePackages = "com.smart.edi.akuh.provider.*.repository", entityManagerFactoryRef = "providerEntityManager", transactionManagerRef = "providerTransactionManager")
@Profile("!tc")
public class PersistenceproviderAutoConfiguration {
    @Autowired
    private Environment env;

//    public PersistenceIntegrationAutoConfiguration() {
//        super();
//    }

    //

    @Bean
    public LocalContainerEntityManagerFactoryBean providerEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(providerDataSource());
        em.setPackagesToScan("com.smart.edi.akuh.provider.*.model");

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
    @ConfigurationProperties(prefix="datasource.akuh")
    public DataSource providerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager providerTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(providerEntityManager().getObject());
        return transactionManager;
    }
    
    protected Map<String, Object> jpaProperties() {
    Map<String, Object> props = new HashMap<>();
    
    return props;
}

}
