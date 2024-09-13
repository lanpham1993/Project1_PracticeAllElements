package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.CalendarsPage;
import Pages.HomePage;
import Pages.JavaScriptDelayPage;
import Pages.ModalsPage;
import Pages.SlidesPage;
import Pages.TablePage;
import Pages.WindowOperationPage;

public class TestComponents extends BaseTest {
	HomePage home = new HomePage();

	@Test
	public void testJavaScriptDelay() {

		JavaScriptDelayPage javaScriptDelay = home.gotoJavaScriptDelay();
		javaScriptDelay.clickBtnStart();
		javaScriptDelay.waitForDelayText();
	}

	@Test
	public void testSlider() throws InterruptedException {
		SlidesPage slider = home.gotoSlides();
		slider.clickOnSlide(1, 0);
		Thread.sleep(1000);
	}

	@Test
	public void testTable() {
		TablePage table = home.gotoTable();
		String price = table.getPrice("Oranges");
		Assert.assertEquals("$3.99", price);
		String population = table.GetPopulation("Germany");
		Assert.assertEquals(population, "83.8");
	}

	@Test
	public void testOpenNewTab() {
		WindowOperationPage window = home.goToWindowOperation();
		window.goToNewTab();
		window.switchToNewWindow();
		Assert.assertEquals(window.getUrl(), "https://automatenow.io/");

	}

	@Test
	public void testOpenReplaceWindow() {
		WindowOperationPage window = home.goToWindowOperation();
		window.goToReplaceWindow();
		Assert.assertEquals(window.getUrl(), "https://automatenow.io/");

	}

	@Test
	public void testOpenNewWindow() {
		WindowOperationPage window = home.goToWindowOperation();
		window.goToNewWindow();
		window.switchToNewWindow();
		Assert.assertEquals(window.getUrl(), "https://automatenow.io/");

	}

	@Test
	public void testCalendars() {
		CalendarsPage calendar = home.goToCalenders();
		calendar.selectDate("2024", "October", "6");
		calendar.goToSubmit();
		Assert.assertEquals(calendar.getDate(), "2024-10-06");

	}

	@Test
	public void testModals() {
		ModalsPage modals = home.goToModals();
		modals.goToSimpleModal().closeSimpleModal();
		modals.goToFormModal().modalSendMessage("Name test", "nametest@gamil.com", "test form modals").submit();
	}
}
