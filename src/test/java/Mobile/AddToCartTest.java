package Mobile;

import DataProviders.TestNGDataProvider;
import Utilities.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class AddToCartTest extends Base {

    public WebDriver driver;
    public Actions actions;
    public WebDriverWait wait;
    public Robot robot;
    public MobilePageObjects.AddToCart pom;
    public JavascriptExecutor js;

    @BeforeTest
    public void setup() throws IOException, AWTException {
        driver = initialiseDriver();
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        wait = get_Explicit_Wait_Object();
        robot = new Robot();

        //Intilialise Page Objects
        pom = new MobilePageObjects.AddToCart(driver);

    }

    @Test(priority = 1)
    public void navigate_Flipkart_URL() {
        String url = getBaseURL();
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test(priority = 2)
    public void click_On_Mobile() throws InterruptedException {
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        pom.getMobile_link().click();
    }

    @Test(priority = 3, dataProvider = "add_to_Cart_data", dataProviderClass = TestNGDataProvider.class)
    public void select_required_phone_checkbox(String checkbox) {

        wait.until(ExpectedConditions.visibilityOfAllElements(pom.getCheckBoxList()));
        select_Value_From_LIST(pom.getCheckBoxList(), checkbox);
    }

    @Test(priority = 4, dataProvider = "add_to_Cart_data", dataProviderClass = TestNGDataProvider.class)
    public void choose_required_phone(String mobileName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(pom.getMobileList()));
        select_Value_From_LIST(pom.getMobileList(), mobileName);
    }

    @Test(priority = 5)
    public void add_to_cart() {
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String window1 = it.next();
        String window2 = it.next();

        driver.switchTo().window(window2);
        actions.moveToElement(pom.getADD_TO_CART_BUTTON()).click().build().perform();

    }

    @Test(priority = 6)
    public void place_order() {
        wait.until(ExpectedConditions.urlContains("viewcart"));

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(pom.getPLACE_ORDER_BUTTON())));
        actions.moveToElement(pom.getPLACE_ORDER_BUTTON()).click().build().perform();

    }

    @Test(priority = 7, dataProvider = "add_to_Cart_data", dataProviderClass = TestNGDataProvider.class)
    public void Enter_Phone_Number(String phone_number) {
        wait.until(ExpectedConditions.urlContains("checkout"));
        actions.sendKeys(pom.getENTER_PHONE_NUMBER(), phone_number).build().perform();


    }

    @Test(priority = 8)
    public void Click_On_Continue() {
        actions.click(pom.getCONTINUE_BUTTON()).build().perform();
    }

    @Test(priority = 9, dataProvider = "add_to_Cart_data", dataProviderClass = TestNGDataProvider.class)
    public void Enter_Password(String pwd) {
        actions.sendKeys(pom.getPASSWORD_FIELD(), pwd).build().perform();
    }

    @Test(priority = 10)
    public void Login_into_Flipkart() {
        actions.click(pom.getLOGIN_BUTTON()).build().perform();
    }
}
