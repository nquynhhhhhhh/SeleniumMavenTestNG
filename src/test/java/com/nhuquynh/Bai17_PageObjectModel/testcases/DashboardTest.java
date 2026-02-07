package com.nhuquynh.Bai17_PageObjectModel.testcases;

import com.nhuquynh.Bai17_PageObjectModel.pages.DashboardPage;
import com.nhuquynh.Bai17_PageObjectModel.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void checkDashboardTotal(){
        loginPage = new LoginPage(driver);
        loginPage.loginCRM(); //chỉ login
//=====================================================

        //ở đây có nghĩa là cho dù loginCRM có thành công hay không thì cũng khởi tạo dashboardPage
        dashboardPage = new DashboardPage(driver);
        dashboardPage.verifyInvoicesAwaitingPayment("1 / 3");

    }
}
