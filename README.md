
        Framework:
        1. Created a New > Project > (Make sure 'Maven' is selected) give a folder name > Done
            This creates a Java project with Maven (Maven is very useful, as we can add all dependencies in pom.xml like RestAssured/Cucumber/TestNG/JUnit/htmlReporting)
        2. Creating a Maven Project will automatically give src/main/java and src/test/java folders
        3. All test related java files and feature files go under src/test/java
            feature files, Step definition files, Utility files, Test Runner files
        4. All indirectly test related java files go under main/java
            POJO files, utility files can come here as well
        5. What we have done here: (In Order)

            1. Created features folder > Gherkin steps in feature file
            2. Created steps folder > Step definitions for feature file (Can create Page folder > for Page steps)
            3. Created cucumber.Options folder > TestRunner java file.
                features file, tag details go here. Running this file executes that particular scenario.
            4. Created resources folder, Utils folder, POJO folder.. added all required java files and calling them from stepdefs.
            5. Added html reporting build in pom.xml (Details are mentioned below)

         6. Important files created that are called in java files.. APIdetails (enum), global.properties, Hooks
       



        1. Running tests from CMD >
            Go to CMD > cd C:\Users\rpooj\IdeaProjects\RestAssuredCucumberBDD
            When on above path in cmd > mvn test
            This will run all tests
        2. Running tagged tests
            mvn test -Dcucumber.options="--tags @AddPlace"
            Above command will run all the tests tagged in feature files, just as we give them in TestRunner.java
        3. Generating Reports
            Maven cucumber reporting > Search in mavenrpository.com
            There's a homepage url: https://github.com/damianszczepanik/maven-cucumber-reporting

            >Add code in above url in pom.xml, before dependencies
            >In TestRunner.java > @CucumberOptions > Add plugin = "json:target/jsonReports/cucumber-report.json"
            >We should use 'verify' command as mentioned in above github url to generate reports after test
            >mvn test verify or mvn test verify -Dcucumber.options="--tags @DeletePlace"
            >Running above will automatically create required folders under target folder.
            Sometimes it might show report generation failure as expected folder is not present > Manually add the specified folder in the Traget folder
            >Then if you run again, html reports will be generated in cucumber-html-reports folder


        Continuous Integration - Jenkins
            1. https://www.jenkins.io/download/ > Download Generic Java Package (.war) file
            2. cmd prompt > cd C:\Users\rpooj
            3. Run java -jar jenkins.war -httpPort=8080 > Logs will have admin password
            4. http:localhost/8080 > User ID: admin; Password: from logs
            5. Followed all steps > Have Reset password to 1712
            6. New Item > Give details of your project > General > Advanced > Custom workspace > C:\Users\rpooj\IdeaProjects\RestAssuredCucumberBDD
                If we have a GIT project > Select GIT and give repo details
            7. Build Steps > Invoke high level maven targets > Can pass Maven commands here like: test verify or test verify -Dcucumber.options="--tags @DeletePlace"
                Now, Save and when you click build option on left panel, it will run whatever maven command you passed above
            8. General > This project is parameterized option
                Choice Parameter > Name - tag > choices - AddPlace/DeletePlace/E2E
                Now change Build maven command to > mvn test verify -Dcucumber.options="--tags @"$tag""
                Build option will be changed to 'Build with Parameter' > Can choose any tag to execute and build


        GIT:

        1. Created a repo on GIThub - https://github.com/sreeharshagummala-art/AutomationProject1
         repo - https://github.com/sreeharshagummala-art/AutomationProject1.git
        2. Open cmd
                git config --global user.name "Harsha"
                git config --global user.email "sreeharshagummala@gmail.com"
                cd GIT project (cd C:\Users\rpooj\IdeaProjects\GIT project)
                git init (Initialize git repo)
                Stash/Stage - git add * (Will add everything. If not, give git add pathOfFile)
                status - git status (Check what all files are stashed)
                Commit - git commit -m "First Commit"(Commit will commit all files which are stashed or in staging)
                Connect to github repo - git remote add origin https://github.com/sreeharshagummala-art/AutomationProject1.git
                Push - git push - git push -u origin master


         */
    }
}
