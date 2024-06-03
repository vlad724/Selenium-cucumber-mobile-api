package myProject.web.PagesObjects;

import org.openqa.selenium.By;

public class OrangeHRMPageObjects {


    public By userNameLoc = new By.ByCssSelector("input[name='username']");
    public By passwordLoc = new By.ByCssSelector("input[placeholder='Password']");
    public By loginBtnLoc =  By.xpath("//button[@type='submit']");
    public By userBulletLoc =  By.cssSelector("img[alt='profile picture']");

    public By adminUserTableListLoc = new By.ByCssSelector("div[class='oxd-table-card']");
    public By adminModuleLoc = By.cssSelector("a[href='/web/index.php/admin/viewAdminModule']");


}
