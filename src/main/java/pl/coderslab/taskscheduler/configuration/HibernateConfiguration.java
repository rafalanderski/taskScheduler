package pl.coderslab.taskscheduler.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "pl.coderslab.taskscheduler.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        try {
            sessionFactory.setDataSource(dataSource());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        sessionFactory.setPackagesToScan(new String[] { "pl.coderslab.taskscheduler" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    @Bean
    public DataSource dataSource() throws URISyntaxException {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://" + "ec2-23-23-225-116.compute-1.amazonaws.com" + ":5432/d1n3brhs8lvbt?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
        dataSource.setUsername("idtokhhtwmlbkq");
        dataSource.setPassword("b89f790b01db4899f02f326b5f8c00194416d51364a585e4b436e02e729efdfc");

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}

