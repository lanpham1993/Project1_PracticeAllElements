package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PageBase {

	public static WebDriver driver;
	public static String browser;

	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis;

	public static Logger log = Logger.getLogger("");

	public static WebDriverWait wait;

	public static JavascriptExecutor js;
	public static int i = 0;

	public PageBase() {
		if (driver == null) {

			// load file configuration
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				config.load(fis);
				log.debug("***** Config file is loaded !!!!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fis);
				log.debug("***** OR file is loaded !!!!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Jenkins Browser filter configuration
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();

				Map<String, Object> prefs = new HashedMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("cendentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disbale-extensions");
				options.addArguments("--disable-infobars");
				options.addArguments("--disable-search-engine-choice-screen");

				driver = new ChromeDriver(options);
				log.debug("Chrome launched  !!!!");
			} else if (config.getProperty("browser").equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("weddriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to " + config.getProperty("testsiteurl"));

			driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
			js = (JavascriptExecutor) driver;

		}
	}

	public static void quit() {
		driver.close();
		driver.quit();
	}

	public WebElement getElement(String locator) {
		WebElement e = null;

		if (locator.toUpperCase().endsWith("_CSS")) {
			e = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.toUpperCase().endsWith("_XPATH")) {
			e = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.toUpperCase().endsWith("_ID")) {
			e = driver.findElement(By.id(OR.getProperty(locator)));
		}
		return e;
	}

	public WebElement getElement(String locator, String parameter) {
		WebElement e = null;
		String getLocator = OR.getProperty(locator);
		String replaceParameter = getLocator.replace("parameter", parameter);
		if (locator.toUpperCase().endsWith("_CSS")) {
			e = driver.findElement(By.cssSelector(replaceParameter));
		} else if (locator.toUpperCase().endsWith("_XPATH")) {
			e = driver.findElement(By.xpath(replaceParameter));
		} else if (locator.toUpperCase().endsWith("_ID")) {
			e = driver.findElement(By.id(replaceParameter));
		}
		return e;
	}

	public List<WebElement> getListElement(String locator) {
		List<WebElement> lstElement = null;
		if (locator.toUpperCase().endsWith("_CSS")) {
			lstElement = driver.findElements(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.toUpperCase().endsWith("_XPATH")) {
			lstElement = driver.findElements(By.xpath(OR.getProperty(locator)));
		} else if (locator.toUpperCase().endsWith("_ID")) {
			lstElement = driver.findElements(By.id(OR.getProperty(locator)));
		}
		return lstElement;
	}

	public List<WebElement> getListElement(String locator, String parameter) {
		List<WebElement> lstElement = null;
		String getLocator = OR.getProperty(locator);
		String replaceParameter = getLocator.replace("parameter", parameter);
		if (locator.toUpperCase().endsWith("_CSS")) {
			lstElement = driver.findElements(By.cssSelector(replaceParameter));
		} else if (locator.toUpperCase().endsWith("_XPATH")) {
			lstElement = driver.findElements(By.xpath(replaceParameter));
		} else if (locator.toUpperCase().endsWith("_ID")) {
			lstElement = driver.findElements(By.id(replaceParameter));
		}
		return lstElement;
	}

	public void click(String locator) {
		getElement(locator).click();
		log.debug("Clicking on an Element");
		// test.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	public String getText(String locator) {
		return getElement(locator).getText();

		// test.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	public String getText(String locator, String parameter) {
		return getElement(locator, parameter).getText();

		// test.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	public void type(String locator, String value) {
		getElement(locator).sendKeys(value);

		log.debug("Typing in an Element : " + locator + " entered value as : " + value);

		// test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " +
		// value);

	}

	public void scrollDown(String locator) {
		WebElement e = getElement(locator);
		new Actions(driver).scrollToElement(e).perform();
		js.executeScript("window.scrollBy(0,550)", "");
	}

	public static void scrollUp(WebElement element) {
		new Actions(driver).scrollToElement(element).perform();
		// js.executeScript("window.scrollBy(0,-400)", "");
	}

	public void waitForElementText(String locator, String text) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageBase.wait.until(ExpectedConditions.textToBePresentInElement(getElement(locator), text));
	}

	public void dragAndDropByOffset(String locator, int x, int y) {
		Actions action = new Actions(driver);
		action.dragAndDropBy(getElement(locator), x, y).perform();

	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

}
