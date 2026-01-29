package com.nhuquynh.Bai18_PageFactory.testcases;

import com.nhuquynh.Bai18_PageFactory.pages.DashboardPage;
import com.nhuquynh.Bai18_PageFactory.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void checkDashboardTotal(){
        loginPage = new LoginPage(driver);
        loginPage.loginCRM("https://crm.anhtester.com/admin/authentication","123456"); //chỉ login

        dashboardPage = new DashboardPage(driver);
        dashboardPage.verifyInvoicesAwaitingPayment("1 / 3");

    }
}
