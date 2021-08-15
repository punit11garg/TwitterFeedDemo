# TwitterFeedDemo

**About the framework**
1. This is a Rest Assured API testing framework using Maven, TestNG which will make the Get call to a public API (juicer.io) which in turn will revert the data from twitter feed.
2. Input data is read from Excel file using TestNG Data Provider. However, same can also be provided using JSON file.
3. Implemented Surefire and Extent reports. Sample Extent Report is also added to the repo. 
4. Logging is done using log4j.

**Prerequisite to execute the test cases**
1. Install & configure Java and Maven using the following links as per the OS.
   1. For windows:
      1. [Java 8](https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html) and [set classpath](https://mkyong.com/java/how-to-set-java_home-on-windows-10/)
      2. [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/)
    2. For Mac
       1. [Java 8](https://mkyong.com/java/how-to-install-java-on-mac-osx/)
       2. [Maven](https://mkyong.com/maven/install-maven-on-mac-osx/)

**Steps to execute test cases**
1. Take the checkout of master branch into local machine.
   1. In case not able to take checkout, download the zip file of code from **"Code > Download zip"** and unzip.
2. Open Terminal/Command Prompt and navigate to root directory (**"twitter-feed-api"**) of the downloaded code on local machine. Execute the following command to execute the test cases: **"mvn clean install test"**
3. All test should pass and report can be accessed at **"twitter-feed-api/test-output/ExtentReport.html"**
