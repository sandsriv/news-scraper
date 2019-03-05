-------------------------------------------------|
-- INSERT SCRIPTS : AUTHORS
-------------------------------------------------|
insert into authors values(1,'Amitabh Ghosh',    'M', curdate(),curdate(),'sandipsr');
insert into authors values(2,'Amish Tripathy',   'M', curdate(),curdate(),'sandipsr');
insert into authors values(3,'Chethan Bhagat',   'M', curdate(),curdate(),'sandipsr');
insert into authors values(4,'Mahadevi Verma',   'F', curdate(),curdate(),'sandipsr');
insert into authors values(5,'VS Naipaul',       'M', curdate(),curdate(),'sandipsr');
insert into authors values(6,'Anita Nair',       'F', curdate(),curdate(),'sandipsr');
insert into authors values(7,'Jhumpa Lahiri',    'F', curdate(),curdate(),'sandipsr');
insert into authors values(8,'Anita Desai',      'F', curdate(),curdate(),'sandipsr');
insert into authors values(9,'Ravi Subramaniam', 'M', curdate(),curdate(),'sandipsr');
insert into authors values(10,'Sanjay Baru',     'M', curdate(),curdate(),'sandipsr');
commit;
insert into authors values(11,'Amitabh Sen',     'M', curdate(),curdate(),'sandipsr');
insert into authors values(12,'Amish Kumar',   	 'M', curdate(),curdate(),'sandipsr');
insert into authors values(13,'Chethan Rao',     'M', curdate(),curdate(),'sandipsr');
insert into authors values(14,'Mahadevi Sharma', 'F', curdate(),curdate(),'sandipsr');
insert into authors values(15,'VS Sen',       	 'M', curdate(),curdate(),'sandipsr');
insert into authors values(16,'Anita Nayar',     'F', curdate(),curdate(),'sandipsr');
insert into authors values(17,'Jhumpa Das',      'F', curdate(),curdate(),'sandipsr');
insert into authors values(18,'Anita R Desai',   'F', curdate(),curdate(),'sandipsr');
insert into authors values(19,'Ravi Suman', 	 'M', curdate(),curdate(),'sandipsr');
insert into authors values(20,'Sanjay Kumar',    'M', curdate(),curdate(),'sandipsr');
commit;

-------------------------------------------------|
-- INSERT SCRIPTS : ARTICLES
-------------------------------------------------|
insert into articles values(1, 'The Hungry Tide', 'FICTION', 'The Hungry Tide is a very contemporary story of adventure and unlikely love, identity, and history, set in one o
f the most fascinating regions on the earth. Off the easternmost coast of India, in the Bay of Bengal, lies the immense labyrinth of tiny islands known as the S
undarbans. For settlers here, life is extremely precarious. Attacks by deadly tigers are common.', curdate(),curdate(),'sandipsr');

insert into articles values(2, 'River of Smoke', 'FICTION', 'River of Smoke is a novel by Indian novelist Amitav Ghosh. It is the second volume of the Ibis trilogy..', curdate(),curdate(),'sandipsr');

insert into articles values(3, 'The Glass Palace', 'FICTION', 'The Glass Palace is a 2000 historical novel by Indian writer Amitav Ghosh. The novel is set in Burma, Bengal, India, and Malaya, spans a century from the British invasion of Burma and the consequent fall of the Konbaung Dynasty in Mandalay, through the Second World War to late 20th century', curdate(),curdate(),'sandipsr');




-------------------------------------------------|
-- INSERT SCRIPTS : ARTICLES-AUTHOR_REL
-------------------------------------------------|
insert into articles_author_rel values(1,   1 , curdate(),curdate(),'sandipsr');
insert into articles_author_rel values(1,   2 , curdate(),curdate(),'sandipsr');
insert into articles_author_rel values(1,   3 , curdate(),curdate(),'sandipsr');

insert into articles_author_rel values(2,   10 , curdate(),curdate(),'sandipsr');
insert into articles_author_rel values(2,   1 , curdate(),curdate(),'sandipsr');
