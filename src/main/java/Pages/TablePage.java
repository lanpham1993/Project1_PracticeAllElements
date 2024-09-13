package Pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import Base.PageBase;

public class TablePage extends PageBase {

	public String getPrice(String item) {
		return getText("colPrice_XPATH", item);
	}

	public void sortByCountry() {
		click("colCountry_XPATH");
	}

	public void sortByPopulation() {
		click("colPopulation_XPATH");
	}

	public String GetPopulation(String country) {
		boolean foundCountry = false;

		while (!foundCountry) {
			List<WebElement> countryListedCurrentPage = getListElement("colCountry_XPATH", country);
			List<WebElement> btndisableNext = getListElement("btndisbaleNext_XPATH");

			// if true -> found country
			if (countryListedCurrentPage.size() > 0) {

				foundCountry = true;
			} else if (btndisableNext.size() == 0) {
				// button Next is not disable
				scrollDown("btnNext_ID");
				click("btnNext_ID");
			} else {
				return "-1";
			}
		}
		return getText("colPopulation_XPATH", country);
	}

}
