package com.qa.opencart.factory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//	1.init_prop
//	2.init_driver

public class DriverFactory {
	
		private Properties prop;
		private WebDriver driver;

		public Properties init_prop() {
			try {
				FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
				prop = new Properties();
				prop.load(fis);
			} catch (FileNotFoundException e) {				
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}
			return prop;
		}
		public WebDriver init_driver() {

			String browserName = init_prop().getProperty("browser");
			System.out.println("Browser started----->" + browserName.toUpperCase());
			switch (browserName.toUpperCase()) {
			case "CHROME":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "IE":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			case "FF":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Please pass the right browser name " + browserName);
			}
			driver.get(init_prop().getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			return driver;
		}

	}

