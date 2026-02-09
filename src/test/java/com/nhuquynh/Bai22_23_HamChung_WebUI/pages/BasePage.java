package com.nhuquynh.Bai22_23_HamChung_WebUI.pages;

import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        new WebUI(driver);
    }

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuTasks = By.xpath("//span[normalize-space()='Tasks']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    private By menuSales = By.xpath("//span[@class='menu-text' and normalize-space()='Sales']");
    private By menuProposals = By.xpath("//span[normalize-space()='Proposals']");
    private By iconProfile = By.xpath("//li[@class='icon header-user-profile']");

    public CustomerPage clickMenuCustomer(){
        WebUI.waitForElementVisible(menuCustomers);
        WebUI.clickElement(menuCustomers);

        return new CustomerPage(driver);
    }

}
