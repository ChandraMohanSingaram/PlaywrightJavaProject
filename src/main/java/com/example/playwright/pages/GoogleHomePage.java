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
     * Constructor: initialize the Google page object with the current Playwright page.
     * @param page The Playwright Page object
     */
    public GoogleHomePage(Page page) {
        super(page);
    }

    // Command: open the Google homepage.
    /**
     * Open the Google homepage.
     */
    public void openGoogle() {
        navigateTo("https://www.google.com");
    }

    // Command: verify the home page is loaded.
    /**
     * Verify whether the Google homepage is loaded.
     * @return true if the homepage is loaded, false otherwise
     */
    public boolean isGoogleHomePageLoaded() {
        return getPageTitle().contains("Google") && isSearchBoxVisible();
    }

    // Command: get the current Google page title.
    /**
     * Get the title of the Google homepage.
     * @return The page title
     */
    public String getGooglePageTitle() {
        return getPageTitle();
    }

    // Command: search for a term on Google.
    /**
     * Search for a term on Google.
     * @param searchTerm The term to search for
     */
    public void searchForTerm_old(String searchTerm) {
        waitForElement(SEARCH_BOX);
        fillText(SEARCH_BOX, searchTerm);
        page.locator(SEARCH_BOX).press("Enter");
        clickElement(SEARCH_BUTTON);
        waitForPageLoad();
    }

    // Command: search for a term by submitting the query.
    public void searchForTerm(String searchTerm) {
        waitForElement(SEARCH_BOX);
        fillText(SEARCH_BOX, searchTerm);
        page.locator(SEARCH_BOX).press("Enter");
        waitForPageLoad();
    }

    // Command: check whether the Google search box is visible.
    /**
     * Check if the Google search box is visible.
     * @return true if the search box is visible, false otherwise
     */
    public boolean isSearchBoxVisible() {
        return isElementVisible(SEARCH_BOX);
    }

    // Command: check whether the Google logo is visible.
    /**
     * Check if the Google logo is visible.
     * @return true if the logo is visible, false otherwise
     */
    public boolean isGoogleLogoVisible() {
        return isElementVisible(GOOGLE_LOGO);
    }

    // Command: get the current page URL.
    /**
     * Get the current URL of the page.
     * @return The current URL
     */
    public String getPageUrl() {
        return getCurrentUrl();
    }
}
