package com.example.playwright.pages;

import com.microsoft.playwright.Page;

/**
 * GoogleHomePage - Page Object for Google Homepage
 * This class contains all locators and actions specific to Google homepage
 */
public class GoogleHomePage extends BasePage {
    
    // Locators for Google Homepage
    private static final String SEARCH_BOX = "textarea[name='q']";
    private static final String SEARCH_BUTTON = "button[name='btnK']";
    private static final String GOOGLE_LOGO = "img[alt='Google']";
    private static final String GOOGLE_SEARCH_RESULTS = "div#search";
    
    /**
     * Constructor to initialize GoogleHomePage
     * @param page The Playwright Page object
     */
    public GoogleHomePage(Page page) {
        super(page);
    }
    
    /**
     * Navigate to Google homepage
     */
    public void openGoogle() {
        navigateTo("https://www.google.com");
    }
    
    /**
     * Verify if Google homepage is loaded
     * @return true if Google homepage is loaded, false otherwise
     */
    public boolean isGoogleHomePageLoaded() {
        return getPageTitle().contains("Google") && isElementVisible(GOOGLE_LOGO);
    }
    
    /**
     * Get the title of the Google homepage
     * @return The page title
     */
    public String getGooglePageTitle() {
        return getPageTitle();
    }
    
    /**
     * Search for a term on Google
     * @param searchTerm The term to search for
     */
    public void searchForTerm(String searchTerm) {
        waitForElement(SEARCH_BOX);
        fillText(SEARCH_BOX, searchTerm);
        clickElement(SEARCH_BUTTON);
        waitForPageLoad();
    }
    
    /**
     * Check if search box is visible
     * @return true if search box is visible
     */
    public boolean isSearchBoxVisible() {
        return isElementVisible(SEARCH_BOX);
    }
    
    /**
     * Check if Google logo is visible
     * @return true if logo is visible
     */
    public boolean isGoogleLogoVisible() {
        return isElementVisible(GOOGLE_LOGO);
    }
    
    /**
     * Get the current URL of the page
     * @return The URL
     */
    public String getPageUrl() {
        return getCurrentUrl();
    }
}
