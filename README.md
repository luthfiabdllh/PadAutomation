# PadAutomation

PadAutomation is a web automation testing project using Java, Selenium WebDriver, Cucumber, and ExtentReports.

## Features
- Web automation testing with Selenium
- Test scenarios written in Cucumber (Gherkin)
- Automated test reporting with ExtentReports
- Modular structure (Page Object Model)

## Project Structure

```
src/
 ├── main/java/pages/                # Page Object classes
 ├── test/java/runners/              # Cucumber test runners
 ├── test/java/stepsDefinition/      # Cucumber step definitions
 ├── test/java/utils/                # Utilities (ExtentReport, Screenshot, etc)
 └── test/resources/features/        # Cucumber feature files (.feature)
```

## Prerequisites
- Java 22
- Maven 3.x
- Chrome/Firefox browser & corresponding WebDriver

## Installation

1. Clone this repository:
   ```
   git clone <repo-url>
   cd PadAutomation
   ```
2. Install dependencies:
   ```
   mvn clean install
   ```

## Running Tests

Run all tests with:
```
mvn test
```
Test reports can be found at:
- `test-output/ExtentReport.html`
- `target/cucumber-report.html`

## Documentation
Test Suite Documentation can be found at:
- `Kelompok 21 2025 - Test Suite & Test Case utk automation & manual.pdf`

## Contribution

1. Fork this repository
2. Create a feature branch (`git checkout -b new-feature`)
3. Commit your changes (`git commit -am 'Add feature'`)
4. Push to the branch (`git push origin new-feature`)
5. Create a Pull Request

## License

This project is licensed under the MIT License.

