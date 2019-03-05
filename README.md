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
1. Eclipse :Eclipse Java EE IDE for Web Developers.
            Version: Luna Service Release 2 (4.4.2)
            Build id: 20150219-0600
2. JDK     :jdk1.8.0_131
3. Maven   :Apache Maven 3.3.9
4. Spring Boot :2.1.1.RELEASE
5. MySQL DB    :MySQL Server 5.5

Project-Structure:
-----------------
 scraper
   -- scraper-api  : Artifacts for interfaces & bean classes
	 -- scraper-impl : Artifacts for implementation for api interfaces.
	 -- scraper-rest :  Artifacts for rest controllers.

