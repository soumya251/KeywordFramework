# Keyword Driven Automation Framework

##  Project Overview
This is a Maven-based Keyword Driven Automation Framework developed using Java, Selenium WebDriver, and TestNG.

The framework is designed to automate web applications using reusable keywords and utility methods. It supports data-driven testing using Excel files and generates detailed execution reports using Extent Reports.

The main objective of this framework is to reduce code duplication, improve maintainability, and make automation execution more scalable and reusable.

---

#  Technologies Used

- Java
- Selenium WebDriver
- Maven
- TestNG
- Apache POI
- Extent Reports
- Eclipse IDE

---

#  Project Structure

```bash
KeywordFramework
│
├── src/main/java
│   │
│   ├── com.framework.driver
│   │   └── Browser driver initialization and setup
│   │
│   ├── com.framework.engine
│   │   └── Framework execution engine
│   │
│   ├── com.framework.keywords
│   │   ├── ActionKeywords.java
│   │   └── ReusableFunctions.java
│   │
│   ├── com.framework.reporting
│   │   └── Extent report generation
│   │
│   └── com.framework.utils
│       └── Utility and helper classes
│
├── src/main/resources
│   └── Framework resource files
│
├── src/test/java
│   │
│   └── com.framework.runner
│       └── TestRunner.java
│
├── src/test/resources
│   ├── config.properties
│   └── testdata.xlsx
│
├── reports
│   └── ExtentReport.html
│
├── screenshots
│   └── Failure screenshots
│
└── pom.xml
```

---

# 🚀 Framework Features

✅ Keyword Driven Automation Framework

✅ Reusable Functions and Keywords

✅ Data Driven Testing using Excel

✅ Cross Browser Support

✅ Screenshot Capture on Failure

✅ Extent Report Integration

✅ Maven Dependency Management

✅ Scalable and Maintainable Structure

✅ Easy Test Execution

---

# ⚙️ Prerequisites

Before running the project, make sure the following are installed:

- Java JDK 8 or above
- Eclipse IDE
- Maven
- Chrome Browser
- Git

---

# ▶️ How to Run the Project

## Step 1: Clone the Repository

```bash
git clone <your-github-repository-link>
```

## Step 2: Import Project

- Open Eclipse
- Click on:
  File → Import → Existing Maven Project
- Select the project folder

## Step 3: Update Maven Dependencies

Right Click Project → Maven → Update Project

## Step 4: Run Test Execution

Run the below file:

```bash
TestRunner.java
```

---

# 📊 Reporting

After successful execution, Extent Reports will be generated inside:

```bash
/reports/ExtentReport.html
```

The framework also captures screenshots for failed test cases.

---

# 📁 Test Data Management

Test data is maintained inside:

```bash
src/test/resources/testdata.xlsx
```

Configuration settings are maintained inside:

```bash
src/test/resources/config.properties
```

---

# 🔥 Future Enhancements

- Jenkins CI/CD Integration
- Docker Support
- Parallel Execution
- Allure Reporting
- Database Testing Support
- API Automation Integration

---

# 👨‍💻 Author

Soumya Sushree Tarai

Automation Test Engineer | Java | Selenium | TestNG | Maven

---

# 📌 Note

This framework is developed for learning and automation testing purposes.  
It can be enhanced further for enterprise-level automation solutions.
