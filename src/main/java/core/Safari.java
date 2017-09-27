package core;

import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Safari {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        String url = "http://facebook.com/";
        String email_address = "avalonkl@gmail.com";
        String password = "0397DR44900";

        if (!System.getProperty("os.name").contains("Mac")) {throw new IllegalArgumentException("Safari is available only on Mac");}

        driver = new SafariDriver();
        //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 15);

        driver.get(url);


        //Thread.sleep(1000); // Pause in milleseconds (1000 – 1 sec)

        String title = driver.getTitle();
        String copyright = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/div/div[3]/div/span"))).getText();

        Thread.sleep(1000);

        driver.findElement(By.id("email")).sendKeys(email_address);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("u_0_5")).click();

        //Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"u_0_a\"]/div[1]/div[1]/div/a/span"))).click();

        //Thread.sleep(5000);
        String friends = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"u_0_13\"]/li[3]/a/span[1]"))).getText();
        System.out.println("You have " + friends + " friends");

        //Thread.sleep(5000);

        WebElement settings = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNavigationLabel")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", settings);

        //Thread.sleep(5000);

        WebElement logout = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"js_in\"]/div/div/ul/li[1]/a/span/span")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logout);

        //Thread.sleep(1000);
        driver.quit();

        System.out.println("Browser is: Safari");
        System.out.println("Title of the page: " + title);
        System.out.println("Copyright: " + copyright);
        System.out.println("You have " + friends + " friends");
    }
}
