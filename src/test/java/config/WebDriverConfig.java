package config;

import config.web.WebDriverFactory;
import config.web.WebDriverProperties;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;




@Log
public class WebDriverConfig {

    static WebDriverProperties webDriverProperties = new WebDriverProperties();

    public static WebDriver initWebConfig() throws Exception {

        WebDriver driver;
        String platform = webDriverProperties.getPlatformName();

        String UrlBase = webDriverProperties.getUrlBase();

        log.info(
                "***********************************************************************************************************");
        log.info(
                "[ POM Configuration ] - Read the basic properties configuration from: ../test.properties");
        /** ****** POM Information ******* */
        log.info("[ POM Configuration ] - Browser: " + platform);
        log.info(
                "***********************************************************************************************************");

        /** **** Load the driver ****** */
        driver = WebDriverFactory.createNewDriver(platform,UrlBase);

        return driver;
    }
}
