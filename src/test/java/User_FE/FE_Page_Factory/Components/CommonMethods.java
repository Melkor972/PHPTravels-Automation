package User_FE.FE_Page_Factory.Components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public abstract class CommonMethods {



    public void select(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void chooseDate(WebElement calendarPopup, WebElement monthYear, WebElement nextYearButton, WebElement previousYearButton,
                          WebElement currentYear ,List<WebElement> days, List<WebElement> months ,String date) {
        String[] splitedDate;
        int year = 0;
        int month =0;
        int day = 0;
        if (!(date.equals(""))) {
            splitedDate = date.split("/");
            year = Integer.parseInt(splitedDate[2]) ;
            month = Integer.parseInt(splitedDate[1]);
            day = Integer.parseInt(splitedDate[0]);





            if (!(monthYear.isDisplayed())) {
                calendarPopup.click();}
                monthYear.click();
          while (true){
              int currYear = Integer.parseInt(currentYear.getText());
              System.out.println(currYear);
              if(currYear == year){
                  break;
              }else {
                  if(currYear>year){
                      previousYearButton.click();
                  }else{
                      nextYearButton.click();}
              }
          }
months.get(month-1).click();
          calendarPopup.click();
          days.get(day-1).click();


        }
    }
}