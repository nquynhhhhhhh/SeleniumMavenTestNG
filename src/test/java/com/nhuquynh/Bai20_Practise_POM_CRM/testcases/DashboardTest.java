package com.nhuquynh.Bai20_Practise_POM_CRM.testcases;

import com.nhuquynh.Bai20_Practise_POM_CRM.pages.DashboardPage;
import com.nhuquynh.Bai20_Practise_POM_CRM.pages.LoginPage;
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
