import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by TDemianenko on 23.05.2017.
 */
public class MyFirstTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        final File file = new File(PropertyLoader.loadProperty("path.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test1() {
        driver.navigate().to("http://juliemr.github.io/protractor-demo/");
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://juliemr.github.io/protractor-demo/" );
        System.out.println(URL);
    }
    @Test
    public void test2() {
        WebElement field1 = driver.findElement(By.xpath("//input[@ng-model = 'first']"));
        field1.sendKeys("1");

        WebElement field2 = driver.findElement(By.xpath("//input[@ng-model = 'second']"));
        field2.sendKeys("1");


        Assert.assertEquals(field1.getAttribute("value"), "1" );
        System.out.println(field1.getAttribute("value"));

        Assert.assertEquals(field2.getAttribute("value"), "1" );
        System.out.println(field2.getAttribute("value"));
    }
    @Test
    public void test3() {

        Select dropdown = new Select(driver.findElement(By.xpath("//select [@ng-model = 'operator']")));
        dropdown.selectByValue("ADDITION");

        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "+");
        System.out.println(dropdown.getFirstSelectedOption().getText());

        }
    @Test
    public void test4() throws InterruptedException {
        WebElement button = driver.findElement(By.xpath("//button"));
        button.click();
        Thread.sleep(10000);

        WebElement result = driver.findElement(By.xpath("//h2[@class = 'ng-binding']"));
        
        Assert.assertEquals(result.getText(), "2" );
        System.out.println(result.getText());
    }
}