package com.nhuquynh.Bai11_Assertions;

import com.nhuquynh.Common.BaseTest;
import com.nhuquynh.Common.Locators;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DemoSoftAssert extends BaseTest {
    @Test
    public void testLoginSucces() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        driver.get("https://crm.anhtester.com/admin/authentication");
        //Kiểm tra đối tượng header và giá trị header
        Assert.assertTrue(driver.findElement(By.xpath(Locators.headerLogin)).isDisplayed(), "Giá trị LoginTest page không hiển thị");
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.headerLogin)).getText(), "Login123", "Giá trị header không đúng");
        //Assert.assertEquals(driver.findElement(By.xpath(Locators.headerLogin)).getText(), "Login123", "Giá trị header không đúng");
        //=> nếu dùng Hard => dừng luôn kh check những cái sau

        driver.findElement(By.xpath(Locators.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(Locators.inputPassword)).sendKeys("123456");
        //driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath(Locators.buttonLogin)).click();

        Assert.assertTrue(driver.findElement(By.xpath(Locators.menuDashboard)).isDisplayed(),"Menu Dashboard không xuất hiện");
        //Locator kh tìm thấy thì findElement đã báo lỗi trước => để đảm bảo Assert hoạt động phải đảm bảo Element đúng
        softAssert.assertEquals(driver.findElement(By.xpath(Locators.menuDashboard)).getText(),"Dashboard123","Giá trị Menu Dashboard");

        //so sánh chứa, trong content có chưa text ... không (chỉ ktra String, không có int, boolean..,)
        softAssert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Invoices Awaiting Payment']")).getText().contains("Invoices Awaiting123"),"Content của label không match");

        //BẮT BUỘ PHẢI CÓ
        softAssert.assertAll();
    }


}
