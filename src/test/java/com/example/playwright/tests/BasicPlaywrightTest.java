package com.example.playwright.tests;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicPlaywrightTest {
    @Test
    public void shouldOpenBingHomePage() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://www.bing.com");
            Assert.assertTrue(page.title().contains("Bing"), "Bing homepage title not found");
            page.waitForTimeout(3000); // Playwright-based pause for 3 seconds
            browser.close();
        }
    }
}