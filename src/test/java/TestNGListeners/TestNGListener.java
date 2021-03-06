package TestNGListeners;

import Mobile.AddToCartTest;
import Utilities.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener extends Base implements ITestListener {

    public Logger log;
    ExtentReports extentReports;
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();


    @Override
    public void onStart(ITestContext iTestContext) {
        log = LogManager.getLogger(AddToCartTest.class.getName());
        DOMConfigurator.configure("Log4J.xml");
        log.info("Log4J.xml File Has Been Configured.");

        String path = System.getProperty("user.dir") + "\\Reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Flipkart Test Automation Report");
        reporter.config().setDocumentTitle("Flipkart Automation Test Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "sachin sharma");


    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(iTestResult.getMethod().getMethodName() + ":Started");
        test = extentReports.createTest(iTestResult.getMethod().getMethodName());
        extentTest.set(test);


    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.debug(iTestResult.getMethod().getMethodName() + ":Passed");
        extentTest.get().log(Status.PASS, "Test Case Passed");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {


        String TestMethodName = iTestResult.getMethod().getMethodName();
        log.error(TestMethodName + ":Failed");
        log.error("Priority of " + TestMethodName + " Method is:" + iTestResult.getMethod().getPriority());
        String exception = iTestResult.getThrowable().getMessage();
        log.error("EXCEPTION MESSAGE:" + exception);

        extentTest.get().log(Status.FAIL, exception);

        //Take ScreenShot of failed Test cases
        try {
            WebDriver driver = (WebDriver) iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
            String path = takeScreenShot(TestMethodName, driver);
            log.error("ScreenShot Saved at:" + path);
            //Show ScreenShot on Extent Report
            extentTest.get().addScreenCaptureFromPath(path, TestMethodName);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(iTestResult.getMethod().getMethodName() + ":Skipped");
        extentTest.get().log(Status.SKIP, "Test Case Skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }


    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();

    }
}
