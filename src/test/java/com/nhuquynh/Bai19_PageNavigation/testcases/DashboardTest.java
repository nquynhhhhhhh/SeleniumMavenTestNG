package com.nhuquynh.Bai19_PageNavigation.testcases;

import com.nhuquynh.Bai17_PageObjectModel.pages.DashboardPage;
import com.nhuquynh.Bai19_PageNavigation.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
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
