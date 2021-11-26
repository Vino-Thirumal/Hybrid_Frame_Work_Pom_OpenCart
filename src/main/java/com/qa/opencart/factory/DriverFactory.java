package com.qa.opencart.factory;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
	
//	1. init_prop
//	2. init_driver

public class DriverFactory {

	public Properties prop;
	public WebDriver driver;
	public static String border = "hightlight";
	private OptionsManager optionsManager;

	public Properties init_prop() {

	String env = System.getProperty("env");

		FileInputStream fis = null;

		try {

			if (env == null) {

				System.out.println("Script execution on : PROD Env");

				fis = new FileInputStream("./src/test/resources/config/prod.properties");
			}

			else {

				System.out.println("=====>Script execution on : " + env);

				switch (env) {
				case "qa":
					fis = new FileInputStream("./src/test/resources/config/qa.properties");
					break;

				case "stage":
					fis = new FileInputStream("./src/test/resources/config/stage.properties");
					break;

				case "dev":
					fis = new FileInputStream("./src/test/resources/config/dev.properties");
					break;

				default:
					System.out.println("Please pass the right environment to execute your code..." + env);
					break;
				}
			}
		}
		catch (Exception e) {

			e.printStackTrace();
		}

		prop = new Properties();
		
		try {

			prop.load(fis);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop;
	}

	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		
		border = prop.getProperty("hightlight");
		
		optionsManager = new OptionsManager(prop);
		
		System.out.println("Browser started=========> " + browserName.toUpperCase());
		
		switch (browserName.toUpperCase()) {
		
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
			
		case "FF":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
			
		default:
			System.out.println("Please pass the right browser name " + browserName);
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}
}