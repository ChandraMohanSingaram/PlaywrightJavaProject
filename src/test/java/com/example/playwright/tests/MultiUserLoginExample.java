package com.example.playwright.tests;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiUserLoginExample {
	@Test
	public void MultiUserLogin() {

		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			// Single context for both users (tabs)
			BrowserContext context = browser.newContext();

			// Tab 1: User 1
			Page page1 = context.newPage();
			page1.navigate("https://www.saucedemo.com");
			page1.waitForTimeout(3000);
			page1.locator("#user-name").fill("standard_user");
			page1.locator("#password").fill("secret_sauce");
			page1.waitForTimeout(7000);
			page1.locator("#login-button").click();
			String title1 = page1.locator(".title").textContent();
			Assert.assertTrue(title1.contains("Products"), "User 1 did not land on Products page");

			// Tab 2: User 2
			Page page2 = context.newPage();
			page2.navigate("https://www.saucedemo.com");
			page2.waitForTimeout(3000);
			page2.locator("#user-name").fill("problem_user");
			page2.locator("#password").fill("secret_sauce");
			page2.waitForTimeout(7000);
			page2.locator("#login-button").click();
			String title2 = page2.locator(".title").textContent();
			Assert.assertTrue(title2.contains("Products"), "User 2 did not land on Products page");

			// Close context and browser
			context.close();
			browser.close();
		}
	}
}