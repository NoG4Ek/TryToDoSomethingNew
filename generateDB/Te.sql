CREATE DATABASE test;
USE test;
CREATE TABLE users (
	idusers SMALLINT UNSIGNED NOT NULL primary key auto_increment, 
	firstname VARCHAR(45) NOT NULL, 
	lastname VARCHAR(45) NOT NULL,
	username VARCHAR(45) NOT NULL,
	email VARCHAR(45) NOT NULL,
	password VARCHAR(45) NOT NULL,
	rating INT NOT NULL default 0,
	completedquests JSON default NULL
 ) ENGINE=InnoDB;
 
CREATE TABLE quests (
	idquests SMALLINT UNSIGNED NOT NULL primary key auto_increment,
	questname VARCHAR(45) NOT NULL,
	description LONGTEXT NOT NULL,
	code VARCHAR(45) NOT NULL,
	cost INT NOT NULL,
	mark VARCHAR(45) default null
 ) ENGINE=InnoDB;
 
 INSERT INTO users ( idusers, firstname, lastname, username, email, password, rating, completedquests ) 
 VALUES ( null, 'test', 'test', 'test', 'test@mail.ru', 'test', 100, null );
 
 INSERT INTO quests ( idquests, questname, description, code, cost, mark)
 VALUES ( null, 'test', 'just test', 'test', 100, 'active' );
 
 UPDATE `test`.`users` SET `completedquests` = '[\"Test\"]' WHERE (`idusers` = '1');