package com.nhuquynh.thuchanh;

import com.nhuquynh.Common.Locators;
import com.nhuquynh.Common.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class ThucHanhCRM_Part1 extends BaseTest {
    @Test
    public void testCustomerCRM() throws InterruptedException {

        driver.get("https://crm.anhtester.com/admin/authentication");
        driver.findElement(By.xpath(Locators.inputEmail)).clear();
        driver.findElement(By.xpath(Locators.inputPassword)).clear();
        driver.findElement(By.xpath(Locators.inputEmail)).sendKeys("admin@example.com");
        driver.findElement(By.xpath(Locators.inputPassword)).sendKeys("123456");
        driver.findElement(By.xpath(Locators.buttonLogin)).click();

        driver.findElement(By.xpath(Locators.menuCustomers)).click();
        System.out.println(driver.findElement(By.xpath("//span[normalize-space()='Customers Summary']")).getText());
        Thread.sleep(2000);
        driver.findElement(By.xpath(Locators.buttonAddNewCustomer)).click();
        driver.findElement(By.xpath(Locators.inputCompany)).sendKeys("CTy Vĩnh Tường");
        driver.findElement(By.xpath(Locators.inputVAT)).sendKeys("10");
        driver.findElement(By.xpath(Locators.inputPhoneNumber)).sendKeys("028yyzzxxx");
        driver.findElement(By.xpath(Locators.inputWebsite)).sendKeys("vinhtuong.com.vn");

        driver.findElement(By.xpath(Locators.dropdownGroup)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Locators.inputSearchGroup)).sendKeys("VIP");
        Thread.sleep(2000);
        driver.findElement(By.xpath(Locators.itemVIP)).click();
        driver.findElement(By.xpath(Locators.dropdownGroup)).click(); //=> đóng dropdown
        Thread.sleep(2000);

        driver.findElement(By.xpath(Locators.dropdownLanguage)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Locators.itemVietnamese)).click(); //=> đóng dropdown
        driver.findElement(By.xpath(Locators.dropdownLanguage)).click();

        driver.findElement(By.xpath(Locators.inputAddress)).sendKeys("HCM");
        driver.findElement(By.xpath(Locators.inputCity)).sendKeys("HCM");
        driver.findElement(By.xpath(Locators.inputState)).sendKeys("Q1");
        driver.findElement(By.xpath(Locators.inputZip)).sendKeys("7000");

        Thread.sleep(2000);
        driver.findElement(By.xpath(Locators.dropdownCountry)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Locators.inputSearchCountry)).sendKeys("Vietnam");
        Thread.sleep(2000);
        driver.findElement(By.xpath(Locators.itemVietnamCountry)).click();
        //=> ở đây chọn Vietnam xong tự động đóng dropdown nên kh cần click lần nữa
        driver.findElement(By.xpath(Locators.buttonSave)).click();
        Thread.sleep(2000);

        //Check alert message nếu có

        //Check item customer hiển thị tại trang Customer list
        driver.findElement(By.xpath(Locators.menuCustomers)).click();
        driver.findElement(By.xpath(Locators.inputSearchCustomer)).sendKeys("CTy Vĩnh Tường");
        Thread.sleep(2000);
        System.out.println("isDisplay (Customer List)" + driver.findElement(By.xpath(Locators.itemCustomerFirst)).isDisplayed());
        System.out.println("Customer in List: " + driver.findElement(By.xpath(Locators.itemCustomerFirst)).getText());

        //Check data đã add đúng hết chưa
        driver.findElement(By.xpath(Locators.itemCustomerFirst)).click();
        System.out.println("Company Detail: " + driver.findElement(By.xpath(Locators.inputCompany)).getAttribute("value"));
        System.out.println(driver.findElement(By.xpath(Locators.inputVAT)).getAttribute("value"));
        System.out.println(driver.findElement(By.xpath(Locators.inputPhoneNumber)).getAttribute("value"));
        System.out.println(driver.findElement(By.xpath(Locators.inputWebsite)).getAttribute("title"));
        System.out.println(driver.findElement(By.xpath(Locators.dropdownGroup)).getAttribute("title"));

        //Check dạng Integration (liên kết với module khác: Customer liên kết với Project)
        //=> ktra xem sau khi save data ở trang Customer, bên Project => tại dropdown Customer có xuất hiện data đã save kh

        Thread.sleep(2000);

    }


}
