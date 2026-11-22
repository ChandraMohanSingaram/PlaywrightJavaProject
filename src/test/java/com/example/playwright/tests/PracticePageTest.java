package com.example.playwright.tests;

import com.example.playwright.pages.PracticePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticePageTest extends BaseTest {
/**
     * Test to verify that submitting a name with Option 2 works correctly.
     */
    @Test
    public void shouldSubmitNameWithOptionTwo() {
        String optionValue = "2";
        String optionLabel = "Option 2";
       // String name = "Taylor";
        String name = "Priya"; // Updated name to "Priya" for testing
        PracticePage practicePage = new PracticePage(page);
        practicePage.open();
        practicePage.submitSelection(optionValue, name);

        String resultMessage = practicePage.getResultMessage();
       
       Assert.assertTrue(resultMessage.contains(name), "Result message should contain the submitted name");
       
        Assert.assertTrue(resultMessage.contains(optionLabel), "Result message should confirm Option 2 was selected");
    }

    @Test
    public void shouldSubmitNameWithOptionThree() {
        String optionValue = "3";
        String name = "Jordan";
        PracticePage practicePage = new PracticePage(page);
        practicePage.open();
        practicePage.submitSelection(optionValue, name);

        String resultMessage = practicePage.getResultMessage();

        Assert.assertEquals(resultMessage, "Submitted: " + name + " chose Option 3",
                "Result message should confirm the submitted name and option");
    }
}