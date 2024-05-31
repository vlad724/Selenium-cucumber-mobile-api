package myProject.web.Pages;


import config.web.WebDriverHelper;
import myProject.web.PagesObjects.OmayoPageObjects;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OmayoPage extends WebDriverHelper {
    OmayoPageObjects omayoPageObjects = new OmayoPageObjects();
    public void iFrameMenu(WebDriver driver){
        WebElement frame1Elem = getElement(driver, omayoPageObjects.iframe1Loc);
        if (frame1Elem != null){
            driver.switchTo().frame(frame1Elem);//ir al frame1

            WebElement divQuestionFrame1Elem = getElement(driver, omayoPageObjects.divQuestionFrame1Loc);
            if(divQuestionFrame1Elem!=null){
                System.out.println(divQuestionFrame1Elem.getText());
            }

        }
    }

    public void clickAlertAceept(WebDriver driver){
        WebElement alert1Elem = getElement(driver, omayoPageObjects.btnAlert1);

        if (alert1Elem != null){
            alert1Elem.click();

            //isAlertPresent es un metodo creado en la clase WebDriverHelper parta verificar que la alerta este presente
            Alert alert1 =  isAlertPresent(driver);


            if(alert1 != null){
                alert1.accept();
            }

        }

    }

    public void IframeMenu2(WebDriver driver){
        WebElement frame2Elem = getElement(driver, omayoPageObjects.iframe2Loc);
        if (frame2Elem != null) {
            driver.switchTo().frame(frame2Elem);//ir al frame2

            WebElement divQuestionFrame2Elem = getElement(driver, omayoPageObjects.divQuestion2Frame1Loc);
            if(divQuestionFrame2Elem!=null){
                System.out.println(divQuestionFrame2Elem.getText());
            }


            driver.getPageSource();
        }

    }

}
