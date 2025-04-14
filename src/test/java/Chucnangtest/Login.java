package Chucnangtest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.sql.Driver;
import java.time.Duration;

public class Login extends Kethua {
    WebDriverWait wait;


    public void login(String username, String password) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).clear();
        driver.findElement(By.id("username")).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-userdropdown-name")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    // TC_CP_01 - Nhập đúng
    @Test
    public void TC01_ValidCredentials() {
        login("Pham Nhu Ngoc", "Ngoc123@");
        Assert.assertTrue(isDashboardDisplayed(), "Should navigate to dashboard");
    }

    // TC_CP_02 - Username có khoảng trắng đầu/cuối
    @Test
    public void TC02_UsernameWithSpaces() {
        login(" Pham Nhu Ngoc ", "Ngoc123@");
        Assert.assertTrue(getPageSource().contains("Invalid username and password"), "Expected failure for space in username");
    }

    // TC_CP_03 - Username chứa số
    @Test
    public void TC03_UsernameWithNumber() {
        login("Pham Nhu Ngoc 123", "Ngoc123@");
        Assert.assertTrue(getPageSource().contains("Invalid username and password"));
    }

    // TC_CP_04 - Username chứa ký tự đặc biệt
    @Test
    public void TC04_UsernameWithSpecialChar() {
        login("Pham Nhu Ngoc@", "Ngoc123@");
        Assert.assertTrue(getPageSource().contains("Invalid username and password"));
    }

    // TC_CP_05 - Username để trống
    @Test
    public void TC05_EmptyUsername() {
        login("", "Ngoc123@");
        Assert.assertTrue(getPageSource().contains("Please fill out this field") || getPageSource().contains("required"), "Expected validation error");
    }

    // TC_CP_06 - Password có khoảng trắng
    @Test
    public void TC06_PasswordWithSpaces() {
        login("Pham Nhu Ngoc", "Ngoc123@ ");
        Assert.assertTrue(getPageSource().contains("Invalid username and password"));
    }

    // TC_CP_07 - Password viết hoa toàn bộ
    @Test
    public void TC07_PasswordAllUppercase() {
        login("Pham Nhu Ngoc", "NGOC123@");
        Assert.assertTrue(getPageSource().contains("Invalid username and password"));
    }

    // TC_CP_08 - Password rỗng
    @Test
    public void TC08_EmptyPassword() {
        login("Pham Nhu Ngoc", "");
        Assert.assertTrue(getPageSource().contains("Please fill out this field") || getPageSource().contains("required"));
    }

    // TC_CP_09 - Cả username và password rỗng
    @Test
    public void TC09_EmptyUsernameAndPassword() {
        login("", "");
        Assert.assertTrue(getPageSource().contains("Please fill out this field") || getPageSource().contains("required"));
    }
}

      /*  driver.navigate().to("https://localhost:7029/Identity/Account/Login?ReturnUrl=%2F");
        Thread.sleep(2000);
        driver.findElement(By.id("typeEmailX")).sendKeys("phamthinhungoc1604@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("typePasswordX")).sendKeys("Ngoc1604@");
        Thread.sleep(2000);
        driver.findElement(By.id("login-submit")).click();     Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/nav/div/div[2]/div/ul/li/a/span")).click(); Thread.sleep(2000);
        driver.findElement(By.id("logout")).click();
        Thread.sleep(5000);*/




