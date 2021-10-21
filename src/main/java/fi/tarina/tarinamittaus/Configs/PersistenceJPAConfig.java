package fi.tarina.tarinamittaus.Configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig{

    @Value("${spring.dataSource.username}")
    private String username;

    @Value("${spring.dataSource.password}")
    private String password;

    @Value("${spring.dataSource.url}")
    private String url;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${spring.dataSource.driver-class-name}")
    private String driverClassName;

    public PersistenceJPAConfig() {
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "fi.tarina.tarinamittaus.Model" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

//        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//        dataSource.setUrl("jdbc:oracle:thin:@***REMOVED***:1521:***REMOVED***");
//        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/xe");
//        dataSource.setUsername("tarinam");
//        dataSource.setPassword("***REMOVED***");
//        dataSource.setPassword("livirules");
        //dataSource.setSchema("TARINAM");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);

        return properties;
    }
}
