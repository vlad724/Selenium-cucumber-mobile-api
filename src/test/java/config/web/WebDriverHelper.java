package config.web;

import io.cucumber.datatable.DataTable;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.SkipException;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static myProject.web.PagesObjects.EmergenciasPageObjects.*;

@Log
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
            log.info(String.format("waiting element: %s", element));
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            isDisplayed=wait.until(ExpectedConditions.presenceOfElementLocated(element)).isDisplayed() && wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
        }catch (NoSuchElementException | TimeoutException e){
            isDisplayed=false;
            log.info(String.valueOf(e));
        }
        log.info(String.format("%s visibility is: %s",element,isDisplayed));
        return isDisplayed;
    }

    public Alert isAlertPresent(WebDriver driver){
        Alert simpleAlert = null;
        try{
            WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
            wait.until(ExpectedConditions.alertIsPresent());
            simpleAlert = driver.switchTo().alert();
            log.info("Alert is present");

        }catch(Exception e){
            log.info("Alert is not present");
        }
        return simpleAlert;
    }

    public void getWindowsHandle(WebDriver driver, String windowsName){
        boolean alreadyExist;
        sleep(10);
        if (windowsHandle.containsKey(windowsName)) {
            driver.switchTo().window(windowsHandle.get(windowsName));
            log.info(
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
                        log.info(
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
            log.info("Scrolling to element: " + locator.toString());
            jse.executeScript("arguments[0].scrollIntoView();", elem);

        }else{
            throw new SkipException("scrollToElement: Locator was not present" + locator);
        }


    }
    public void scrollToElement(WebDriver driver, WebElement elm){
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        if(elm!=null){
            log.info("Scrolling to element: " + elm.toString());
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
            log.info("Clicking to element: " + locator.toString());
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
        log.info(String.format("Checking if page is loaded..."+ getActual));

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

    public DataTable createDataTable(List<List<String>> table) {
        DataTable data;
        data = DataTable.create(table);
        log.info(data.toString());
        return data;
    }

    public void jsSendKeys(WebDriver driver,By locator, String value) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement elem = getElement(driver, locator);
        if (elem != null) {
            jse.executeScript(String.format("arguments[0].value='%s';", value), elem);
            log.info(locator + " value set by Js " + value);
        } else {
            throw new SkipException("Locator was not present " + locator);
        }
    }

    public void click(WebDriver driver, By locator) {
        WebElement elem = getElement(driver, locator);
        if (elem != null) {
            elem.click();
            log.info(locator + " clicked");
        } else {
            throw new SkipException("Locator was not present " + locator);
        }
    }

    public void selectOptionDropdownByText(WebDriver driver, By locator, String optionToSelect) {
        log.info("Select option: " + optionToSelect + " by text");
        WebElement selectElm = getElement(driver, locator);
        if (selectElm != null) {
            Select select = new Select(selectElm);
            List<WebElement> selectListOpt = select.getOptions();
            Optional<WebElement> matchingOption = selectListOpt.stream()
                    .filter(option -> option.getText().equals(optionToSelect))
                    .findFirst();
            matchingOption.ifPresent(elm -> elm.click());

        } else {
            throw new SkipException("Locator was not present " + locator);
        }

    }

    public void setInputValues(WebDriver driver, By locator, String key, String valueToBeSet) {
        if (isWebElementDisplayed(driver, locator)) {
            WebElement divElements = getElement(driver, locator);
            WebElement removeElem = divElements.findElement(STEPPER_REMOVE_BUTTON);
            WebElement addElem = divElements.findElement(STEPPER_ADD_BUTTON);
            WebElement valueElem = null;

            if (StringUtils.containsIgnoreCase(key, "Cantidad de adultos")) {
                valueElem = divElements.findElement(STEPPER_ADULTS_VALUE_TEXT_BOX);
            } else if (StringUtils.containsIgnoreCase(key, "Menores de 12 años")) {
                valueElem = divElements.findElement(STEPPER_CHILDREN_VALUE_TEXT_BOX);
            }

            if (!removeElem.isDisplayed() && !addElem.isDisplayed() && !valueElem.isDisplayed()) {
                Assert.fail(String.format("Select %s elements are not present", key));
            }

            String currentValue = getAttribute(valueElem, "value");

            if (StringUtils.isNotEmpty(currentValue)
                    && Integer.parseInt(currentValue) > Integer.parseInt(valueToBeSet)) {
                int clicksQuantity = Integer.parseInt(currentValue) - Integer.parseInt(valueToBeSet);
                for (int i = 0; i < clicksQuantity; ++i) {
                    sleep(1);
                    removeElem.click();
                }

            } else if (StringUtils.isNotEmpty(currentValue)
                    && Integer.parseInt(currentValue) < Integer.parseInt(valueToBeSet)) {
                int clicksQuantity = Integer.parseInt(valueToBeSet) - Integer.parseInt(currentValue);
                for (int i = 0; i < clicksQuantity; ++i) {
                    sleep(5);
                    addElem.click();
                }
            } else {
                log.info(key + ": already contain the input value");
            }
        }
    }

    public String getAttribute(WebElement locator, String attribute) {
        String value = locator.getAttribute(attribute);
        log.info(locator + " return the value " + value);
        return value;
    }
    //)
}
