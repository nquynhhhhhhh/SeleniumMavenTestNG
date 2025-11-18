package com.nhuquynh.Bai10_Annotations;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class DemoBeforeClassAndMethod extends SetUpClass{

    @Test( priority = 2, description = "Testcase search check blog")
    public void testAnhTesterBlog() throws InterruptedException {
        driver.get("https://anhtester.com");
        driver.findElement(By.xpath("//a[normalize-space()='blog']")).click();
        Thread.sleep(2000);
    }

    @Test ( priority = 1, description = "Testcase search data on CocCoc")
    public void testCocCocSearch() throws InterruptedException {
        driver.get("https://coccoc.com/search");
        driver.findElement(By.xpath("//textarea[@id='main-search-input']")).sendKeys("anhtester", Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//h3[normalize-space()='Anh Tester Automation Testing']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//h3[normalize-space()='Anh Tester Automation Testing']")).isDisplayed());
    }

 }
