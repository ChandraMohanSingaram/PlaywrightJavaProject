package com.example.playwright.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Path;

public class PracticePage extends BasePage {

    private static final String OPTION_DROPDOWN = "[data-testid='dropdown']";
    private static final String NAME_INPUT = "[data-testid='name-input']";
    private static final String SUBMIT_BUTTON = "[data-testid='submit-btn']";
    private static final String STATUS_MESSAGE = "[data-testid='status']";

    public PracticePage(Page page) {
        super(page);
    }

    public void open() {
        Path practicePage = Path.of("src", "test", "resources", "practice-page.html");
        navigateTo(practicePage.toUri().toString());
    }

    public void submitSelection(String optionValue, String name) {
        getElement(OPTION_DROPDOWN).selectOption(optionValue);
        fillText(NAME_INPUT, name);
        clickElement(SUBMIT_BUTTON);
    }

    public String getResultMessage() {
        getElement(STATUS_MESSAGE).waitFor(
                new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return getText(STATUS_MESSAGE);
    }
}