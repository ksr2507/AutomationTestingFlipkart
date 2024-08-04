
# Flipkart Automation with Selenium and TestNG

This project automates the search and filtering of products on Flipkart using Selenium WebDriver and TestNG. The script performs the following steps:

* Opens Flipkart.com
* Searches for "Samsung Galaxy S10"
* Filters results to show only mobile phones
* Applies filters for brand (Samsung) and Flipkart Assured
* Sorts results by price (high to low)
* Collects and displays product details from page 1


### Key Components
* __src/test/java/Flipkart/FlipkartTestLocal__: Contains the main test class with Selenium WebDriver logic and TestNG annotations for running a automation test on single browser locally.
* __src/test/java/Flipkart/FlipkartTestParallel__: Contains the main test class with Selenium WebDriver logic and TestNG annotations for running a automation test on 5 OS/Browser combinations parallely using browserstack.
* __src/test/resources__ : Holds configuration files and WebDriver executables.
* __testng.xml__ : Configuration file for TestNG test execution.

### Result
[Video Recording of Results](https://drive.google.com/file/d/1s0y7wSTv4qvT5GLKfbCkEpX7eNrEbUms/view?usp=sharing)
