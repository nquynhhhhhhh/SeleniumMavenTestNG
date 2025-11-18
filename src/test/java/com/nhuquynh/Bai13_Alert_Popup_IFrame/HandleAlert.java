package com.nhuquynh.Bai13_Alert_Popup_IFrame;

import com.nhuquynh.Common.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HandleAlert extends BaseTest {

    @Test
    public void demoHandleAlertAccept() throws InterruptedException {
        driver.get("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        Thread.sleep(2000);

        //Click vào nút "Click Me" thứ nhất
        driver.findElement(By.xpath("(//button[text()='Click Me'])[1]")).click();
        Thread.sleep(1000);

        //Khởi tạo class Alert
        Alert alert1 = driver.switchTo().alert();
        //Dùng hàm accept() để xác nhận Alert (tương ứng click vào nút OK)
        alert1.accept();
        //driver.switchTo().alert().accept();

        Thread.sleep(1000);
    }

    @Test
    public void demoHandleAlertDismiss() throws InterruptedException {
        driver.get("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        Thread.sleep(2000);

        //Click vào nút "Click Me" thứ hai
        driver.findElement(By.xpath("(//button[text()='Click Me'])[2]")).click();
        Thread.sleep(1000);

        //Khởi tạo class Alert
        Alert alert2 = driver.switchTo().alert();
        //Dùng hàm dismiss() để từ chối Alert (tương ứng click vào nút Cancel)
        alert2.dismiss();

        Assert.assertTrue(driver.findElement(By.xpath("//p[@id='confirm-demo']")).isDisplayed(),"Chưa click được nút Cancel");
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='confirm-demo']")).getText(),"You pressed Cancel!","The message content not match");

        //Nếu bị che bởi iframe quảng cáo thì dùng Scroll to element
        //WebElement element = driver.findElement(By.xpath("(//button[text()='Click Me'])[2]"));
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView(true);", element);
        //=> true là cuộn xuống (đưa element lên trên), false là cuộn lên
        Thread.sleep(1000);
    }

    @Test
    public void demoHandleAlertInputText() throws InterruptedException {
        driver.get("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
        Thread.sleep(2000);

        //Mở Alert Input text, click nút Click Me thứ ba
        driver.findElement(By.xpath("(//button[text()='Click Me'])[3]")).click();
        Thread.sleep(1000);

        //Khởi tạo class Alert
        Alert alert3 = driver.switchTo().alert();
        System.out.println(alert3.getText());
        alert3.sendKeys("Anh Tester Demo Alert");
        alert3.accept();

        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='prompt-demo']")).getText(),
                "You have entered 'Anh Tester Demo Alert' !",
                "Chưa điền được text");

        Thread.sleep(1000);
    }


}
