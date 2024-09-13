package Pages;

import Base.PageBase;

public class JavaScriptDelayPage extends PageBase {

	public void clickBtnStart() {
		click("btnStart_ID");
	}

	public void waitForDelayText() {
		waitForElementText("txtDelay_ID", "Liftoff!");
	}

}
