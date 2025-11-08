package com.nhuquynh.package_demo.package1;

import org.testng.annotations.Test;

public class TestPackage1_A {
    @Test
    public void testLoginCRM(){
        System.out.println("Test Login success CRM");
    }

    @Test
    public void testCreateCustomer(){
        System.out.println("Test add new Customer");

    }

    @Test
    public void testEditCustomer(){
        System.out.println("Test edit Customer");

    }
}
