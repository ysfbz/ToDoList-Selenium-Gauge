import com.thoughtworks.gauge.*;
import helper.ElementHelper;
import helper.StoreHelper;
import model.ElementInfo;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BaseTest {
    @BeforeSuite
    public void hazirlik (){
        System.out.println("Senaryo Basliyor");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 40);
        driver.manage().window().maximize();

    }

    static WebDriver driver;
    static Actions action;
    static WebDriverWait wait;


    public static void getUrl(){
        driver.get("https://todomvc.com/examples/vue/");
    }

    public WebElement findElement(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    public List<WebElement> findElements(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    public void clickElement(String by){
        findElement(by).click();
    }

    public void openNewTab(){
        for(String childWindow: driver.getWindowHandles()){
            driver.switchTo().window(childWindow);
        }
    }

    public void hooverListElement(int index, String by){
        action.moveToElement(findElements(by).get(index)).build().perform();
    }

    public void waitUntilExist(String by){
        wait.until(ExpectedConditions.elementToBeClickable(findElement(by)));
    }

    public void sendKeyElements(String by, String text){
        findElement(by).sendKeys(text);
    }

    public void sendEnter(String by){
        findElement(by).sendKeys(Keys.RETURN);
    }

    public void nonExist(String by){
        assertTrue(findElements(by).isEmpty());
    }

    public void checkListElement(int index, String by){
        try {
            findElements(by).get(index);
        } catch (Exception e) {
            Assert.fail("Element bulunamadi.");
        }
    }

    public void clickListElement(int index, String by){
        findElements(by).get(index).click();
    }

    @AfterSuite
    public void bitir() {
        driver.quit();
        System.out.println("Senaryo Tamamlandi");
    }
}
