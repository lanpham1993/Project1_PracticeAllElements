package Pages;

import Base.PageBase;

public class SlidesPage extends PageBase {

	public void clickOnSlide(int x, int y) {
		dragAndDropByOffset("slider_ID", x, y);
	}

}
