package com.nhuquynh.Bai22_23_HamChung_WebUI.testcases;

import com.nhuquynh.Bai22_23_HamChung_WebUI.pages.DashboardPage;
import com.nhuquynh.Bai22_23_HamChung_WebUI.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest{
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void checkDashboardTotal(){
        loginPage = new LoginPage(driver);
//        loginPage.loginCRM(); //chỉ login
//        dashboardPage = new DashboardPage(driver);

        dashboardPage = loginPage.loginCRM();
        dashboardPage.verifyInvoicesAwaitingPayment("1 / 3");

    }
}
