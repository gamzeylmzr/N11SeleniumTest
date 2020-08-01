package com.gamzeyilmazer.base;

import com.gamzeyilmazer.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;

    public TestBase(){

        try {
            prop = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")+
                    "/src/main/java/com/gamzeyilmazer/config/config.properties");
            prop.load(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void initialize(){
        String browserName=prop.getProperty("browser");

        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/java/com/gamzeyilmazer/drivers/chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }

}
