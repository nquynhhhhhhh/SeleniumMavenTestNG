package com.nhuquynh.Bai20_Practise_POM_CRM.testcases;

import com.nhuquynh.Bai20_Practise_POM_CRM.pages.CustomerPage;
import com.nhuquynh.Bai20_Practise_POM_CRM.pages.DashboardPage;
import com.nhuquynh.Bai20_Practise_POM_CRM.pages.LoginPage;
import com.nhuquynh.Common.BaseTest;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;


    @Test
    public void testAddNewCustomer(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomer();

        String customerName = "Cty YHL";
        customerPage.verifyNavigateToCustomerPage();
        customerPage.clickButtonAddNewCustomer();
        customerPage.submitDataForNewCustomer(customerName);
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(customerName);

    }


}
