package Testcases;

import org.testng.annotations.AfterSuite;

import Base.PageBase;

public class BaseTest {

	@AfterSuite
	public void tearDown() {
		PageBase.quit();
	}
}
