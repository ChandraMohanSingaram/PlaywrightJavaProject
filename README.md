# Playwright Java Automation Framework

> A hands-on Java test automation project demonstrating Playwright, TestNG, and the Page Object Model for reliable browser testing.

[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://openjdk.org/)
[![Playwright](https://img.shields.io/badge/Playwright-1.62.0-45ba4b.svg)](https://playwright.dev/java/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red.svg)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-C71A36.svg)](https://maven.apache.org/)

## At a Glance

- Browser automation with Microsoft Playwright for Java
- Maintainable Page Object Model with reusable base classes
- TestNG lifecycle management for browser setup and cleanup
- Local practice application for deterministic form testing
- Google UI smoke coverage for real-world browser navigation
- Maven and TestNG configuration for repeatable command-line execution
- Extent Reports integration prepared for rich HTML reporting

## What This Project Demonstrates

| Capability | Implementation |
|---|---|
| Browser automation | Chromium launches in headless or headed mode through Playwright |
| Page Object Model | `BasePage`, `GoogleHomePage`, and `PracticePage` separate UI actions from test logic |
| Stable locators | Page objects centralize `data-testid`, ID, name, and accessible attributes |
| Explicit waits | Practice-page assertions wait for dynamically rendered result content |
| Test isolation | Each TestNG method receives a fresh browser, page, and Playwright instance |
| Maven execution | The Surefire plugin runs the suite declared in `testng.xml` |
| Assertions | TestNG validates page state, submitted values, options, and navigation |

## Tech Stack

- **Language:** Java 11
- **Browser Automation:** Playwright for Java 1.62.0
- **Test Framework:** TestNG 7.10.2
- **Build Tool:** Apache Maven
- **Reporting:** TestNG reports plus Extent Reports 5.1.1
- **Browser:** Chromium

## Project Structure

```text
src/
├── main/java/com/example/playwright/pages/
│   ├── BasePage.java
│   ├── GoogleHomePage.java
│   └── PracticePage.java
└── test/
    ├── java/com/example/playwright/
    │   ├── reports/
    │   │   └── ExtentTestNGListener.java
    │   └── tests/
    │       ├── BaseTest.java
    │       ├── PracticePageTest.java
    │       └── PlaywrightTestWithPageObject.java
    └── resources/
        └── practice-page.html

pom.xml
testng.xml
```

## Test Coverage

The active Maven suite contains four tests:

- `shouldSubmitNameWithOptionTwo`
- `shouldSubmitNameWithOptionThree`
- `shouldOpenGoogleHomePage`
- `shouldSearchOnGoogle`

The local practice page verifies dropdown and name submission end to end. Google tests demonstrate page loading, search-box interaction, title validation, and URL-based result verification.

Additional examples for basic Bing automation, multi-user browser sessions, and experimental AI-assisted locator recovery are retained in the source tree for learning purposes.

## Getting Started

### Prerequisites

- JDK 11 or newer
- Maven 3.8 or newer
- Internet access for Playwright-dependent Google tests

### Install Browser Binaries

```bash
mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
```

### Run the Active Suite

```bash
mvn clean test
```

### Run a Single Test Class

```bash
mvn clean test -Dtest=PracticePageTest
```

```bash
mvn clean test -Dtest=PlaywrightTestWithPageObject
```

### Run a Single Test Method

```bash
mvn clean test -Dtest=PracticePageTest#shouldSubmitNameWithOptionThree
```

## Reports

After a Maven run, inspect:

- `target/testng-reports/` for TestNG HTML and XML results
- `target/surefire-reports/` for Surefire output
- `test-output/ExtentReport.html` after enabling the Extent listener

The Extent listener is implemented but currently commented out in `testng.xml`.

## Architecture

```text
TestNG test
    |
    | uses
    v
Page Object  -->  BasePage  -->  Playwright Page
    |
    | returns state or performs actions
    v
TestNG assertions
```

Page objects own page-specific locators and workflows. Tests own assertions and scenarios. `BaseTest` owns Playwright, browser, page, and teardown lifecycle management.

## Test Status Snapshot

A local verification run with `mvn test` produced:

- **Tests:** 4
- **Passed:** 3
- **Failed:** 1

The Google search test currently has an external-UI-dependent URL assertion that did not observe `q=` in the captured URL. This is visible in the TestNG report and is a useful example of why network and third-party UI changes should be monitored.

## Roadmap

The framework can be extended with:

- CI/CD execution through GitHub Actions or Azure Pipelines
- Browser and operating-system matrices
- Trace, video, and screenshot capture on failure
- Retry and self-healing policy with clear, non-magic fallbacks
- Environment-specific configuration
- Allure or always-on Extent reporting
- Expanded API and visual regression coverage

## License

This project is intended for learning and portfolio demonstration.
