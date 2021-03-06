package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {


    public static WebDriver driver;
    public static Properties prop;

    //Initialise the WebDriver and Invoke the Corresponding Browser.
    public static WebDriver initialiseDriver() throws IOException {

        prop = new Properties();
        FileInputStream fin = new FileInputStream("C:\\Users\\ss26370\\IdeaProjects\\MakeMyTripAutomationProject\\src\\main\\resources\\GlobalData.properties");
        prop.load(fin);

        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
           /* options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");*/
            options.addArguments("--incognito");

            System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver(options);


        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\ss26370\\IdeaProjects\\RealTimeProject\\Drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();


        } else if (browserName.equalsIgnoreCase("IE")) {
            System.setProperty("webdriver.ie.driver", "C:\\Users\\ss26370\\IdeaProjects\\RealTimeProject\\Drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();


        }

        int timeout = Integer.parseInt(prop.getProperty("timeout"));
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        return driver;

    }

    public static WebDriverWait get_Explicit_Wait_Object() {
        String expwait = prop.getProperty("explicit_wait");
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(expwait));
        return wait;

    }

    public static String getBaseURL() {
        String url = prop.getProperty("url");
        return url;


    }

    public static Properties getSystemProperty() {
        return prop;

    }

    //Take ScreenShot of Failed Test Cases Only , Calling from Listener Class.
    public static String takeScreenShot(String testcasename, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") + "\\Screenshots\\" + testcasename + ".png";
        FileHandler.copy(source, new File(dest));
        return dest;

    }

    //Select Value from the Web Element List.
    public static void select_Value_From_LIST(List<WebElement> elements, String value) {

        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equalsIgnoreCase(value)) {
                elements.get(i).click();
                break;

            }

        }

    }

    public static void get_All_Text_From_List(List<WebElement> list) {
        System.out.println("SIZE_OF_LIST:" + list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Element_Text_Value_at " + i + " index:" + list.get(i).getText());

        }

    }


}
