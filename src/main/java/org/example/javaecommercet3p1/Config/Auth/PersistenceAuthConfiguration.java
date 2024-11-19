package org.example.javaecommercet3p1.Config.Auth;


import org.example.javaecommercet3p1.Repos.Auth.CountryRepository;
import org.example.javaecommercet3p1.Repos.Auth.OrderRepository;
import org.example.javaecommercet3p1.Repos.Auth.StateRepository;
import org.example.javaecommercet3p1.Repos.Auth.UserRepository;
import com.google.common.base.Preconditions;
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
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "authEntityManager",
        transactionManagerRef = "authTransactionManager",
        basePackageClasses = {UserRepository.class ,OrderRepository.class, CountryRepository.class,  StateRepository.class, CountryRepository.class}) // TODO: put transaction in seperate database
public class PersistenceAuthConfiguration {
    @Autowired
    private Environment env;

    public PersistenceAuthConfiguration() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean authEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(authDataSource());
        em.setPackagesToScan("org/example/javaecommercet3p1/Entities/Auth");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);


        return em;
    }

    @Bean("authDataSource")
    public DataSource authDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("auth.jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager authTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(authEntityManager().getObject());
        return transactionManager;
    }



}
