package ru.ya.pogoda;

import org.openqa.selenium.*;

import org.testng.Assert;
//import org.junit.BeforeClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

//import org.junit.Test;
import org.testng.annotations.Test;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class PogodaTest {
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://yandex.ru");
    }

    @Test
    public void pogodaPage() {

        //Очистить поле поиска
        WebElement searchField = driver.findElement(By.xpath("//*[@id=\'text\']"));
        searchField.clear();

        //Заполнить в поле поиска поисковой запрос "погода"
        searchField.sendKeys("погода");

        //Нажать клавишу "ENTER"
        searchField.sendKeys(Keys.ENTER);

        //Кликнуть ссылку "Погода в..."
        WebElement pogodaButton = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[1]/ul/li[1]/div/div[1]/h2/a/div[2]"));
        pogodaButton.click();

        //Убедиться что открылась страница "Погода в Санкт-Петербурге"
        WebElement profileUser = driver.findElement(By.partialLinkText("Погода в "));
        String versionNumber = profileUser.getText();
        Assert.assertEquals("Погода в Санкт-Петербурге", versionNumber);

    }

    @AfterClass
    public static void tearDown() {

        driver.quit();
    }

}
