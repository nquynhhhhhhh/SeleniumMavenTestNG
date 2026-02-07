package com.nhuquynh.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import java.time.Duration;

public class WebUI {
    //B2: tạo hàm chung chứa các hàm click, sendKeys... + tích hợp cơ chế wait Explicit cho gọn hơn
    private static WebDriver driver; //Tất cả object dùng chung biến

    //viết hàm chung để dùng cơ chế wait (selenium) => phải có driver
    //class WebUI kh kế thừa base test (kh có biến driver)
    //=> nên phải khởi tạo hàm để “đưa driver từ Base Test vào class WebUI” => để thao tác element (click, sendKeys...)
    public WebUI(WebDriver driver) { //truyền tham số vào hàm xây dựng
        this.driver = driver; //this.driver (webUI), driver (base test)
        // (chưa có gán, hiện tại driver = null, kiểu “nếu ai đưa driver cho tôi, tôi sẽ nhận")
        //driver lấy từ base test, khi viết hàm sử dụng phải truyền driver vào hàm xây dựng trên để hàm sử dụng biết nên lấy driver từ đâu
    }

    public static void sleep(double second){
        try {
            Thread.sleep((long) (1000*second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void openWebside(String url) {
        System.out.println("Open Webside: " + url);
        driver.get("https://crm.anhtester.com/admin/authentication");
    }

    public static void clickElement(By by) { //thay thế nguyên cụm cụ thể By.xpath(Locators.inputEmail) = đối tượng by
        System.out.println("Click on element: " + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)); //sẵn sàng có thể click
        driver.findElement(by).click();
    }

    public static void setText(By by, String text) {
    //public static void setText(WebElement by, String text)
        System.out.println("Set text: " + text + " on element " + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        //wait.until(ExpectedConditions.visibilityOf(by)); //NẾU DÙNG ĐỐI TƯỢNG WebElement
        //=> Từ WebElement => By dễ hơn => wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
        driver.findElement(by).sendKeys(text);

    }

    public static void clearText(By by) {
        System.out.println("Clear text on element: " + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).clear();
    }

    public static String getTextElement(By by){
        System.out.println("Get text of element: " + by);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        String text = driver.findElement(by).getText();
        System.out.println("==> Text: " + text);
        return  driver.findElement(by).getText();
    }

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementVisible(By by, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    }
