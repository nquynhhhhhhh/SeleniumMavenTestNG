package com.nhuquynh.Bai13_Alert_Popup_IFrame;

import com.nhuquynh.Common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import java.util.Set;

public class HandlePopupWindow extends BaseTest {
    @Test
    public void testOpenNewTab() {
        driver.get("https://anhtester.com");

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://google.com");
    }

    @Test
    public void testOpenNewWindow() {
        driver.get("https://anhtester.com");

        driver.switchTo().newWindow(WindowType.WINDOW);

        driver.get("https://google.com");
    }

    @Test
    public void demoHandlePopupTypeTab() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//button[@id='tabButton']")).click();

        WebElement element = driver.findElement(By.xpath("//button[@id='windowButton']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        driver.findElement(By.xpath("//button[@id='windowButton']")).click();

        Thread.sleep(1000);
        // Lưu lại lớp window đầu tiên - mã ID hơi dài, in ra sẽ thấy :)
        String MainWindow = driver.getWindowHandle();
        System.out.println("Main Window: " + MainWindow);

        // Lấy tất cả các mã định danh Tab Window.
        Set<String> windows = driver.getWindowHandles();

        //Set là một Collection để lưu các phần tử giá trị KHÔNG trùng lặp.
        //Cách duyệt từng phần tử không trùng lặp trong Collection (Set) - Java Basic
//        for (String tabWindow: windows) {
//            System.out.println(tabWindow);
//            if (!MainWindow.equals(tabWindow)) { //dấu ! có nghĩa ngược lại, ở đây là "nếu như KHÔNG BẰNG"
//                //So sánh nếu thằng nào khác thằng Chính (đầu tiên) thì chuyển hướng qua nó mới thao tác được
//                //Chuyển hướng driver đến Tab mới (Tab con)
//                driver.switchTo().window(tabWindow);
//
//                Thread.sleep(1000);
//                System.out.println("Đã chuyển đến Tab Window mới");
//
//                //Một số hàm hỗ trợ
//                System.out.println("Title Tab  Window mới" + driver.switchTo().window(tabWindow).getTitle());
//                System.out.println("URL Tab  Window mới" + driver.switchTo().window(tabWindow).getCurrentUrl());
//                Thread.sleep(1000);
//
//                //Sau khi chuyển hướng sang Tab mới thì getText cái header
//                System.out.println(driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText());
//
//                // Tắt cái Tab Window mới.
//                Thread.sleep(1000);
//                driver.close();
//            }
//        }


        String firstWindow = (String) windows.toArray()[0]; //Cửa sổ đầu
        String secondWindow = (String) windows.toArray()[1]; //Cửa sổ thứ hai
        driver.switchTo().window(secondWindow);

        System.out.println("Title Tab  Window mới: " + driver.getTitle());
        System.out.println("URL Tab  Window mới: " + driver.getCurrentUrl());
        Thread.sleep(1000);
        System.out.println("Header: " + driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText());
        Thread.sleep(1000);
        driver.close();

        // Chuyển hướng về lại tab chính ban đầu (Main Window)
        driver.switchTo().window(MainWindow);
        System.out.println("Đã chuyển về lớp Window chính: " + driver.getCurrentUrl());

        Thread.sleep(1000);
    }


}
