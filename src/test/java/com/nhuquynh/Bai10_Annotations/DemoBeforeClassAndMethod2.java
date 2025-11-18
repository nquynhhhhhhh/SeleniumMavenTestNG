package com.nhuquynh.Bai10_Annotations;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoBeforeClassAndMethod2 extends SetUpClass{

    @Test
    public void testloginCRM()  {
        driver.get("https://crm.anhtester.com/admin/authentication");
        driver.findElement(By.id("email")).sendKeys("admin@example.com");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.tagName("button")).click();
    }

 }
