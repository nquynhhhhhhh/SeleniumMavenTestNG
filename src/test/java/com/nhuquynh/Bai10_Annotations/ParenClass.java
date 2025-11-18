package com.nhuquynh.Bai10_Annotations;

import org.testng.annotations.*;

public class ParenClass {
    @BeforeClass
    public void beforeClassParent() {
        System.out.println("Before Class PARENT");
    }

    @AfterClass
    public void afterClassParent() {
        System.out.println("After Class PARENT");
    }

    @BeforeMethod
    public void beforeMethodParent() {
        System.out.println("Before Method PARENT");
    }

    @AfterMethod
    public void afterMethodParent() {
        System.out.println("After Method PARENT");
    }
}
