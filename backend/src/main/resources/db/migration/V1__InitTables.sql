DROP TABLE IF EXISTS User CASCADE;
DROP TABLE IF EXISTS Admin;
DROP TABLE IF EXISTS Account CASCADE;
DROP TABLE IF EXISTS "Regular User";

CREATE TABLE Account (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255 ) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE Admin (
    admin_name VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);

CREATE TABLE User(
     id SERIAL PRIMARY KEY,
     profile_description VARCHAR(255),
     FOREIGN KEY(id) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Regular_User (
    id int NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    gender varchar(255),
    phone_number varchar(255),
    birthdate timestamp,
    address varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES User (id) ON DELETE CASCADE
);

CREATE TABLE Certificate_Skill (
   certificate_name VARCHAR(255),
   content VARCHAR(255),
   user_id INT,
   FOREIGN KEY(user_id) REFERENCES Regular_User(id) ON DELETE CASCADE
);

CREATE TABLE connection (
    connected_1_id int NOT NULL,
    connected_2_id int NOT NULL,
    accepted int DEFAULT 0,
    PRIMARY KEY (connected_1_id, connected_2_id),
    FOREIGN KEY (connected_1_id) REFERENCES Regular_User(id) ON DELETE CASCADE,
    FOREIGN KEY (connected_2_id) REFERENCES Regular_User(id) ON DELETE CASCADE
);
