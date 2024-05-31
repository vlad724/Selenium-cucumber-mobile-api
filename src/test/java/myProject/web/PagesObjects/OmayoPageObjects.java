package myProject.web.PagesObjects;

import org.openqa.selenium.By;

public class OmayoPageObjects {
    public By txtFNameLoc = By.name("fname");
    public By txtFIdLoc =  By.id("ta1");
    public By txtFxpathLoc = By.xpath("//textarea[contains(text(),'The cat was playing in the garden.')]");
    public By multiselect1Loc = By.id("multiselect1");
    public By drop1Loc = By.id("drop1");
    public By table1loc = new By.ByCssSelector("table[id='table1'] td");
    public By table1PuneLoc = By.xpath("//table[@id='table1']//td[contains(.,'Pune')]");
    public By btnSameNameLoginLoc = By.xpath("//button[contains(text(),'Login')]");
    public By btnSameNameLoc = By.xpath("//button[@name='samename']");
    public By btnAlert1 = By.id("alert1");
    public By btnConfirm1 = By.id("confirm");
    public By btnPrompt1 = By.id("prompt");
    public By seleniumTutorialLinkLoc = By.id("selenium143");
    public By newPopUpLoc = By.linkText("Open a popup window");
    public By questionPopUp = By.xpath("//h3[contains(text(),'New Window')]");
    public By questionLoc = By.linkText("What is Salenium?");
    public By iframe1Loc = By.xpath("//iframe[@id='iframe1']");
    public By divQuestionFrame1Loc =By.xpath("(//div[@class='widget-content']//table[contains(., 'What is Selenium?')])[1]");
    public By iframe2Loc = By.xpath("//iframe[@id='iframe2']");
    public By divQuestion2Frame1Loc = By.cssSelector("div[id*='post-body']");
    public By table1xLoc =By.id("table1");

}
