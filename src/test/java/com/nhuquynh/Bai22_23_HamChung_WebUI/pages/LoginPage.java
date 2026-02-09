package com.nhuquynh.Bai22_23_HamChung_WebUI.pages;

import com.nhuquynh.Common.Locators;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {
    //Khai báo driver cục bộ trong chính class này
    //driver cục bộ là nhận giá trị driver từ bên ngoài vào và sử dụng trong nội bộ class
    private WebDriver driver;

    //Khai báo hàm xây dựng, để truyền driver từ bên ngoài vào
    public LoginPage(WebDriver driver) {
        this.driver = driver; //=> driver ở trong class này (dòng 11) mới nhận driver từ bên ngoài
        new WebUI(driver); //khởi tạo giá trị driver cho class WebUI
    }

    //Khai báo các element dạng đối tượng By (phương thức tìm kiếm)
    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[contains(@class,'alert-danger')]");
    private By errorMessage1 = By.xpath("(//div[contains(@class,'alert-danger')])[1]");
    private By errorMessage2 = By.xpath("(//div[contains(@class,'alert-danger')])[2]");


    //Xây dựng hàm xử lý => hàm public, phải gọi sang chỗ khác dùng được, đảm nhiệm xử lý chức năng nội bộ của trang Login

    //C1: Đã có WebUI nên không cần khởi tạo hàm trung gian
    //chạy hàm automation login (dùng chung ở toàn bộ TC)
    public DashboardPage loginCRM() {
        WebUI.openURL("https://crm.anhtester.com/admin/authentication");
        WebUI.setText(inputEmail, "admin@example.com");
        WebUI.setText(inputPassword, "123456");
        WebUI.clickElement(buttonLogin);
        verifyLoginSuccess(); //khi login thì check luôn có succes kh

        return new DashboardPage(driver);
    }

    public void loginCRM(String email, String password) { //chạy automation login, verify là 2 hàm trên
        WebUI.openURL("https://crm.anhtester.com/admin/authentication");
        WebUI.waitForPageLoaded();
        WebUI.clearText(inputEmail);
        WebUI.clearText(inputPassword);
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);
    }

    //hàm verify (dùng ở từng TC riêng)
    public void verifyLoginSuccess() {
        Assert.assertEquals(driver.findElement(By.xpath(Locators.menuDashboard)).getText(), "Dashboard", "FAIL. Vẫn đang ở trang Login");
        Assert.assertFalse(driver.getCurrentUrl().contains("authentication"), "FAIL. Vẫn đang ở trang Login");
    }

    public void verifyLoginFail() { //đã truyền text r nên gọi hàm kh cần truyền text nữa nhưng chỉ sử dụng được 1 TH
        Assert.assertTrue(driver.getCurrentUrl().contains("authentication"), "FAIL. Không còn ở trang Login");
        Assert.assertTrue(driver.findElement(errorMessage).isDisplayed(), "Error message NOT displays");
        Assert.assertEquals(driver.findElement(errorMessage).getText(), "Invalid email or password", "Content of error massage NOT match.");
    }
    //giống cái trên => tính đa hình
    public void verifyLoginFail(String message) { //khi gọi hàm phải truyền text nhưng sử dụng được nhiều TH
        Assert.assertTrue(driver.getCurrentUrl().contains("authentication"), "FAIL. Không còn ở trang Login");
        Assert.assertTrue(driver.findElement(errorMessage).isDisplayed(), "Error message NOT displays");
        Assert.assertEquals(driver.findElement(errorMessage).getText(), message, "Content of error massage NOT match.");
    }

    public void verifyLoginFailWithNullFields() {
        Assert.assertTrue(driver.getCurrentUrl().contains("authentication"), "FAIL. Không còn ở trang Login");
        Assert.assertTrue(WebUI.isElementDisplayed(errorMessage1), "Error message 1 NOT displays");
        Assert.assertTrue(WebUI.isElementDisplayed(errorMessage2), "Error message 2 NOT displays");

        Assert.assertEquals(WebUI.getElementText(errorMessage1), "The Password field is required.", "Content of error massage 1 NOT match.");
        Assert.assertEquals(WebUI.getElementText(errorMessage2), "The Email Address field is required.", "Content of error massage 2 NOT match.");
    }


    //NẾU CÓ NHIỀU error message quá => dùng for
    public void verifyLoginFailWithNullFields_ArrayList(int totalNullFields) {
        List<String> messageString = new ArrayList<>();
        messageString.add("The Password field is required.");
        messageString.add("The Email Address field is required.");

        boolean check = false;

        //for ngoài là số lượng error message
        for (int i = 1; i <= totalNullFields; i++) { //Biến i được dùng để xây dựng chuỗi XPath (dưới)=> mà XPath, chỉ mục (index) của các phần tử bắt đầu từ 1
            Assert.assertTrue(driver.findElement(By.xpath("(//div[contains(@class,'alert-danger')])[" + i + "]")).isDisplayed(), "Error message " + i + " NOT displays");
            //for trong là số lượng message mình compare
            for (int j = 0; j < messageString.size(); j ++) {
                if(WebUI.getElementText(By.xpath("(//div[contains(@class,'alert-danger')])[" + i + "]")).equals(messageString.get(j))){
                    check = true;
                    break;
                }
            }
            Assert.assertTrue(check,"Content of error massage " + 1 + " NOT match.");
        }
    }
    //=> Trả về log compare error mesage thứ 2 2 lần là đúng
    //khi i=1 => compare với error message 1 => OK => break
    //khi i=2 => compare với error message 1 => NG => compare với error message 2 => OK




}

//CÁCH 2: khai báo hàm trung gian (TRONG TH KHÔNG CÓ HÀM CHUNG WEBUI)
//    private void setEmail(String email) {
//        driver.findElement(inputEmail).sendKeys(email);
//    }
//    private void setPassword(String password) {
//        driver.findElement(inputPassword).sendKeys(password);
//    }
//    private void clickLoginButton() {
//        driver.findElement(buttonLogin).click();
//    }

//    public void loginCRM(String email, String password) { //khai báo hàm trung gian
//        driver.get("https://crm.anhtester.com/admin/authentication"); //Gọi từ class ConfigData dạng biến static
//        setEmail(email);
//        setPassword(password);
//        clickLoginButton();
//    public void verifyLoginSuccess()....


//CÁCH 3: Bởi vì WebUI đặt tên hàm chung như setText nên muốn cụ thể hơn là setEmail thì có thể gộp C1 VÀ C2
//    public void setEmail(String email) {
//        WebUI.setText(inputEmail, email);
//    }
//    public void setPassword(String password) {
//        WebUI.setText(inputPassword, password);
//    }
//    public void clickLoginButton() {
//        WebUI.clickElement(buttonLogin);
//    }
//=> nếu 3 hàm này cung cấp ra bên ngoài sử dụng thì đổi sang public
//=> các biến private muốn đem ra ngoài sử dụng thì bỏ vào hàm public => tính đóng gói trong java oop

//    public void loginCRM(String email, String password) { //khai báo hàm trung gian
//        driver.get("https://crm.anhtester.com/admin/authentication"); //Gọi từ class ConfigData dạng biến static
//        setEmail(email);
//        setPassword(password);
//        clickLoginButton();
//    public void verifyLoginSuccess()....
