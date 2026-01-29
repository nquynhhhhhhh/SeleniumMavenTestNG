package com.nhuquynh.Bai18_PageFactory.pages;

import com.nhuquynh.Common.Locators;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {
    private WebDriver driver;

    public DashboardPage(WebDriver driver){
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
