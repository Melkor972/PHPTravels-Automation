package User_FE.FE_Page_Factory.Components;

import User_FE.FE_Page_Factory.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar {
    WebDriver driver;
    @FindBy(linkText = "HOME")
    private WebElement homeButton;

    @FindBy(linkText = "HOTELS")
    private WebElement hotelsButton;

    @FindBy(linkText = "MY ACCOUNT")
    private WebElement authentificationDropdown;

    @FindBy(linkText = "Login")
    private WebElement loginButton;

    @FindBy(linkText = "Sign up")
    private WebElement registerButton;

    public NavigationBar(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }

    public LoginPage goToLoginPage(){
        authentificationDropdown.click();
        loginButton.click();
        return new LoginPage(driver);
    }
}
