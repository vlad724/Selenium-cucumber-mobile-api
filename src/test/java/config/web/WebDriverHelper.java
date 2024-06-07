package config.web;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class WebDriverHelper extends WebDriverDataManagementHelper{
    //final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(5); darle segundos de espera a que encuentre elemento
    final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(5);
    public HashMap<String, String> windowsHandle = new HashMap<>();



    //metodos para cuando un elemento no esta presente (
    public WebElement getElement(WebDriver driver, By loc){
        return isWebElementDisplayed(driver, loc)? driver.findElement(loc):null;

    }

    public List<WebElement> getElements(WebDriver driver, By loc) {
        return isWebElementDisplayed(driver, loc)? driver.findElements(loc):null;
    }

    public boolean isWebElementDisplayed(WebDriver driver, By element) {
        boolean isDisplayed;
        try{
            System.out.println(String.format("waiting element: %s", element));
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            isDisplayed=wait.until(ExpectedConditions.presenceOfElementLocated(element)).isDisplayed() && wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
        }catch (NoSuchElementException | TimeoutException e){
            isDisplayed=false;
            System.out.println(String.valueOf(e));
        }
        System.out.println(String.format("%s visibility is: %s",element,isDisplayed));
        return isDisplayed;
    }

    public Alert isAlertPresent(WebDriver driver){
        Alert simpleAlert = null;
        try{
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            wait.until(ExpectedConditions.alertIsPresent());
            simpleAlert = driver.switchTo().alert();
            System.out.println("Alert is present");

        }catch(Exception e){
            System.out.println("Alert is not present");
        }
        return simpleAlert;
    }

    public void getWindowsHandle(WebDriver driver, String windowsName){
        boolean alreadyExist;
        sleep(10);
        if (windowsHandle.containsKey(windowsName)) {
            driver.switchTo().window(windowsHandle.get(windowsName));
            System.out.println(
                    String.format(
                            "I go to Windows: %s with value: %s ",
                            windowsName, windowsHandle.get(windowsName)));
        } else {
            for (String winHandle : driver.getWindowHandles()) {
                for (String entry : windowsHandle.keySet()) {
                    String value = windowsHandle.get(entry.trim());
                    alreadyExist = StringUtils.equalsIgnoreCase(value, winHandle);
                    if (!alreadyExist) {
                        windowsHandle.put(windowsName, winHandle);
                        System.out.println(
                                "The New window"
                                        + windowsName
                                        + "is saved in scenario with value"
                                        + windowsHandle.get(windowsName));
                        driver.switchTo().window(winHandle);
                        break;
                    }
                }
            }
        }
    }
    //)
   public void sleep(int seconds){
        try {
            Thread.sleep(1000*seconds);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
   }

   public Actions createActionBuilder(WebDriver driver){
        return new Actions(driver);
   }
   public Action moveToElement(WebDriver driver, By loc){
//buildear acciones
       return createActionBuilder(driver)
               .moveToElement(getElement(driver, loc))
               .build();
   }

    public Action moveToElementAndClick(WebDriver driver, By loc){
//buildear acciones
        return createActionBuilder(driver)
                .moveToElement(getElement(driver, loc))
                .click(getElement(driver, loc))
                .build();
    }

    public Action dragAndDropToElement(WebDriver driver, By sourceLoc, By targetLoc){
//buildear acciones
        return createActionBuilder(driver)
                .dragAndDrop(getElement(driver,sourceLoc),getElement(driver,targetLoc))
                .build();
    }

    //ejecutar metodos js y esta vez es para scroll a elemento(
    public void scrollToElement(WebDriver driver, By locator){
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        //todo lo que tenga que ver con js no se puede ocupar metodo getlement() si no que
        WebElement elem =driver.findElement(locator) != null ? driver.findElement(locator) : null;

        if(elem!=null){
            System.out.println("Scrolling to element: " + locator.toString());
            jse.executeScript("arguments[0].scrollIntoView();", elem);

        }else{
            throw new SkipException("scrollToElement: Locator was not present" + locator);
        }


    }
    public void scrollToElement(WebDriver driver, WebElement elm){
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        if(elm!=null){
            System.out.println("Scrolling to element: " + elm.toString());
            jse.executeScript("arguments[0].scrollIntoView();", elm);
        }else{
            throw new SkipException("scrollToElement: Element was not present");
        }


    }

    public void jsClick(WebDriver driver, WebElement elm){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if(elm!=null){
            jse.executeScript("arguments[0].click();",elm);
        }else{
            throw new SkipException("jsClick: Element was not present");
        }

    }

    public void jsClick(WebDriver driver, By locator){
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        //todo lo que tenga que ver con js no se puede ocupar metodo getlement() si no que
        WebElement elem =driver.findElement(locator) != null ? driver.findElement(locator) : null;

        if(elem!=null){
            System.out.println("Clicking to element: " + locator.toString());
            jse.executeScript("arguments[0].click();",elem);
        }else{
            throw new SkipException("jsClick: Locator was not present" + locator);
        }

    }

    public void setAttribute(WebDriver driver, WebElement element, String key, String value){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        if(element!=null){
            executor.executeScript(String.format("arguments[0].setAttribute('%s', '%s');",key,value),element);
        }else{
            throw new SkipException("setAttribute: Element was not present");
        }
    }

    public void setAttribute(WebDriver driver, By locator, String key, String value){
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        //todo lo que tenga que ver con js no se puede ocupar metodo getlement() si no que
        WebElement element =driver.findElement(locator) != null ? driver.findElement(locator) : null;

        if(element!=null){
            executor.executeScript(String.format("arguments[0].setAttribute('%s', '%s');",key,value),element);
        }else{
            throw new SkipException("setAttribute: Locator was not present"+locator);
        }
    }

    public void waitPageCompletelyLoaded(WebDriver driver){
        String getActual = driver.getCurrentUrl();
        System.out.println(String.format("Checking if page is loaded..."+ getActual));

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(EXPLICIT_TIMEOUT)
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);

        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );


    }

    //)
}
