package com.example.playwright.tests;

import com.microsoft.playwright.*;
import com.example.playwright.pages.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Sample Playwright Test using Page Object Model
 */
public class PlaywrightTestWithPageObject {
    
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private GoogleHomePage googleHomePage;
    
    @BeforeMethod
    public void setUp() {
        // Initialize Playwright and Browser
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        
        // Initialize GoogleHomePage
        googleHomePage = new GoogleHomePage(page);
    }
    
    @AfterMethod
    public void tearDown() {
        // Close page and browser
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
    
    @Test
    public void shouldOpenGoogleHomePage() {
        // Using PageObject
        googleHomePage.openGoogle();
        
        // Verify Google homepage is loaded
        Assert.assertTrue(googleHomePage.isGoogleHomePageLoaded(),"Google homepage did not load properly");
        
        // Verify page title contains Google
        Assert.assertTrue(googleHomePage.getGooglePageTitle().contains("Google"),"Google homepage title not found");
        
        // Verify search box is visible
        Assert.assertTrue(googleHomePage.isSearchBoxVisible(), "Search box is not visible on Google homepage");
        
        System.out.println("✓ Google homepage loaded successfully!");
        System.out.println("  Page Title: " + googleHomePage.getGooglePageTitle());
        System.out.println("  Page URL: " + googleHomePage.getPageUrl());
    }
    
    @Test
    public void shouldSearchOnGoogle() {
        // Open Google homepage
        googleHomePage.openGoogle();
        Assert.assertTrue(googleHomePage.isGoogleHomePageLoaded(),"Google homepage did not load");
        
        // Search for a term
        String searchTerm = "Playwright Java";
        googleHomePage.searchForTerm(searchTerm);
        
        // Wait for results to load
        googleHomePage.wait(2000);
        
        // Verify search was performed
        String currentUrl = googleHomePage.getPageUrl();
        Assert.assertTrue(currentUrl.contains("q="), "Search query not found in URL");
        
        System.out.println("✓ Search completed successfully!");
        System.out.println("  Search Term: " + searchTerm);
        System.out.println("  Result URL: " + currentUrl);
    }
}