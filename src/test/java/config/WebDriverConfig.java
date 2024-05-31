package config;

import config.web.WebDriverFactory;
import config.web.WebDriverProperties;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class WebDriverConfig {

    static WebDriverProperties webDriverProperties = new WebDriverProperties();

    public static WebDriver initWebConfig() throws Exception {
        Logger log = LoggerFactory.getLogger(WebDriverConfig.class);

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
