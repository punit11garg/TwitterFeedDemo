# TwitterFeedDemo
1. This is a Rest Assured API testing framework using Maven, TestNG which will make the Get call to a public API (juicer.io) which in turn will revert the data from twitter feed.
2. Input data is being provided from Excel file using TestNG Data Provider. However, same can also be provided using json files.
3. Surefire reports are implemented. Extent reports can also be implementd if need to attach screenshots for any UI part
4. logging is done using log4j.

**Prerequisite**
Java (8 or above), Maven must be installed on the machine before running this project.

**Commnd to execute test cases**
1. Take the checkout of master branch into local machine.
2. Navigate to root directory (tweeter-feed-api) and execute : **mvn clean install test**
3. All test should pass and report can be accessed at **<root-directory/target/surefire-reports/index.html**
