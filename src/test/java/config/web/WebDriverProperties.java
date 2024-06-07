package config.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverProperties {

    private static Properties prop = new Properties();
    private static final String GLOBAL_DATA_FILE_LOCATION = "/test.properties";

    public String PLATFORM_NAME = null;

    public String ENVIROMENT = null;
    public String CLIENT = null;

    public String MAIN_USERNAME= null;
    public String MAIN_PASSWORD= null;

    public WebDriverProperties(){
        initConfig();
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


    }
    public String getPlatformName(){
        return PLATFORM_NAME;
    }
    public String getEnviroment(){
        return ENVIROMENT;
    }
    public String getClient(){
        return CLIENT;
    }
    public String getUrlBase(){
        String rawUrlBase = String.format("webdriver.%s.urlBase.%s",getEnviroment(),getClient());
        return prop.getProperty(rawUrlBase);
    }

    public String getMainUsername(){
        String rawMainUserName = String.format("webdriver.%s.adminUser.%s",getEnviroment(),getClient());
        return prop.getProperty(rawMainUserName);
    }

    public String getMainUserPass(){
        String rawMainUserPass = String.format("webdriver.%s.adminUserPass.%s",getEnviroment(),getClient());
        return prop.getProperty(rawMainUserPass);
    }

}
