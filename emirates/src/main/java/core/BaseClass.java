package core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;




public class BaseClass {

    public static WebDriver webDriver;


@BeforeClass(alwaysRun = true)
public void setUp() {
    webDriver = new ChromeDriver();
    webDriver.manage().window().maximize();
    webDriver.get("https://www.emirates.com");
}

@AfterClass
    public void closeDriver(){
//    webDriver.quit();
}
}
