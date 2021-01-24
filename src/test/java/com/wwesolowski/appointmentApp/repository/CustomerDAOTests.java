package com.wwesolowski.appointmentApp.repository;

import com.wwesolowski.model.Customer;
import com.wwesolowski.repository.CustomerDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDAOTests {

    @Autowired
    CustomerDAO customerDao;

    @Test
    @Transactional
    public void createCustomerTest() throws Exception {
        Customer customer = new Customer("1234", "1234");
        Customer created = customerDao.save(customer);
        Assert.assertEquals(created.getCustomerDigitId(), customer.getCustomerDigitId());
    }

    @Test
    @Transactional
    public void deleteCustomerTest() {
        Customer customer = new Customer("1234", "1234");
        customerDao.save(customer);
        customerDao.delete(customer);
        Assert.assertEquals(null, customerDao.findByCustomerDigitId("1234"));
    }
}
