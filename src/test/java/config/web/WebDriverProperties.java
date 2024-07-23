package config.web;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Log
public class WebDriverProperties {

    private static Properties prop = new Properties();
    private static final String GLOBAL_DATA_FILE_LOCATION = "/test.properties";

    public String PLATFORM_NAME = null;

    public String ENVIROMENT = null;
    public String CLIENT = null;

    public String MAIN_USERNAME= null;
    public String MAIN_PASSWORD= null;
    public static String  TESTNG_ENVIROMENT, TESTNG_CLIENT,
            SYSTEM_ENVIRONMENT,
            PROPERTIES_ENVIRONMENT,SYSTEM_CLIENT,
            PROPERTIES_CLIENT,
            URL_BASE;

    public WebDriverProperties(){
        initConfig();
    }

    public static String setTestNgEnviroment(String value) {
        TESTNG_ENVIROMENT=value;
        log.info(TESTNG_ENVIROMENT);
        return TESTNG_ENVIROMENT;
    }




    public void initConfig() {
        try {
            InputStream input;
            input = WebDriverProperties.class.getResourceAsStream(GLOBAL_DATA_FILE_LOCATION);
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PLATFORM_NAME = prop.getProperty("webdriver.platformName");
        ENVIROMENT = prop.getProperty("webdriver.env");
        CLIENT = prop.getProperty("webdriver.client");


        // system var can be set as $env:webdriver_environment='dev' (windows)
        // system var can be set as $env:webdriver_client='client1' (windows)


        SYSTEM_ENVIRONMENT = System.getenv("webdriver_environment");
        PROPERTIES_ENVIRONMENT = prop.getProperty("webdriver.env");

        SYSTEM_CLIENT = System.getenv("webdriver_client");
        PROPERTIES_CLIENT = prop.getProperty("webdriver.client");
    }
    public String getPlatformName(){
        return PLATFORM_NAME;
    }
    public String getSystemClient() {
        log.info("SYSTEM webdriver.client for this test set is " + SYSTEM_CLIENT);
        return SYSTEM_CLIENT;
    }
    public String getTestNgClient() {
        return TESTNG_CLIENT;
    }
    public String getClient(){
        SYSTEM_CLIENT = getSystemClient();
        TESTNG_CLIENT = getTestNgClient();
        PROPERTIES_CLIENT = getPropertiesClient();
        CLIENT =
                StringUtils.isNotEmpty(SYSTEM_CLIENT)
                        ? SYSTEM_CLIENT
                        : StringUtils.isNotEmpty(TESTNG_CLIENT)
                        ? TESTNG_CLIENT
                        : StringUtils.isNotEmpty(PROPERTIES_CLIENT) ? PROPERTIES_CLIENT : null;

        return CLIENT;
    }
    public String getUrlBase(){
        String urlProperty = String.format("webdriver.%s.urlBase.%s", getEnvironment(), getClient());
        URL_BASE = prop.getProperty(urlProperty) != null ? prop.getProperty(urlProperty) : null;

        if (StringUtils.isEmpty(URL_BASE)) {
            urlProperty = String.format(
                    "webdriver.%s.urlBase.%s", getPropertiesEnvironment(), getPropertiesClient());
            URL_BASE = prop.getProperty(urlProperty) != null ? prop.getProperty(urlProperty) : null;
        }
        Assert.assertTrue(StringUtils.isNotEmpty(URL_BASE), "url base malformation");

        return URL_BASE;
    }

    public String getMainUsername(){
        String rawMainUserName = String.format("webdriver.%s.adminUser.%s", getEnvironment(), getClient());
        return prop.getProperty(rawMainUserName);
    }

    public String getMainUserPass(){
        String rawMainUserPass = String.format("webdriver.%s.adminUserPass.%s",getEnvironment(),getClient());
        return prop.getProperty(rawMainUserPass);
    }


    public String getSystemEnvironment() {
        log.info("SYSTEM webdriver.env for this test set is " + SYSTEM_ENVIRONMENT);
        return SYSTEM_ENVIRONMENT;
    }

    public String getTestNgEnvironment() {
        return TESTNG_ENVIROMENT;
    }

    public String getPropertiesEnvironment() {
        return PROPERTIES_ENVIRONMENT;
    }
    public String getPropertiesClient() {
        return PROPERTIES_CLIENT;
    }

    public String getEnvironment() {
        SYSTEM_ENVIRONMENT = getSystemEnvironment();
        TESTNG_ENVIROMENT = getTestNgEnvironment();
        PROPERTIES_ENVIRONMENT = getPropertiesEnvironment();
        ENVIROMENT = StringUtils.isNotEmpty(SYSTEM_ENVIRONMENT)
                ? SYSTEM_ENVIRONMENT
                : StringUtils.isNotEmpty(TESTNG_ENVIROMENT)
                ? TESTNG_ENVIROMENT
                : StringUtils.isNotEmpty(PROPERTIES_ENVIRONMENT) ? PROPERTIES_ENVIRONMENT : null;

        return ENVIROMENT;
    }

    public static String setTestNgEnvironment(String value) {
        TESTNG_ENVIROMENT = value;
        log.info(TESTNG_ENVIROMENT);
        return TESTNG_ENVIROMENT;
    }

    public static String setTestNgClient(String value) {
        TESTNG_CLIENT=value;
        log.info(TESTNG_CLIENT);
        return TESTNG_CLIENT;
    }


}
