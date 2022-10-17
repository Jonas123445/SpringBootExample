package com.mexxon.Config;

import com.mexxon.Repository.ExtendedRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(
        entityManagerFactoryRef = "EntityManagerFactory",
        transactionManagerRef = "TransactionManager",
        basePackages = {"com.mexxon.Repository", },
        repositoryBaseClass = ExtendedRepositoryImpl.class
)

@Component
@Primary
public class DBConfig {
    @Value("${datasource.dialect}")
    private String dialect;

    @Value("${datasource.ddl-auto}")
    private String hbm2ddl;

    @Value("${my.hibernate.generate_statistics}")
    private String generateStatistics;

    @Value("${my.hibernate.show_sql}")
    private String showSql;

    @Value("${my.hibernate.format_sql}")
    private String formatSql;

    @Value("${my.hibernate.use_sql_comments}")
    private String useSqlComments;

    @Value("${my.hibernate.c3p0.min_size}")
    private String c3p0MinSize;

    @Value("${my.hibernate.c3p0.max_size}")
    private String c3p0MaxSize;

    @Value("${my.hibernate.c3p0.timeout}")
    private String c3p0Timeout;

    @Value("${my.hibernate.c3p0.max_statements}")
    private String c3p0MaxStatements;


    @Primary
    @Bean(name = "DataSource")
    @ConfigurationProperties(prefix = "datasource")
    public DataSource dataSource() {




        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean EntityManagerFactory(@Qualifier("DataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[] {"com/mexxon/Entities"});


        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();

        // Hibernate config
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
        properties.setProperty("hibernate.ejb.entitymanager_factory_name", "EntityManager");
        properties.setProperty("hibernate.generate_statistics", generateStatistics);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.use_sql_comments", useSqlComments);
        // c3p0 config
        properties.setProperty("hibernate.c3p0.min_size", c3p0MinSize);
        properties.setProperty("hibernate.c3p0.max_size", c3p0MaxSize);
        properties.setProperty("hibernate.c3p0.timeout", c3p0Timeout);
        properties.setProperty("hibernate.c3p0.max_statements", c3p0MaxStatements);




        em.setJpaProperties(properties);
        em.setPersistenceUnitName("Unit");


        return em;
    }



    @Primary
//    @PersistenceContext(unitName="Unit")
    @Bean(name = "TransactionManager")
    public PlatformTransactionManager TransactionManager(
            @Qualifier("EntityManagerFactory") EntityManagerFactory
                    EntityManagerFactory
    ) {
        return new JpaTransactionManager(EntityManagerFactory);
    }
}
