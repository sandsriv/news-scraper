-------------------------------------------------|
-- CREATE MYSQL DATABASE

create DATABASE newsscraper;
-------------------------------------------------|

-- CREATE TABLE Author
create table AUTHORS(	AUTHOR_ID      INT PRIMARY  KEY AUTO_INCREMENT,
						NAME           VARCHAR(100) NOT NULL,				  
				  		GENDER         VARCHAR(20)  NOT NULL,
				  		CREATED_ON     DATE,
				  		UPDATED_ON     DATE,
				  		UPDATED_BY     VARCHAR(100),
				  		
				  		CONSTRAINT  athr_pk1 UNIQUE KEY (AUTHOR_ID)
				  	  );
				  	  
-- CREATE TABLE Author
create table ARTICLES(	ARTICLE_ID     INT PRIMARY    KEY AUTO_INCREMENT,
						TITLE          VARCHAR(100)   NOT NULL,
						ARTICLE_TYPE   VARCHAR(100)   ,
				  		DESCRIPTION    VARCHAR(2000)  ,
				  		CREATED_ON     DATE,
				  		UPDATED_ON     DATE,
				  		UPDATED_BY     VARCHAR(100),
				  		
				  		CONSTRAINT  article_pk1 UNIQUE KEY (ARTICLE_ID, TITLE, ARTICLE_TYPE)
				  	  );

-- CREATE TABLE ARTICLES_CONTENT
create table ARTICLES_CONTENT(	ARTICLE_ID     INT 			  NOT NULL,
								CONTENTS       VARCHAR(2000)  NOT NULL,
				  				CREATED_ON     DATE,
				  				UPDATED_ON     DATE,
				  				UPDATED_BY     VARCHAR(100),
				  		FOREIGN KEY article_fk1 ( ARTICLE_ID )   REFERENCES ARTICLES(ARTICLE_ID) ON UPDATE CASCADE ON DELETE RESTRICT
				  	  );
				  	  
-- CREATE TABLE Articles-Author Mapping
create table ARTICLES_AUTHOR_REL(	ARTICLE_ID     INT,
									AUTHOR_ID      INT,
				  					CREATED_ON     DATE,
				  					UPDATED_ON     DATE,
				  					UPDATED_BY     VARCHAR(100),
				  		FOREIGN KEY art_auth_fk1 ( ARTICLE_ID )   REFERENCES ARTICLES(ARTICLE_ID) ON UPDATE CASCADE ON DELETE RESTRICT,
				  		FOREIGN KEY art_auth_fk2  ( AUTHOR_ID  )   REFERENCES AUTHORS (AUTHOR_ID ) ON UPDATE CASCADE ON DELETE RESTRICT
				  	  );  