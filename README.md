# news-scraper
Project Title : 
----------------
  This project demonstrates a Rest bases search engine for New-Scraper Service.
	
Problem statement:
-------------------
 1.Scrap newspaper articles data from https://www.thehindu.com/archive/
 2. Create a REST service which answers following queries from scrap data:
	a. Search available authors
	b. Search articles based on author name
	c. Search articles based on article title and description

Pre-requisites:
-----------------
Below Softwares/Tools used for the application:
git Clone :
-----------
1. Eclipse :Eclipse Java EE IDE for Web Developers.
            Version: Luna Service Release 2 (4.4.2)
            Build id: 20150219-0600
2. JDK     :jdk1.8.0_131
3. Maven   :Apache Maven 3.3.9
4. Spring Boot :2.1.1.RELEASE
5. MySQL DB    :MySQL Server 5.5
6. MYSQL DB Port, Username/Password : 3306, root/root
7. Application running on : 8012

Project-Structure:
-----------------
 1.scraper.
 
 1.1 scraper-api  : Artifacts for interfaces & bean classes.
     --src
     --target
     --pom.xml
 1.2 scraper-impl : Artifacts for implementation for api interfaces.
     --src
     --target
     --pom.xml
 1.3 scraper-rest :  Artifacts for rest controllers.
     --src
     --target
     --pom.xml
 pom.xml
   
Build & Run The Project:
------------------------

DB Scripts SetUP :
------------------
  1. Open the Mysql editor and execute the scripts  @below location
  1.1 scraper\scraper-impl\src\main\resources\mysql\01.mysql-db-scripts.sql
  1.2 scraper\scraper-impl\src\main\resources\mysql\02.mysql-db-scripts.sql
    
   note:
   ------
    Above scripts are sample data used for this application. The sample data populates Author,Articles and Articles-Author
    tables using the above scripts.
    For testing, one can refer the scripts and populates more data as per the use-case testing.
    
Code SetUp:
------------
1. via Eclipse : 
1.1  Clone the project and import the parent project (scraper) as an existing maven project.
1.2. Build the project using "mvn clean package".
1.3. Run the Springboot APP main class : NewsScraperAPP

2. without eclipse : 1. Clone the project and build the parent project (scraper) using "mvn clean install".
                     2. All the parent and child projects should get built successfully.
		     3. Run the spring boot app : NewsScraperAPP

Testing
-------------------

The Testing has been performed using Advanced Rest Client utility.
------------------------------------------------------------------
Following End Points are supported:
1. http://localhost:8082/queryAuthor      (method : POST, content-type: application/json)
2. http://localhost:8082/queryArticles    (method : POST, content-type: application/json)
3. http://localhost:8082/searchNews       (method : POST, content-type: application/json)
4. http://localhost:8082/searchData?newsdata=<searchTxt1>,<searchTxt2>,<searchTxt3>.... ,<searchTxtN> (method : GET, content-type: application/json)

http://localhost:8082/queryAuthor:  
---------------
1.This rest end point can be used to search available author by means of applying seacrh filter against name attribute of an author.
  It can consists of 1..* many such criteria filter and displays the authors with articles owned by them.

2. (method : POST, content-type: application/json).
3. sample payload: {"criterias":[{"criterianame":"name","value":"Amitabh","valueList":null}]}
4. Description   :  It consists of 1..* criiterias for author name search:
                {"criterias":[{"criterianame":"name","value":"<<text1>>","valueList":null},
	                      {"criterianame":"name","value":" <<text2>>","valueList":null},
	                      .............................
	                      {"criterianame":"name","value":"<<textN>>","valueList":null}]}
	
http://localhost:8082/queryArticles:
------
1. This rest end point can be used to search available articles by means of applying seacrh filter against title and description  
   attributes of an Articles.
   It can consists of 1..* many such criteria filter and displays the articles alongwith associated authors.
   
2. (method : POST, content-type: application/json).
3. sample payload: {"criterias":[{"criterianame":"title","value":"River","valueList":null}]}
4. Description   :  It consists of 1..* criiterias for author name search:
                {"criterias":[{"criterianame":"title","value":"River","valueList":null}, 
		              {"criterianame":"description","value":"novel","valueList":null},
			      ........................................
			      {"criterianame":"title","value":"hungry","valueList":null}]}


http://localhost:8082/searchNews:
------
1. This rest end point can be used to search available articles or authors by means of applying seacrh filter against title and 
   description attributes of an Articles and name attribute against Authors.
   It can consists of 1..* many such criteria filter and displays the articles alongwith associated authors.
   It consists mix of criterias used for author and articles.

2. (method : POST, content-type: application/json).
3. sample payload: {"criterias":[{"criterianame":"title","value":"River","valueList":null},
                              {"criterianame":"description","value":"novel","valueList":null},
			      {"criterianame":"name","value":"Rahul","valueList":null}]}

4. Description   :  It consists of 1..* criiterias for author name search:
                {"criterias":[{"criterianame":"title","value":"River","valueList":null}, 
		              {"criterianame":"description","value":"novel","valueList":null},
			      ........................................
			      {"criterianame":"title","value":"hungry","valueList":null}]}

http://localhost:8082/searchData?newsdata=<searchTxt1>,<searchTxt2>,<searchTxt3>.... ,<searchTxtN>:
-----------
1. (method : GET, content-type: application/json).
2. This rest end point can be used to search available articles or authors by means of applying filter as a plain text passed as 
   querystring.
3. The querystring (searchData) consists of comma (,) seperated plain texts values : eg: Chethan,River of Smoke,The Hungry Tide,Amitabh
   It returns all the matched articles and authors and displays the result set.

This is the most simple text search based mechanism used.
