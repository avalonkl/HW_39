package core;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Firefox {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);

        String driverPath = "";

        String url = "http://facebook.com/";
        String email_address = "avalonkl@gmail.com";
        String password = "0397DR44900";

        if (System.getProperty("os.name").toUpperCase().contains("MAC"))     driverPath = "./resources/webdrivers/mac/geckodriver.sh";
        else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")) driverPath = "./resources/webdrivers/pc/geckodriver.exe";
        else throw new IllegalArgumentException("Unknown OS");

        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 15);

        driver.get(url);

        String title = driver.getTitle();
        String copyright = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/div/div[3]/div/span"))).getText();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(email_address);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("u_0_5"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/div/div/div/div[2]/div[1]/div[1]/div/a/span"))).click();

        String friends = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/ul/li[3]/a"))).getText();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("userNavigationLabel"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[14]/a/span/span"))).click();

        driver.quit();

        char[] chars = friends.toCharArray();
        friends = "";
        for (int i = 0; i < chars.length; i++){
            if ((int) chars[i] > 47 && (int) chars[i] < 58){
                friends += chars[i];
            }
        }

        System.out.println("Browser is: Firefox");
        System.out.println("Title of the page: " + title);
        System.out.println("Copyright: " + copyright);
        System.out.println("You have " + friends + " friends");
    }
}
