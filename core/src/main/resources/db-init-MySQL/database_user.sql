CREATE DATABASE IF NOT EXISTS contactsbook;

ALTER DATABASE contactsbook
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci;

CREATE USER IF NOT EXISTS
	'cb-user'@'localhost' IDENTIFIED BY 'cb-user';

GRANT ALL ON contactsbook.* TO 'cb-user'@'localhost';