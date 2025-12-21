# API Automation Framework

## **Overview**
This framework automates REST API testing using **Java**, **Rest-Assured**, and **TestNG**, with optional **Cucumber BDD support**. It supports CRUD operations, positive/negative tests, and integrates with **Jenkins** for CI/CD automation.  

**Key Features:**
- Modular and reusable structure
- Supports CRUD operations for REST APIs
- Positive and negative test scenarios
- Logging and reporting via TestNG and ExtentReports
- Configurable base URL and credentials
- Optional BDD support using Cucumber feature files
- CI/CD integration with Jenkins

---

## **Project Structure**
api-automation-framework/
│
├─ src/main/java/
│ ├─ base/ # BaseTest and utilities
│ ├─ constants/ # API endpoints and config constants
│ ├─ pojo/ # Request/Response POJOs
│
├─ src/test/java/
│ ├─ tests/ # API test classes (CRUD, negative tests)
│
├─ src/test/resources/
│ ├─ config.properties # Base URL, credentials
│ ├─ test-data/ # Optional test data files
│
├─ test-output/ # TestNG/ExtentReports reports
├─ pom.xml # Maven dependencies
└─ README.md # Project documentation


---

## **Prerequisites**
- Java 17 or above  
- Maven  
- IntelliJ IDEA / Eclipse  
- Git  
- Jenkins (optional for CI/CD)  

---

## **Setup Instructions**

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/api-automation-framework.git
cd api-automation-framework
2.**Import project in IDE**

Open IntelliJ IDEA → File → New → Project from Existing Sources → Select pom.xml.

3.**Install dependencies**

mvn clean install


4.**Configure config.properties**

baseUrl=https://petstore.swagger.io/v2
username=your_username
password=your_password


5.**Run Tests**

Using Maven:

mvn test

Using TestNG XML:

mvn test -DsuiteXmlFile=testng.xml


6.**View Reports**

TestNG default report: test-output/index.html
ExtentReports: test-output/ExtentReport.html
-----------------------------------------------
**Jenkins Setup for CI/CD**

1.Install Jenkins from https://www.jenkins.io

2.Install Required Plugins
Maven Integration Plugin
Git Plugin
HTML Publisher Plugin

3.Create Jenkins Job

New Item → Freestyle Project → Enter name → OK
Source Code Management → Git → Repository URL

Build Step → Invoke top-level Maven targets → Goals: clean test

Post-build → Publish HTML reports → Directory: test-output → Index: index.html

4.Run the Job

Click Build Now → View HTML Report from the last build
