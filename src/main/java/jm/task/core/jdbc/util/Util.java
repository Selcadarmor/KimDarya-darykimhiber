package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.util.Properties;
import org.hibernate.Session;

public class Util {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/projectdaryakim");
            properties.setProperty("hibernate.connection.username", "root");
            properties.setProperty("hibernate.connection.password", "070196^Slava*");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("hibernate.show_sql", "true");
            properties.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(jm.task.core.jdbc.model.User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private Util() {}
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }
    public static Connection getConnection() {
        Session session = sessionFactory.openSession();
        try {
            return session.doReturningWork(connection -> connection);
        } catch (Exception e) {
            throw new RuntimeException("Error while obtaining connection from session.", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
