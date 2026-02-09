package com.nhuquynh.Bai18_PageFactory.pages;

import com.nhuquynh.Common.Locators;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class DashboardPage {
    private WebDriver driver;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private  By totalInvoicesAwaitingPayment = By.xpath(Locators.totalInvoicesAwaitingPayment);
    private  By totalConvertedLeads = By.xpath(Locators.totalConvertedLeads);

    @FindBys({@FindBy(xpath="//ul[@id='side-menu']/li[contains(@class,'menu-item')]")})
    //hoặc
    //@FindBy(xpath="//ul[@id='side-menu']/li[contains(@class,'menu-item')]")
    private List<WebElement> listElement;

    public void getListMenu(){
        System.out.println("Menu total: " + listElement.size());
        for (int i = 0; i < listElement.size(); i++){
            System.out.println(listElement.get(i).getText());
        }
    }


    public void verifyInvoicesAwaitingPayment(String total){
        //lúc nào cũng phải check tồn tại trc
        Assert.assertTrue(driver.findElement(totalInvoicesAwaitingPayment).isDisplayed(),"The Invoices Awaiting Payment total label not match");
        Assert.assertEquals(WebUI.getElementText(totalInvoicesAwaitingPayment), total, "The Invoices Awaiting Payment total not match");
    }


}
