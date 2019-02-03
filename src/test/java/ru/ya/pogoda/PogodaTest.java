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

        //�������� ���� ������
        WebElement searchField = driver.findElement(By.xpath("//*[@id=\'text\']"));
        searchField.clear();

        //��������� � ���� ������ ��������� ������ "������"
        searchField.sendKeys("������");

        //������ ������� "ENTER"
        searchField.sendKeys(Keys.ENTER);

        //�������� ������ "������ �..."
        WebElement pogodaButton = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div/div[1]/ul/li[1]/div/div[1]/h2/a/div[2]"));
        pogodaButton.click();

        //��������� ��� ��������� �������� "������ � �����-����������"
        WebElement profileUser = driver.findElement(By.partialLinkText("������ � "));
        String versionNumber = profileUser.getText();
        Assert.assertEquals("������ � �����-����������", versionNumber);

    }

    @AfterClass
    public static void tearDown() {

        driver.quit();
    }

}
