package com.ex.Services;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;


public class SessionFactoryHelper {


    static SessionFactory factory;
    static ServiceRegistry registry;

    static {
        try {
            Configuration conf = new Configuration()

                    .configure();
            registry = new StandardServiceRegistryBuilder().applySettings(
                    conf.getProperties()).build();
            factory = conf.buildSessionFactory(registry);
        } catch (Throwable ex) {
            System.err.println("Failed to create session factory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }

    //    StandardServiceRegistryBuilder.destroy((org.hibernate.service.ServiceRegistry) registry);

    }


    public static SessionFactory getSessionFactory() {
        return factory;
    }

}
