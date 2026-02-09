package com.nhuquynh.keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

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

    private static int TIMEOUT = 10;
    private static double STEP_TIME = 0.5;
    private static int PAGE_LOAD_TIMEOUT = 20;

    //Wait for Element

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            logConsole("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            logConsole("Timeout waiting for the element ready to click. " + by.toString());
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    //Chờ đợi trang load xong mới thao tác
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition< Boolean > jsLoad = new ExpectedCondition < Boolean > () {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            //System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }


    public static void sleep(double second) {
        try {
            Thread.sleep((long)(1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    public static Boolean checkElementExist(By by) {
        List<WebElement> listElement = getWebElements(by);

        if (listElement.size() > 0) {
            System.out.println("checkElementExist: " + true + " --- " + by);
            return true;
        } else {
            System.out.println("checkElementExist: " + false + " --- " + by);
            return false;
        }
    }

    // Hàm kiểm tra sự tồn tại của phần tử với lặp lại nhiều lần
    public static boolean checkElementExist(By by, int maxRetries, int waitTimeMillis) {
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                WebElement element = getWebElement(by);
                if (element != null) {
                    System.out.println("Tìm thấy phần tử ở lần thử thứ " + (retryCount + 1));
                    return true; // Phần tử được tìm thấy
                }
            } catch (NoSuchElementException e) {
                System.out.println("Không tìm thấy phần tử. Thử lại lần " + (retryCount + 1));
                retryCount++;
                try {
                    Thread.sleep(waitTimeMillis); // Chờ trước khi thử lại
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

        // Trả về false nếu không tìm thấy phần tử sau maxRetries lần
        logConsole("Không tìm thấy phần tử sau " + maxRetries + " lần thử.");
        return false;
    }

    public static boolean isElementDisplayed(By by) {
        try {
            WebElement element = driver.findElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void openURL(String url) {
        driver.get(url);
        sleep(STEP_TIME);
        logConsole("Open URL:  " + url);
    }

    public static void clickElement(By by) {
        waitForElementClickable(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        logConsole("Click on element " + by);
    }

    public static void clickElement(By by, int timeout) {
        waitForElementClickable(by, timeout);
        sleep(STEP_TIME);
        getWebElement(by).click();
        logConsole("Click on element " + by);
    }

    public static void clearText(By by) {
        sleep(STEP_TIME);
        waitForElementVisible(by);
        getWebElement(by).clear();
        logConsole("Clear text on element: " + by);
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        logConsole("Set text " + value + " on element " + by);
    }

    public static String getElementText(By by) {
        waitForElementVisible(by);
        logConsole("Get text of element " + by);
        String text = getWebElement(by).getText();
        logConsole("==> TEXT: " + text);
        return text; //Trả về một giá trị kiểu String
    }

    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        System.out.println("Get attribute of element " + by);
        String value = getWebElement(by).getAttribute(attributeName);
        System.out.println("==> Attribute value: " + value);
        return value;
    }

    public static String getElementCssValue(By by, String cssPropertyName) {
        waitForElementVisible(by);
        System.out.println("Get CSS value " + cssPropertyName + " of element " + by);
        String value = getWebElement(by).getCssValue(cssPropertyName);
        System.out.println("==> CSS value: " + value);
        return value;
    }

    }
