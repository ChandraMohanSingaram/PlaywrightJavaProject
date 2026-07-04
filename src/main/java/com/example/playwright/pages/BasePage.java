package com.example.playwright.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

/**
 * BasePage class - Base class for all Page Objects
 * Contains common actions that can be performed on any page
 */
public class BasePage {
    
    protected Page page;
    
    /**
     * Constructor to initialize the page object
     * @param page The Playwright Page object
     */
    public BasePage(Page page) {
        this.page = page;
    }
    
    /**
     * Navigate to a given URL
     * @param url The URL to navigate to
     */
    public void navigateTo(String url) {
        page.navigate(url);
        waitForPageLoad();
    }
    
    /**
     * Wait for page to load completely
     */
    public void waitForPageLoad() {
        page.waitForLoadState();
    }
    
    /**
     * Get the current page title
     * @return The page title
     */
    public String getPageTitle() {
        return page.title();
    }
    
    /**
     * Get the current page URL
     * @return The page URL
     */
    public String getCurrentUrl() {
        return page.url();
    }
    
    /**
     * Get page content
     * @return The HTML content of the page
     */
    public String getPageContent() {
        return page.content();
    }
    
    /**
     * Find an element by locator
     * @param locator The locator string (CSS or XPath)
     * @return The Locator object
     */
    public Locator getElement(String locator) {
        return page.locator(locator);
    }
    
    /**
     * Click on an element
     * @param locator The locator string
     */
    public void clickElement(String locator) {
        page.locator(locator).click();
    }
    
    /**
     * Fill text in an input field
     * @param locator The locator string
     * @param text The text to fill
     */
    public void fillText(String locator, String text) {
        page.locator(locator).fill(text);
    }
    
    /**
     * Get text from an element
     * @param locator The locator string
     * @return The text content
     */
    public String getText(String locator) {
        return page.locator(locator).textContent();
    }
    
    /**
     * Wait for an element to be visible
     * @param locator The locator string
     */
    public void waitForElement(String locator) {
        page.locator(locator).waitFor();
    }
    
    /**
     * Check if an element is visible
     * @param locator The locator string
     * @return true if visible, false otherwise
     */
    public boolean isElementVisible(String locator) {
        try {
            return page.locator(locator).isVisible();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Take a screenshot of the page
     * @param filename The filename to save the screenshot
     */
    public void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions().setPath(
                java.nio.file.Paths.get("screenshots/" + filename)));
    }
    
    /**
     * Wait for a specific amount of time
     * @param milliseconds The time to wait in milliseconds
     */
    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Close the page
     */
    public void closePage() {
        if (page != null) {
            page.close();
        }
    }
}
