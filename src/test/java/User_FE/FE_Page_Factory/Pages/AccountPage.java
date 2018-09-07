package User_FE.FE_Page_Factory.Pages;

import User_FE.FE_Page_Factory.Components.CommonMethods;
import Utils.ExcelUtils;
import com.sun.xml.internal.ws.handler.ServerSOAPHandlerTube;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class AccountPage extends CommonMethods {
    private WebDriver driver;

   @FindAll(@FindBy(how = How.XPATH,using = "//*[@id=\"bookings\"]/div[contains(@class, 'row')]"))
    private List<WebElement> tripsList;

   @FindBy(xpath = "//*[@id=\"AddReview4\"]/div/div")
   private WebElement reviewForm;



    public void showInvoice(String bookingID){


        WebElement element = findTripByID(bookingID);
        String invoiceButtonXpath = getAbsoluteXPath(element)+"/div[4]/a";
        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(By.xpath(invoiceButtonXpath)).click();


        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        Assert.assertTrue(driver.getTitle().equals("Invoice"));



    }

    public void addComment(String bookingID, String cleanMark ,String comfortMark, String locationMark, String facilityMark, String staffMark, String review){
      WebElement element = findTripByID(bookingID);
      String reviewButtonXpath = getAbsoluteXPath(element)+"/div[4]/span";
      WebElement reviewButton = driver.findElement(By.xpath(reviewButtonXpath));
      reviewButton.click();
      WebDriverWait webDriverWait = new WebDriverWait(driver,5);

      webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"AddReview"+bookingID+"\"]/div/div/div[1]/h4"))));
      WebElement cleanSelect = driver.findElement(By.xpath("//select[@name ='reviews_clean' and @id = '"+bookingID+"']"));
        WebElement comfortSelect = driver.findElement(By.xpath("//select[@name ='reviews_comfort' and @id = '"+bookingID+"']"));
        WebElement locationSelect = driver.findElement(By.xpath("//select[@name ='reviews_location' and @id = '"+bookingID+"']"));
        WebElement facilitySelect = driver.findElement(By.xpath("//select[@name ='reviews_facilities' and @id = '"+bookingID+"']"));
        WebElement staffSelect = driver.findElement(By.xpath("//select[@name ='reviews_staff' and @id = '"+bookingID+"']"));
        WebElement reviewField = driver.findElement(By.xpath("//*[@id='reviews-form-"+bookingID+"']//textarea"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@id = '"+bookingID+"']"));
        reviewField.sendKeys(review);;
        select(cleanSelect,cleanMark);
        select(comfortSelect,comfortMark);
        select(locationSelect, locationMark);
        select(facilitySelect,facilityMark);
        select(staffSelect,staffMark);
        submitButton.click();

        webDriverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@id=\"AddReview"+bookingID+"\"]/div/div/div[1]/h4"))));

        int attempts = 0;
        while (attempts<3){
       try {
           Assert.assertTrue(reviewButton.getText().equals("Your Review "));
           break;
       }catch (org.openqa.selenium.StaleElementReferenceException e){
           attempts++;
       }

    }}


    private WebElement findTripByID(String bookingID){
        bookingID = "Booking ID" + " " +bookingID;
        //some delay is required because tripList doesn't always fully filled without it.
        try{
            Thread.sleep(500);
        }catch (Exception e){}
        WebElement el=null;


        for(WebElement element : tripsList){
            if(element.getText().contains(bookingID)){
                el=element;

            }
        }

        return el;
    }

    public  String getAbsoluteXPath(WebElement element)
    {
        return (String) ((JavascriptExecutor) driver).executeScript(
                "function absoluteXPath(element) {"+
                        "var comp, comps = [];"+
                        "var parent = null;"+
                        "var xpath = '';"+
                        "var getPos = function(element) {"+
                        "var position = 1, curNode;"+
                        "if (element.nodeType == Node.ATTRIBUTE_NODE) {"+
                        "return null;"+
                        "}"+
                        "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {"+
                        "if (curNode.nodeName == element.nodeName) {"+
                        "++position;"+
                        "}"+
                        "}"+
                        "return position;"+
                        "};"+

                        "if (element instanceof Document) {"+
                        "return '/';"+
                        "}"+

                        "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"+
                        "comp = comps[comps.length] = {};"+
                        "switch (element.nodeType) {"+
                        "case Node.TEXT_NODE:"+
                        "comp.name = 'text()';"+
                        "break;"+
                        "case Node.ATTRIBUTE_NODE:"+
                        "comp.name = '@' + element.nodeName;"+
                        "break;"+
                        "case Node.PROCESSING_INSTRUCTION_NODE:"+
                        "comp.name = 'processing-instruction()';"+
                        "break;"+
                        "case Node.COMMENT_NODE:"+
                        "comp.name = 'comment()';"+
                        "break;"+
                        "case Node.ELEMENT_NODE:"+
                        "comp.name = element.nodeName;"+
                        "break;"+
                        "}"+
                        "comp.position = getPos(element);"+
                        "}"+

                        "for (var i = comps.length - 1; i >= 0; i--) {"+
                        "comp = comps[i];"+
                        "xpath += '/' + comp.name.toLowerCase();"+
                        "if (comp.position !== null) {"+
                        "xpath += '[' + comp.position + ']';"+
                        "}"+
                        "}"+

                        "return xpath;"+

                        "} return absoluteXPath(arguments[0]);", element);
    }

    public AccountPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
}
