package com.wwesolowski.appointmentApp;

import com.wwesolowski.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Base64;
import java.util.TimeZone;

@SpringBootApplication
@ComponentScan("com.wwesolowski")
@EnableJpaRepositories(basePackages = {"com.wwesolowski"})
@EntityScan(basePackages = {"com.wwesolowski"})
public class AppointmentApp {


    public static void main(String[] args) {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate_cfg.xml")
                        .build();

        SessionFactory sessionFactory = null;

        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.exit(1);
        }
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
            Base64.Encoder encoder = Base64.getEncoder();
            Customer firstCustomer = new Customer("0001", encoder.encodeToString("1234".getBytes()));
            Customer secondCustomer = new Customer("0002", encoder.encodeToString("1234".getBytes()));

            Doctor firstDoctor = new Doctor("0001");
            Doctor secondDoctor = new Doctor("0002");

            PersonalInfo firstCustomerInfo = new PersonalInfo("Andrzej", "Kowalski", "andrzej@o2.pl", "111222333");
            PersonalInfo secondCustomerInfo = new PersonalInfo("Jan", "Kowalski", "jan@o2.pl", "333444555");
            PersonalInfo firstDoctorInfo = new PersonalInfo("Agnieszka", "Kowalska", "agnieszka@o2.pl", "666777999");
            PersonalInfo secondDoctorInfo = new PersonalInfo("Maria", "Kowalska", "maria@o2.pl", "999888777");

            firstCustomerInfo.setCustomer(firstCustomer);
            secondCustomerInfo.setCustomer(secondCustomer);
            firstDoctorInfo.setDoctor(firstDoctor);
            secondDoctorInfo.setDoctor(secondDoctor);

            firstCustomer.setPersonalInfo(firstCustomerInfo);
            secondCustomer.setPersonalInfo(secondCustomerInfo);
            firstDoctor.setPersonalInfo(firstDoctorInfo);
            secondDoctor.setPersonalInfo(secondDoctorInfo);

            session.persist(firstCustomerInfo);
            session.persist(secondCustomerInfo);
            session.persist(firstDoctorInfo);
            session.persist(secondDoctorInfo);
            session.persist(firstCustomer);
            session.persist(secondCustomer);
            session.persist(firstDoctor);
            session.persist(secondDoctor);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }

        sessionFactory.close();
        SpringApplication.run(AppointmentApp.class, args);
    }

}
