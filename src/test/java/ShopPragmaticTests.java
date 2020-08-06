import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShopPragmaticTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("http://shop.pragmatic.bg");
    }

    @Test
    public void loginTest() {
        driver.findElement(By.cssSelector(".fa-user")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
        driver.findElement(By.cssSelector("div.well a.btn-primary")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Darina");
        driver.findElement(By.id("input-lastname")).sendKeys("Bahchevanova");
        String randomEmail = RandomStringUtils.randomAlphanumeric(7) + "@blabla.com";
        driver.findElement(By.id("input-email")).sendKeys(randomEmail);
        driver.findElement(By.id("input-telephone")).sendKeys("04568924");
        driver.findElement(By.id("input-password")).sendKeys("parola12345");
        driver.findElement(By.id("input-confirm")).sendKeys("parola12345");
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();

        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Modify your address book entries')]")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Darina");
        driver.findElement(By.id("input-lastname")).sendKeys("Bahchevanova");
        driver.findElement(By.id("input-address-1")).sendKeys("adres1");
        driver.findElement(By.id("input-city")).sendKeys("Sofia");
        driver.findElement(By.id("input-postcode")).sendKeys("1234");

        Select countryDropDown = new Select(driver.findElement(By.id("input-country")));
        countryDropDown.selectByValue("33");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("input-zone"),"Yambol"));

        Select regionDropDown = new Select(driver.findElement(By.id("input-zone")));
        regionDropDown.selectByValue("498");

        driver.findElement(By.cssSelector(".btn-primary")).click();

        WebElement message = driver.findElement(By.cssSelector(".alert-dismissible"));
        Assert.assertEquals(message.getText(), "Your address has been successfully added");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
