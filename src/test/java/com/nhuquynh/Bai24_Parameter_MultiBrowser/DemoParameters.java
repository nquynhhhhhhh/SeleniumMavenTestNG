package com.nhuquynh.Bai24_Parameter_MultiBrowser;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoParameters {
    @Test(priority = 1)
    @Parameters({"val1","val2"})
    public void Sum(int v1, int v2){
        int finalSum = v1 + v2;
        System.out.println("Kết quả là: " + finalSum);
    }

    @Test(priority = 2)
    @Parameters({"val1","val2","number3"})
    public void sum2(int v1, int v2, int v3){
        int finalSum = v1 + v2 + v3;
        System.out.println("Kết quả là: " + finalSum);
    }

    @Test(priority = 3)
    @Parameters({"email","password","roleNumber"})
    public void login(@Optional("admin@example.com") String email, @Optional("123456") String password, @Optional("2") int roleNumber){
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Role Number: " + roleNumber);
    }

}
