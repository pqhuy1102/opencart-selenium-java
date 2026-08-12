# OpenCart Selenium Java Automation

![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk\&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-43B02A?logo=selenium\&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-C71A36?logo=apachemaven\&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-Test%20Framework-red)

UI automation testing project for **OpenCart**, built with **Java, Selenium WebDriver, Maven, and TestNG**.

The project focuses on applying the **Page Object Model (POM)** approach to create reusable and maintainable automated UI tests.

---

## 🛠️ Tech Stack

| Technology             | Purpose                       |
| ---------------------- | ----------------------------- |
| **Java**               | Programming language          |
| **Selenium WebDriver** | Browser automation            |
| **TestNG**             | Test execution & assertions   |
| **Maven**              | Dependency & build management |
| **POM**                | Test automation architecture  |

---

## 🏗️ Project Structure

```text
opencart-selenium-java/
│
├── src/
│   └── test/
│       └── java/
│           ├── pages/
│           ├── tests/
│           └── utils/
│
├── testData/
├── logs/
├── master.xml
├── pom.xml
└── README.md
```

### Main Components

* **Pages** — Page Object classes and reusable UI interactions
* **Tests** — Test scenarios and assertions
* **Utils** — Reusable automation utilities
* **testData** — External test data
* **master.xml** — TestNG test suite configuration
* **pom.xml** — Maven dependencies and project configuration

---

## 🧪 Test Automation Scope

The project is designed to cover key OpenCart user flows, including:

* User Login
* Account Registration
* Product Search
* Product Selection
* Shopping Cart
* Checkout

---

## ▶️ Getting Started

### Prerequisites

* Java JDK 17+
* Maven
* Git
* OpenCart running locally

Verify Java and Maven:

```bash
java -version
mvn -version
```

### Clone Repository

```bash
git clone https://github.com/pqhuy1102/opencart-selenium-java.git
cd opencart-selenium-java
```

### Install Dependencies

```bash
mvn clean install
```

---

## ▶️ Run Tests

Run the TestNG suite:

```bash
mvn test -DsuiteXmlFile=master.xml
```

Or execute `master.xml` directly from your IDE.

---

## 🧩 Framework Approach

The project follows the **Page Object Model** pattern:

```text
Test Case
    │
    ▼
Page Object
    │
    ▼
Selenium WebDriver
    │
    ▼
OpenCart
```

This separates test scenarios from UI implementation and improves:

* Maintainability
* Reusability
* Test readability
* Locator management

---

## 👨‍💻 Author

**Pham Quoc Huy**

Quality Engineer | QA Automation

**Repository:**
https://github.com/pqhuy1102/opencart-selenium-java
