# Page Object Model Implementation

## Overview
The project has been upgraded with a Page Object Model (POM) pattern, which is a design pattern used in test automation to maintain a separation between test logic and page-specific code.

## Architecture

### 1. BasePage Class
**Location:** `src/main/java/com/example/playwright/pages/BasePage.java`

The `BasePage` class is the foundation of all page objects. It contains:

#### Common Methods:
- **navigateTo(String url)** - Navigate to a URL
- **waitForPageLoad()** - Wait for page to load completely
- **getPageTitle()** - Get the current page title
- **getCurrentUrl()** - Get the current page URL
- **getPageContent()** - Get page HTML content
- **getElement(String locator)** - Find an element by locator
- **clickElement(String locator)** - Click on an element
- **fillText(String locator, String text)** - Fill text in input fields
- **getText(String locator)** - Get text from an element
- **waitForElement(String locator)** - Wait for element visibility
- **isElementVisible(String locator)** - Check if element is visible
- **takeScreenshot(String filename)** - Take page screenshot
- **wait(int milliseconds)** - Wait for specified time
- **closePage()** - Close the page

### 2. GoogleHomePage Class
**Location:** `src/main/java/com/example/playwright/pages/GoogleHomePage.java`

This page object extends `BasePage` and represents the Google homepage. It includes:

#### Locators:
```java
private static final String SEARCH_BOX = "textarea[name='q']";
private static final String SEARCH_BUTTON = "button[name='btnK']";
private static final String GOOGLE_LOGO = "img[alt='Google']";
private static final String GOOGLE_SEARCH_RESULTS = "div#search";
```

#### Methods:
- **openGoogle()** - Navigate to Google homepage
- **isGoogleHomePageLoaded()** - Verify Google homepage is loaded
- **getGooglePageTitle()** - Get Google page title
- **searchForTerm(String searchTerm)** - Perform search on Google
- **isSearchBoxVisible()** - Check if search box is visible
- **isGoogleLogoVisible()** - Check if Google logo is visible
- **getPageUrl()** - Get current page URL

### 3. Updated Test Class
**Location:** `src/test/java/com/example/playwright/tests/SamplePlaywrightTest.java`

The test class now uses the PageObject pattern:

#### Setup/Teardown:
- **@BeforeMethod** - Initialize Playwright, Browser, and Page objects
- **@AfterMethod** - Clean up resources

#### Test Methods:
1. **testOpenGoogleHomePage()** - Verify Google homepage loads correctly
2. **testSearchOnGoogle()** - Perform search and verify results

## Benefits of Page Object Model

1. **Maintainability** - Changes to UI only require updates in page objects
2. **Reusability** - Common methods in BasePage can be reused across all page objects
3. **Readability** - Tests are more readable and self-documenting
4. **Scalability** - Easy to add new page objects for different pages
5. **Reduced Duplication** - Common actions are centralized in BasePage

## Project Structure

```
PlaywrightJavaProject/
├── src/
│   ├── main/java/com/example/playwright/
│   │   └── pages/
│   │       ├── BasePage.java              (Base class)
│   │       └── GoogleHomePage.java        (Google page object)
│   └── test/java/com/example/playwright/
│       └── tests/
│           └── SamplePlaywrightTest.java  (Updated test)
├── pom.xml
└── testng.xml
```

## How to Run Tests

### Run all tests:
```bash
mvn clean test
```

### Run specific test class:
```bash
mvn clean test -Dtest=SamplePlaywrightTest
```

### Run specific test method:
```bash
mvn clean test -Dtest=SamplePlaywrightTest#shouldOpenGoogleHomePage
```

## Adding New Page Objects

To add a new page object:

1. **Create a new class extending BasePage:**
```java
public class NewPage extends BasePage {
    public NewPage(Page page) {
        super(page);
    }
    
    // Define locators
    private static final String ELEMENT = "selector";
    
    // Define page-specific methods
    public void performAction() {
        // Use methods from BasePage
    }
}
```

2. **Use in tests:**
```java
NewPage newPage = new NewPage(page);
newPage.performAction();
```

## Best Practices

1. **Locators** - Keep locators as private static final constants
2. **Methods** - Create descriptive method names that reflect user actions
3. **Wait Handling** - Always use `waitForPageLoad()` after navigation
4. **Error Handling** - Implement try-catch for optional elements
5. **Documentation** - Add JavaDoc comments to all methods

## Example Usage in Tests

```java
@Test
public void testGoogleSearch() {
    GoogleHomePage google = new GoogleHomePage(page);
    
    // Using Page Object methods
    google.openGoogle();
    Assert.assertTrue(google.isGoogleHomePageLoaded());
    
    google.searchForTerm("Playwright Java");
    google.wait(2000);
    
    String url = google.getPageUrl();
    Assert.assertTrue(url.contains("q="));
}
```

## Next Steps

1. Create more page objects for different pages/features
2. Add more test cases using the page objects
3. Implement custom wait strategies
4. Add logging and reporting capabilities
5. Create a utility class for common operations

---

For more information on Page Object Model pattern, refer to:
- [Playwright Documentation](https://playwright.dev/java/)
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
