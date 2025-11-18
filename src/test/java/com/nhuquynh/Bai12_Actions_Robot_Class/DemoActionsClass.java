package com.nhuquynh.Bai12_Actions_Robot_Class;

import com.nhuquynh.Common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
//PHẢI ktra lại import bởi giả sử Action class trùng với các thư viện khác thì phải check xem có đúng thư viện selenium kh

public class DemoActionsClass extends BaseTest {
    @Test
    public void testSendKeys() throws InterruptedException {
        driver.get("https://cms.anhtester.com/");

        driver.findElement(By.xpath("//i[@class='la la-close fs-20']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[normalize-space()='Ok. I Understood']")).click();

        //Input search
        WebElement element = driver.findElement(By.xpath("//input[@id='search']"));

        //Tạo đối tượng của Actions class và để driver vào
        Actions action = new Actions(driver);

        //Dùng action để gọi hàm sendKeys điền dữ liệu. Không dùng sendKeys của WebElement
        action.sendKeys(element, "Giày nữ").perform();
        action.sendKeys(Keys.ENTER).perform();

        Thread.sleep(2000);
    }

    @Test
    public void testPressEnterKey() throws InterruptedException {
        driver.get("https://cms.anhtester.com/");

        driver.findElement(By.xpath("//i[@class='la la-close fs-20']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[normalize-space()='Ok. I Understood']")).click();

        //Input search
        WebElement element = driver.findElement(By.xpath("//input[@id='search']"));

        //Tạo đối tượng của Actions class và để driver vào
        Actions action = new Actions(driver);

        //Dùng action để gọi hàm sendKeys điền dữ liệu. Không dùng sendKeys của WebElement
        action.sendKeys(element, "Giày nữ").perform();

        Thread.sleep(2000);

        action.sendKeys(Keys.ENTER).perform();

        Thread.sleep(2000);
    }

    @Test
    public void testClick() throws InterruptedException {
        driver.get("https://cms.anhtester.com/");

        driver.findElement(By.xpath("//i[@class='la la-close fs-20']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[normalize-space()='Ok. I Understood']")).click();

        //Input search
        WebElement element = driver.findElement(By.xpath("//input[@id='search']"));

        //Tạo đối tượng của Actions class và để driver vào
        Actions action = new Actions(driver);

        //Dùng action để gọi hàm sendKeys điền dữ liệu. Không dùng sendKeys của WebElement
        action.sendKeys(element, "Giày nữ").perform();
        Thread.sleep(2000);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);

        WebElement giaynu = driver.findElement(By.xpath("(//a[contains(text(),'Giày nữ')])[1]"));
        Assert.assertEquals(giaynu.getText(),"Giày nữ 99", "Title not match");
        action.click(giaynu).perform();


        Thread.sleep(2000);
    }

    @Test
    public void doubleClick() throws InterruptedException {
        driver.get("https://anhtester.com/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("(//h2[@class='section__title'])[1]"));
        //element là hình vuông thì click ngay tâm điểm của element
        Actions action = new Actions(driver);

        action.doubleClick(element).perform();
        Thread.sleep(2000);
    }

    @Test
    public void contextClick() throws InterruptedException {
        driver.get("https://anhtester.com/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("(//h2[@class='section__title'])[1]"));

        Actions action = new Actions(driver);

        action.contextClick(element).perform();
        Thread.sleep(2000);
    }

    @Test
    public void moveToElement() throws InterruptedException {
        driver.get("https://anhtester.com/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//h2[contains(text(),'Kiến thức Automation Testing')]"));

        Actions action = new Actions(driver);

        //Move to element (di chuyển tới title Kiến thức Automation Testing)
        action.moveToElement(element).perform();

        Thread.sleep(2000);
    }

    @Test
    public void demoDragAndDropWithActionClass() throws InterruptedException {
        driver.get("https://www.lambdatest.com/selenium-playground/drag-and-drop-demo");
        Thread.sleep(1000);

        // Bắt element cần kéo
        WebElement elementFrom1 = driver.findElement(By.xpath("//span[normalize-space()='Draggable 1']"));
        WebElement elementFrom2 = driver.findElement(By.xpath("//span[normalize-space()='Draggable 2']"));
        // Bắt element cần thả đến
        WebElement elementTo = driver.findElement(By.xpath("//div[@id='mydropzone']"));

        Thread.sleep(1000);
        Actions action = new Actions(driver);
        // Kéo và thả
        action.dragAndDrop(elementFrom1, elementTo).perform();
        Thread.sleep(1000);
        action.dragAndDrop(elementFrom2, elementTo).perform();

        Thread.sleep(2000);
    }

    @Test
    public void inputTextUppercase() throws InterruptedException {
        driver.get("https://www.google.com/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//textarea[@name='q']"));

        Actions action = new Actions(driver);

        // Đè giữ phím SHIFT và nhập text -> Chữ in hoa
        action.keyDown(element, Keys.SHIFT).sendKeys("anh tester").keyUp(Keys.SHIFT).build().perform();
        //build dùng để liên kết 2 hàm trở lên - chuỗi liên hoàn (sendKey vs perform)
        action.sendKeys("Automation Testing").perform();

        Thread.sleep(2000);
    }

    @Test
    public void scrollPageDownAndUp() throws InterruptedException {
        driver.get("https://anhtester.com/");
        Actions action = new Actions(driver);

        Thread.sleep(1000);
        // Scroll down
        action.keyDown(Keys.END).perform();
        action.keyUp(Keys.END).perform();
        Thread.sleep(2000);
        // Scroll up
        action.keyDown(Keys.HOME).perform();
        action.keyUp(Keys.HOME).perform();
        Thread.sleep(2000);
    }

    @Test
    public void copyAndPaste() throws InterruptedException {
        driver.get("https://anhtester.com/blogs");
        Thread.sleep(2000);

        WebElement inputCourseElement = driver.findElement(By.xpath("//input[@placeholder='Tìm kiếm khoá học...']"));
        WebElement inputBlogElement = driver.findElement(By.xpath("//input[contains(@placeholder, 'Tìm kiếm bài viết')]"));

        Actions action = new Actions(driver);

        Thread.sleep(1000);
        // Nhập text vào ô search course
        inputCourseElement.sendKeys("Selenium");
        Thread.sleep(1000);
        //CÁC KÝ TỰ TỪ A => Z THÌ PHẢI VIẾT sendKeys("a")
        //Ctrl + a để bôi đen
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(1000);
        //Ctrl + c để copy
        action.keyDown(Keys.CONTROL).sendKeys("x").keyUp(Keys.CONTROL).build().perform();
        Thread.sleep(1000);
        //click vào ô Blog search
        inputBlogElement.click();
        Thread.sleep(1000);
        //Ctrl + v để dán
        action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();

        Thread.sleep(2000);
    }

}
