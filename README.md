# TwitterFeedDemo

**About the framework**
1. This is a Rest Assured API testing framework using Maven, TestNG which will make the Get call to a public API (juicer.io) which in turn will revert the data from twitter feed.
2. Input data is read from Excel file using TestNG Data Provider. However, same can also be provided using JSON file.
3. Implemented Surefire and Extent reports. Sample Extent Report is also added to the repo. 
4. Logging is done using log4j.

**Prerequisite to execute the test cases**
1. [JDK](https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html#license-lightbox) and [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/) must be installed and set in classpath (install the packages supported by the local machine OS) on the machine before running this project.

**Steps to execute test cases**
1. Take the checkout of master branch into local machine.
   1. In case not able to take checkout, download the zip file of repo from **Code > Download zip** option and unzip the same.
2. Navigate to root directory (tweeter-feed-api) and execute the following command: **mvn clean install test**
3. All test should pass and report can be accessed at **<root-directory/test-output/ExtentReport.html**
