package Pages;

import Base.PageBase;

public class HomePage extends PageBase {

	public JavaScriptDelayPage gotoJavaScriptDelay() {
		click("javascriptDelay_XPATH");
		return new JavaScriptDelayPage();
	}

	public SlidesPage gotoSlides() {
		click("sliders_XPATH");
		return new SlidesPage();
	}

	public TablePage gotoTable() {
		click("table_XPATH");
		return new TablePage();
	}

	public JavaScriptDelayPage gotoAds() {
		click("javascriptDelay_XPATH");
		return new JavaScriptDelayPage();
	}

	public WindowOperationPage goToWindowOperation() {
		click("window_XPATH");
		return new WindowOperationPage();
	}

	public CalendarsPage goToCalenders() {
		click("calendar_XPATH");
		return new CalendarsPage();
	}

	public ModalsPage goToModals() {
		click("modals_XPATH");
		return new ModalsPage();
	}

	public IFramePage goToIframes() {
		click("iframes_XPATH");
		return new IFramePage();
	}

}
