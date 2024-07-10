package config.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.java.Log;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Log

public class WebDriverFactory {



    public static WebDriver createNewDriver(String platform, String urlBase) throws Exception {
        WebDriver driver;

        if (!"CHROME_LOCAL".equalsIgnoreCase(platform)) {
            log.info("Creating session using boni garcia");
        }

        if ("FIREFOX".equalsIgnoreCase(platform)) {
            WebDriverManager.firefoxdriver().clearResolutionCache().forceDownload().setup();
            driver = new FirefoxDriver();

        } else if ("CHROME".equalsIgnoreCase(platform)) {
            WebDriverManager.chromedriver().clearResolutionCache().forceDownload().setup();
            Map<String, Object> prefs = new HashMap<String, Object>();
            ChromeOptions options = new ChromeOptions();
            prefs.put(
                    "download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
            prefs.put("download.prompt_for_download", false);
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);

        } else if ("CHROME_LOCAL".equalsIgnoreCase(platform)) {
            System.setProperty(
                    "webdriver.chrome.driver",
                    getCurrentPath() + "\\src\\test\\resources\\bin\\windows32\\chromedriver.exe");
            Map<String, Object> prefs = new HashMap<String, Object>();
            ChromeOptions options = new ChromeOptions();
            prefs.put(
                    "download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
            prefs.put("download.prompt_for_download", false);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        } else if("CHROME_SELENIUM_GRID".equalsIgnoreCase(platform)){
            //para utilizar selenium grid se descarga el jar en https://www.selenium.dev/documentation/grid/getting_started/, se descarga el jar en leatest release y luego se guarda
            // luego de guardarlo en una ruta especifica se ejecuta el comando java -jar "(ruta del jar)" standalone --selenium-manager true desde la terminal
            //ejemplo java -jar "C:\Selenium grid\selenium-server-4.22.0.jar" standalone --selenium-manager true
            //luego se va a http://localhost:4444 para revisar

            //en caso de ejecutar con archivo de configuraciones se crean uno y luego ejecutan con comando
            // java -jar "(ruta del jar)" standalone --selenium-manager --config "(ruta del archivo)"
            //ejemplo java -jar "C:\Selenium grid\selenium-server-4.22.0.jar" standalone --selenium-manager true --config "C:\Selenium grid\config.toml"
            //se debe cambiar el puerto del metodo RemoteWebDriver

            log.info("Creating chrome sesion from selenium grid");
            ChromeOptions options = new ChromeOptions();
            options.setCapability("platformName", "Windows");
            options.setCapability("browserName", "chrome");
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver =  new RemoteWebDriver(new URL("http://localhost:4444"),options);
        }else {
            log.info("The Driver is not selected properly, invalid name: " + platform);
            return null;
        }

        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(urlBase);
        return driver;
    }


    private static Object getCurrentPath() {
        return Paths.get("").toAbsolutePath().toString();
    }
}
