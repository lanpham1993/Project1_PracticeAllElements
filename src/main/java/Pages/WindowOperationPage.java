package Pages;

import java.util.Iterator;
import java.util.Set;

import Base.PageBase;

public class WindowOperationPage extends PageBase {

	public void goToNewTab() {
		click("btnNewTab_XPATH");

	}

	public void goToReplaceWindow() {
		click("btnReplaceWindow_XPATH");
	}

	public void goToNewWindow() {
		click("btnNewWindow_XPATH");
	}

	public void switchToNewWindow() {
		String currentWindow = getWindowHandle();

		Set<String> handles = getWindowHandles();

		// switch to new window
		Iterator<String> iter = handles.iterator();
		String newWindow = null;
		while (iter.hasNext()) {
			newWindow = iter.next();
			if (!currentWindow.equals(newWindow)) {
				driver.switchTo().window(newWindow);
			}
		}

	}
}
