# GitHubChallenge

A Standalone Java Application named GitHubHomeTest developed using:  
* Apache Maven
* StringUtils Library for Standard String operations
* JDK 1.8 Library
* Junit 4.11 Library for Unit Test Cases

#### Refer to [Wiki](https://github.com/nishantmittal1989/GitHubChallenge/wiki/) pages for application details.

## How to run the application  
There are two ways to run this application, through the pre-built [jar](https://github.com/nishantmittal1989/GitHubChallenge/tree/master/Deliverables/GitHubHomeTest-1.0.0-SNAPSHOT.jar) or by importing the project in your choice of IDE.  

### Pre-requisites
* JRE 1.8
* Maven Version 3.0 or higher  
* CSV File on your local drive location. Download the CSV file or have your own CSV file and place it in any of the directory structure on your local system. The Sample .csv file is present [here](https://github.com/nishantmittal1989/GitHubChallenge/tree/master/src/main/resources/Data.csv)

Note: If you are using your own CSV file, please make sure to have the same template/format as that of the Sample Data.csv file.

### Through Jar  
1. Download the Jar file [GitHubHomeTest-1.0.0-SNAPSHOT.jar](https://github.com/nishantmittal1989/GitHubChallenge/tree/master/Deliverables/GitHubHomeTest-1.0.0-SNAPSHOT.jar)
2. Run the jar file using below command:
```
java -jar GitHubHomeTest-1.0.0-SNAPSHOT.jar "fileLocation/fileName"
```
* pass fileLocation as the location of the CSV file(stored on your local system drive) and fileName as the name of the csv file as argument to java jar statement.

Example: (Run the command on cmd for Windows and on Terminal for Mac OS)
java -jar GitHubHomeTest-1.0.0-SNAPSHOT.jar "user/testuser/data/Sample.csv"

Here fileLocation = user/testuser/data and fileName = Sample.csv
***  

### By Importing in IDE  
Below steps are for Eclipse IDE  
1. Download the project and unzip in local system.
2. Open eclipse.
3. Click File > Import.
4. Type Maven in the search box under Select an import source.
5. Select Existing Maven Projects.
6. Click Next.
7. Click Browse and select the folder that was unzipped in step 1 (contains the pom.xml file)
8. Click Next.
9. Click Finish.  
10. Modify the config.properties file(present in /GitHubHomeTest/src/main/resources/) to include the location of the Sample .csv file against the key 'FileLocation'
* The config.properties file should look like this below:
```
FileLocation=/GitHubHomeTest/src/main/resources/Data.csv
```

* /GitHubHomeTest/src/main/resources/Data.csv is the Sample value(location of the file on local system drive) against the key FileLocation. Please don't consider this value as the real file location. Please make sure NOT TO CHANGE THE KEY and to CHANGE ONLY THE VALUE as per the location of the file on your local system. You can also download the Sample Data.csv file from [here](https://github.com/nishantmittal1989/GitHubChallenge/tree/master/src/main/resources/Data.csv)
11. Run Application.java present under com.github.main package as Java Application.
12. Results will be printed on Console.

Note: .csv is the only valid extension of the file that can be used for this project. 
***  

## Future work / ToDo  
- [ ] Make the Build more flexible to include more parameters
- [ ] Scale the soultion to include parallel thread processing to parse huge files    
- [ ] Add more JUnit testing for io and logic packages
- [ ] Build UI to show results