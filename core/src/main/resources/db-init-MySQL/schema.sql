USE contactsbook;

DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(25),
    email VARCHAR(50),
    date_of_birth DATE,
    notes VARCHAR(500)
) ENGINE=InnoDB AUTO_INCREMENT=1;