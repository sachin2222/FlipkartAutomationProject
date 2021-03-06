package MobilePageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddToCart {

    public WebDriver driver;

    @FindBy(linkText = "Mobiles")
    WebElement mobile_link;

    @FindBy(xpath = "//div[@class='_3879cV']")
    List<WebElement> checkBoxList;

    @FindBy(xpath = "//div[@class='_4rR01T']")
    List<WebElement> mobileList;


    @FindBy(xpath = "//*[@id='container']/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")
    WebElement ADD_TO_CART_BUTTON;

    @FindBy(xpath = "//*[@id='container']/div/div[2]/div/div/div[1]/div/div[3]/div/form/button")
    WebElement PLACE_ORDER_BUTTON;


    @FindBy(xpath = "//div[@class='IiD88i GtCYSF']/input")
    WebElement ENTER_PHONE_NUMBER;

    @FindBy(xpath = "//button[@class='_2KpZ6l _20xBvF _3AWRsL']")
    WebElement CONTINUE_BUTTON;

    @FindBy(xpath = "//input[@type='password']")
    WebElement PASSWORD_FIELD;


    @FindBy(xpath = "//button[@type='submit']")
    WebElement LOGIN_BUTTON;


    public AddToCart(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement getLOGIN_BUTTON() {
        return LOGIN_BUTTON;
    }



    public WebElement getPLACE_ORDER_BUTTON() {
        return PLACE_ORDER_BUTTON;
    }

    public WebElement getPASSWORD_FIELD() {
        return PASSWORD_FIELD;
    }

    public WebElement getADD_TO_CART_BUTTON() {
        return ADD_TO_CART_BUTTON;
    }

    public WebElement getMobile_link() {
        return mobile_link;
    }

    public List<WebElement> getCheckBoxList() {
        return checkBoxList;
    }

    public List<WebElement> getMobileList() {
        return mobileList;
    }

    public WebElement getENTER_PHONE_NUMBER() {
        return ENTER_PHONE_NUMBER;
    }

    public WebElement getCONTINUE_BUTTON() {
        return CONTINUE_BUTTON;
    }
}
