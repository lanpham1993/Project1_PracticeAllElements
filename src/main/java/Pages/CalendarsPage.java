package Pages;

import org.openqa.selenium.By;

import Base.PageBase;

public class CalendarsPage extends PageBase {

	public void goToSubmit() {
		click("btnSubmit_CSS");
	}

	public void goToCalendar() {
		click("txtCalendar_ID");
	}

	public void selectDate(String year, String month, String day) {
		click("txtCalendar_ID");

		// FIND MONTH AND YEAR
		while (true) {
			String displayedMonth = getText("txtMonth_CSS");
			String displayedYear = getText("txtYear_CSS");

			if (displayedMonth.equals(month) && displayedYear.equals(year)) {
				break;
			}

			click("txtNext_CSS");
		}
		driver.findElement(By.cssSelector("a[data-date='" + day + "']")).click();
	}

	public String getDate() {
		return getText("txtSelectedDate_CSS");
	}

}
