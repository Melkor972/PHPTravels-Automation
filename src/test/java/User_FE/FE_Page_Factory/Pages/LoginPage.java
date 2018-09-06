package User_FE.FE_Page_Factory.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage  {
    WebDriver driver;

    @FindBy(name = "username")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"loginfrm\"]/div[1]/div[5]/button")
    private WebElement loginButton;

    @FindBy(id = "remember-me")
    private WebElement rememberMeCheckbox;

    @FindBy(linkText = "SIGN UP")
    private WebElement registerButton;

    @FindBy(linkText = "FORGET PASSWORD")
    private WebElement forgetPasswordButton;

    @FindBy(xpath = "//*[@id=\"loginfrm\"]/div[1]/div[2]/div")
    private WebElement invalidLoginAlert;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }

    public void login(String email, String password, String stayLogged){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        if(stayLogged == "True"){
            rememberMeCheckbox.click();
        }
        loginButton.click();


    }
    public AccountPage successfulLogin(String email, String password, String stayLogged){
        login(email,password,stayLogged);
        return new AccountPage(driver);
    }
    public void unsuccessfulLogin(String email, String password, String stayLogged){
        login(email,password,stayLogged);
        checkErrorMessage();
    }

    public void checkErrorMessage(){
        Assert.assertTrue(invalidLoginAlert.isDisplayed());
    }
}
