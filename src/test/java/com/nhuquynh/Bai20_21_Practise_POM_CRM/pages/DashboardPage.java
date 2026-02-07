package com.nhuquynh.Bai20_21_Practise_POM_CRM.pages;

import com.nhuquynh.Common.Locators;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage extends BasePage{
    private WebDriver driver;

    public DashboardPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    private  By totalInvoicesAwaitingPayment = By.xpath(Locators.totalInvoicesAwaitingPayment);
    private  By totalConvertedLeads = By.xpath(Locators.totalConvertedLeads);


    public void verifyInvoicesAwaitingPayment(String total){
        //lúc nào cũng phải check tồn tại trc
        Assert.assertTrue(driver.findElement(totalInvoicesAwaitingPayment).isDisplayed(),"The Invoices Awaiting Payment total label not match");
        Assert.assertEquals(WebUI.getTextElement(totalInvoicesAwaitingPayment), total, "The Invoices Awaiting Payment total not match");
    }


}
