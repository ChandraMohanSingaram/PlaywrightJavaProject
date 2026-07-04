package com.example.playwright.tests;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import java.net.http.*;
import java.net.URI;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.*; // For JSON parsing
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AIEnhancedSelfHealingTest {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    // Simulated method to call AI service for locator suggestions
    private static String getLocatorFromAI(String elementDescription) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String requestBody = "{\"description\": \"" + elementDescription + "\"}";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://your-ai-service/api/getLocator"))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        // Parse JSON response to extract locator
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        return jsonResponse.get("locator").getAsString();
    }

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            try { browser.close(); } catch (Exception ignored) {}
        }
        if (playwright != null) {
            try { playwright.close(); } catch (Exception ignored) {}
        }
    }

    @Test
    public void aiEnhancedSelfHealingTest() throws Exception {
        // Navigate to Wikipedia which contains an input with id 'searchInput'
        page.navigate("https://www.wikipedia.org/");

        String elementDescription = "search input field on homepage";
        String locator = null;

        try {
            // Try primary locator first
            Locator primaryLocator = page.locator("#searchInput456"); // Intentionally incorrect
            if (primaryLocator.count() > 0) {
                locator = "#searchInput";
                System.out.println("Primary locator succeeded.");
            } else {
                // If primary fails, call AI service for locator
                locator = getLocatorFromAI(elementDescription);
                System.out.println("AI service locator succeeded.");
            }
        } catch (Exception e) {
            // On error, fallback to AI locator
            locator = getLocatorFromAI(elementDescription);
        }

        if (locator == null || locator.isEmpty()) {
            throw new RuntimeException("No valid locator found for element: " + elementDescription);
        }

        Locator effectiveLocator = page.locator(locator);
        effectiveLocator.fill("Playwright AI-enhanced test");
        effectiveLocator.press("Enter");

        // Assert the page title contains 'Wikipedia'
       // PlaywrightAssertions.assertThat(page).hasTitle(title -> title.contains("Wikipedia"));
    }
}