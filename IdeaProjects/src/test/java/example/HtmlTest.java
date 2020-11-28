package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HtmlTest {

    private WebDriver driver;
    @BeforeClass
    public void beforeM() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://downdetector.com/");
    }
    @Test
    public void jsImported() {
        Assert.assertEquals(driver.findElements(By.cssSelector("script:not([src])")).size(), 0);
    }
    @Test
    public void jsSingle() {
        Assert.assertEquals(driver.findElements(By.cssSelector("script[src]")).size(), 1);
    }
    @Test
    public void cssImported() {
        try{
            Assert.assertEquals(driver.findElements(By.tagName("style")).size(), 0);
            Assert.assertEquals(driver.findElements(By.cssSelector("*[style]")).size(), 0);
        } catch (AssertionError e) {
            System.out.println("qqqqqqq");
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void cssSingle() {
        Assert.assertEquals(driver.findElements(By.cssSelector("link[rel='stylesheet']")).size(), 1);
    }
    @Test
    public void H1sinlge() {
        Assert.assertEquals(driver.findElements(By.tagName("h1")).size(), 1);
    }
    @Test
    public void uniqueIds() {
        List<WebElement> elements = driver.findElements(By.cssSelector("*[id]"));
        List<String> ids = new ArrayList<>();
        for (WebElement el: elements) {
            ids.add(el.getAttribute("id"));
        }
        Set<String> uniqueids = new HashSet<>(ids);
        Assert.assertEquals(ids.size(), uniqueids.size());
    }
    @AfterClass
    public void afterM() {
        driver.quit();
    }
}

