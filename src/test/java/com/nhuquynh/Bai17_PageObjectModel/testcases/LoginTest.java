package com.nhuquynh.Bai17_PageObjectModel.testcases;

import com.nhuquynh.Bai17_PageObjectModel.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    //khai báo đối tượng (đang null)
    LoginPage loginPage; //khai báo biến toàn cục loginPage (kiểu dữ liệu LoginPage, kiểu dữ liệu đối tượng class) để cung cấp những hàm sử dụng từ class LoginPage

    @Test
    public void loginSuccess(){
        //khởi tạo đối tượng (có giá trị) 
        loginPage = new LoginPage(driver); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cũng sẽ mang giá trị
        loginPage.loginCRM("admin@example.com","123456");
        loginPage.verifyLoginSuccess();
    }

    @Test
    public void loginFailWithEmailInvalid(){
        loginPage = new LoginPage(driver); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("admin123@example.com","123456");
        loginPage.verifyLoginFail();
    }

    @Test
    public void loginFailWithPassInvalid(){
        loginPage = new LoginPage(driver); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("admin@example.com","111");
        loginPage.verifyLoginFail();
    }

    @Test
    public void loginFailWithEmailNull(){
        loginPage = new LoginPage(driver); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("","123456");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    @Test
    public void loginFailWithPassNull(){
        loginPage = new LoginPage(driver); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("admin@example.com","");
        loginPage.verifyLoginFail("The Password field is required.");
    }

    @Test
    public void loginFailWithNullFields(){
        loginPage = new LoginPage(driver); //khởi tạo browser ở BaseTest trước rồi nên driver đã có giá trị => chỗ này cng sẽ mang giá trị
        loginPage.loginCRM("","");
        loginPage.verifyLoginFailWithNullFields_ArrayList(2);
    }
}
