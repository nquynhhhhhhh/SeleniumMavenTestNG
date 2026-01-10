package com.nhuquynh.thuchanh;

import com.nhuquynh.Common.BaseTest;
import com.nhuquynh.Common.Locators;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class ThucHanhCRM_Part2 extends BaseTest {
    //NẾU CLASS BASE TEST BỎ DÒNG implicitlyWait (kéo dài testcase) => bị lỗi kh thể thao tác element => mỗi dòng code phải tự thêm
    @Test
    public void testCustomerCRM() throws InterruptedException {
        //NẾU CLASS BASE TEST BỎ DÒNG implicitlyWait (kéo dài testcase) => bị lỗi kh thể thao tác element
        //=> khai báo object + gọi wait => nhưng do mỗi locator là 1 wait nên sẽ dài dòng và phức tạp
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.inputEmail)));
        //=> tạo 1 hàm chung (WebUI) tích hợp cơ chế wait

        //B3: khởi tạo class WebUI để GÁN GIÁ TRỊ driver của BaseTest vào WebUI để class WebUI nhận và truyền dô các hàm sử dụng
        new WebUI(driver);

        driver.get("https://crm.anhtester.com/admin/authentication");
        System.out.println("Open Website: https://crm.anhtester.com/admin/authentication");

        //các hàm xây dựng là static nên kh cần khởi tạo oject mà lấy class. dùng luôn
        WebUI.clearText(By.xpath(Locators.inputEmail));
        WebUI.clearText(By.xpath(Locators.inputPassword));
        WebUI.setText(By.xpath(Locators.inputEmail),"admin@example.com");
        WebUI.setText(By.xpath(Locators.inputPassword),"123456");
        WebUI.clickElement(By.xpath(Locators.buttonLogin));

        WebUI.clickElement(By.xpath(Locators.menuCustomers));
        System.out.println(WebUI.getTextElement(By.xpath("//span[normalize-space()='Customers Summary']")));
        WebUI.clickElement(By.xpath(Locators.buttonAddNewCustomer));
        WebUI.setText(By.xpath(Locators.inputCompany),"CTy Vĩnh Tường 2025");
        WebUI.setText(By.xpath(Locators.inputVAT),"10");
        WebUI.setText(By.xpath(Locators.inputPhoneNumber),"028yyzzxxx");
        WebUI.setText(By.xpath(Locators.inputWebsite),"vinhtuong.com.vn");

        //Nếu TH màn hình web nhỏ/ nhập Company trùng => show text
        // => đẩy UI VIP xuống => kh hiện trên screen => kh click được item VIP
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath(Locators.labelGroup));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        WebUI.clickElement(By.xpath(Locators.dropdownGroup));
        WebUI.setText(By.xpath(Locators.inputSearchGroup),"VIP");
        WebUI.clickElement(By.xpath(Locators.itemVIP));
        WebUI.clickElement(By.xpath(Locators.dropdownGroup));//=> đóng dropdown

        WebUI.clickElement(By.xpath(Locators.dropdownLanguage));
        WebUI.clickElement(By.xpath(Locators.itemVietnamese));
        WebUI.clickElement(By.xpath(Locators.dropdownLanguage));

        WebUI.setText(By.xpath(Locators.inputAddress),"HCM");
        WebUI.setText(By.xpath(Locators.inputCity),"HCM");
        WebUI.setText(By.xpath(Locators.inputState),"Q1");
        WebUI.setText(By.xpath(Locators.inputZip),"7000");

        WebUI.clickElement(By.xpath(Locators.dropdownCountry));
        WebUI.setText(By.xpath(Locators.inputSearchCountry),"Vietnam");
        WebUI.clickElement(By.xpath(Locators.itemVietnamCountry));
        WebUI.clickElement(By.xpath(Locators.buttonSave));

        //Check alert message nếu có
        //Check item customer hiển thị tại trang Customer list
        WebUI.clickElement(By.xpath(Locators.menuCustomers));
        WebUI.setText(By.xpath(Locators.inputSearchCustomer),"CTy Vĩnh Tường 2025");
        Thread.sleep(2000); //đợi trả ra kết quả sau đó mới chờ nó xuất hiện

        WebUI.waitForElementVisible(By.xpath(Locators.itemCustomerFirst)); //thay cho isDisplay
        Assert.assertEquals(WebUI.getTextElement(By.xpath(Locators.itemCustomerFirst)),"CTy Vĩnh Tường 2025");
        System.out.println("Customer in List: " + WebUI.getTextElement(By.xpath(Locators.itemCustomerFirst)));

        //Check data đã add đúng hết chưa
        WebUI.clickElement(By.xpath(Locators.itemCustomerFirst));

        Assert.assertEquals(driver.findElement(By.xpath(Locators.inputCompany)).getAttribute("value"),"CTy Vĩnh Tường 2025","The Company Name not match");
        Assert.assertEquals(driver.findElement(By.xpath(Locators.inputVAT)).getAttribute("value"),"10","The VAT value not match");
        Assert.assertEquals(driver.findElement(By.xpath(Locators.inputPhoneNumber)).getAttribute("value"),"028yyzzxxx","The Phone Number not match");
        Assert.assertEquals(driver.findElement(By.xpath(Locators.inputWebsite)).getAttribute("value"),"vinhtuong.com.vn","The Webside not match");
        Assert.assertEquals(driver.findElement(By.xpath(Locators.dropdownGroup)).getAttribute("title"),"VIP","The Group not match");

        //Check dạng Integration (liên kết với module khác: Customer liên kết với Project)
        //=> ktra xem sau khi save data ở trang Customer, bên Project => tại dropdown Customer có xuất hiện data đã save kh

        Thread.sleep(2000);

    }


}
