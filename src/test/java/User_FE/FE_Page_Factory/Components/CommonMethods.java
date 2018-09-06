package User_FE.FE_Page_Factory.Components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public abstract class CommonMethods {



    public void select(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void chooseDate(WebElement calendarPopup, WebElement currentMonthYear, WebElement nextMonthButton,
                           List<WebElement> days, String date) {
        String[] splitedDate = null;
        String monthYear = null;
        int day = 0;
        if (!(date.equals(""))) {
            splitedDate = date.split("/");
            monthYear = splitedDate[1] + " " + splitedDate[2];
            day = Integer.parseInt(splitedDate[0]);

        }

        if (!(date.equals(""))) {

            if (!(currentMonthYear.isDisplayed())) {
                calendarPopup.click();
            }
            while (true) {
                String cm = currentMonthYear.getText();
                if (cm.equals(monthYear)) {
                    break;
                } else
                    nextMonthButton.click();
            }
            days.get(day - 1).click();
        }
    }
}