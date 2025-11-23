package com.nhuquynh.Bai15_Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoImplicitWait {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Set timeout for implicitlyWait
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test (priority = 1)
    public void demoImplicitWait() throws InterruptedException {
        driver.get("https://hrm.anhtester.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("iusername")).sendKeys("admin_example");
        driver.findElement(By.id("ipassword")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Click menu dự án
        driver.findElement(By.xpath("//span[contains(text(),'Projects')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        //muốn không áp dụng wait 10s ở testcase sau thì thêm dòng này ở cuối

        Thread.sleep(2000);
        driver.quit();
    }

    @Test (priority = 2)
    public void demoImplicitWait02() throws InterruptedException {
        driver.get("https://hrm.anhtester.com/");

        driver.findElement(By.id("iusername")).sendKeys("admin_example");
        driver.findElement(By.id("ipassword")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Click menu dự án
        driver.findElement(By.xpath("//span[contains(text(),'Projects')]")).click();

        Thread.sleep(2000);
        driver.quit();
    }
}
