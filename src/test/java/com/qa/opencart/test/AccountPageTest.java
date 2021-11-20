package com.qa.opencart.test;

	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import com.qa.opencart.utils.ConstantUtils;

	public class AccountPageTest extends BaseTest {

		@BeforeClass
		public void accountsPageSetup() {

			accountsPage = loginPage.loginOpenCart(prop.getProperty("username"), prop.getProperty("password"));

		}

		@Test
		public void accountPageUrlTest() {

			Assert.assertEquals(accountsPage.getaccountPageUrl(), ConstantUtils.ACCOUNTS_PAGE_URL);
		}

		@Test
		public void accountPageHeadersTest() {

			Assert.assertEquals(accountsPage.getaccountPageHeaders(),
					excelUtils.readDataFromExcel(ConstantUtils.ACCOUNT_HEADER_SHEETNAME));

		}

		@Test
		public void accountsPageFootersTest() {

			System.out.println(accountsPage.getaccountPageFooters());

			System.out.println(excelUtils.readDataFromExcel(ConstantUtils.ACCOUNT_FOOTER_SHEETNAME));

			Assert.assertEquals(accountsPage.getaccountPageFooters(),
					excelUtils.readDataFromExcel(ConstantUtils.ACCOUNT_FOOTER_SHEETNAME));
		}

		@Test
		public void accountsPageList() {

			Assert.assertTrue(accountsPage.getaccountPageList()
					.containsAll(excelUtils.readDataFromExcel(ConstantUtils.ACCOUNT_PAGE_LIST)));

		}
}
