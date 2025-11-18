package com.nhuquynh.Bai11_Assertions;

import com.nhuquynh.Common.BaseTest;
import com.nhuquynh.Common.Locators;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoHardAssert extends BaseTest {
    @Test
    public void testLoginSucces() throws InterruptedException {
        driver.get("https://crm.anhtester.com/admin/authentication");
        //Kiểm tra đối tượng header và giá trị header
        Assert.assertTrue(driver.findElement(By.xpath(Locators.headerLogin)).isDisplayed(), "Giá trị Login page không hiển thị");
        Assert.assertEquals(driver.findElement(By.xpath(Locators.headerLogin)).getText(), "Login", "Giá trị header không đúng");

        driver.findElement(By.xpath(Locators.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(Locators.inputPassword)).sendKeys("123456");
        //driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath(Locators.buttonLogin)).click();

        Assert.assertTrue(driver.findElement(By.xpath(Locators.menuDashboard)).isDisplayed(),"Menu Dashboard không xuất hiện");
        //Locator kh tìm thấy thì findElement đã báo lỗi trước => để đảm bảo Assert hoạt động phải đảm bảo Element đúng
        Assert.assertEquals(driver.findElement(By.xpath(Locators.menuDashboard)).getText(),"Dashboard","Giá trị Menu Dashboard");

        //so sánh chứa, trong content có chưa text ... không (chỉ ktra String, không có int, boolean..,)
        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Invoices Awaiting Payment']")).getText().contains("Invoices Awaiting"));

    }


}
