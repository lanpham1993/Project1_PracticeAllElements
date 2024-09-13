package Pages;

import Base.PageBase;

public class IFramePage extends PageBase {

	public void switchFrame(int frame) {
		driver.switchTo().frame(frame);
	}

	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

}
