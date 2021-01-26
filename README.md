# restassured-api-testframework

API Back-end automation framework using restassured , TestNG test runner , allure reporting , and maven build tool.

1.  Install **Maven** and impot maven project into your intellij or eclipse.
2.  Install allure and testng plugins to the IDE you are using. 
3. **Framework Structure:**
     - **_base:_** Base methods like http validadtions and configuration methods. 
     - **_httprequests:_** Everything regarding a HTTP request i.e., every type of call, header and endpoint builder.
     - **_tests:_** Contains all the test case files. 
     - **_utils:_** Contains utils like file reader, json inturpreter. 
     
4.  **How to execute the project:**
    - After prjoct import is done succesfully, goto terminal/cmd and cd to the project directory where pom.xml is placed.
    - Run command **$ mvn clean test**. This will execute the tests. 
    - To genrate the report run command **$ mvn allure:report**.
    - Report would be available at 'project:dir/target/site/allure-maven-plugin/index.html <br />
        like for me it is : /Users/aman/git/assignmentVL/target/site/allure-maven-plugin/index.html
