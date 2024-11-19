
package org.example.javaecommercet3p1.Config.Group1;


import com.google.common.base.Preconditions;
import org.example.javaecommercet3p1.Repos.Group1.DesktopRepository;
import org.example.javaecommercet3p1.Repos.Group1.PhoneRepository;
import org.example.javaecommercet3p1.Repos.Group1.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:multiple-db.properties"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "group1EntityManager",
        transactionManagerRef = "group1TransactionManager",
        basePackageClasses = {
                DesktopRepository.class, PhoneRepository.class, ProductCategoryRepository.class
                ,
        })
public class JobPersistenceConfig {
    @Autowired
    private Environment env;

    public JobPersistenceConfig() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean group1EntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPackagesToScan("org/example/javaecommercet3p1/Entities/Group1");
        em.setDataSource(group1DataSource());



        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

        em.setJpaPropertyMap(properties);


        return em;
    }

//    @Bean
    @Bean("group1DataSource")
    public DataSource group1DataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("group1.products.jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager group1TransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(group1EntityManager().getObject());
        return transactionManager;
    }



}



