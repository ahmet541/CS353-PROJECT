DROP TABLE IF EXISTS "User" CASCADE;
DROP TABLE IF EXISTS Admin;
DROP TABLE IF EXISTS Account CASCADE;
DROP TABLE IF EXISTS RegularUser CASCADE;
DROP TABLE IF EXISTS Recruiter;
DROP TABLE IF EXISTS Career_Expert;
-- TODO


CREATE TABLE Account (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255 ) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE "User"(
    id INT NOT NULL UNIQUE,
    profile_description VARCHAR(255),
    avatar VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY(id) REFERENCES Account(id) ON DELETE CASCADE
);

CREATE TABLE Post (
    post_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    photo_link VARCHAR(255),
    explanation VARCHAR(255) NOT NULL,
    heading VARCHAR(255) NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES "User" (id)
);

CREATE TABLE follow(
   follower_id INT NOT NULL,
   followed_id INT NOT NULL,
   PRIMARY KEY(followed_id, follower_id),
   FOREIGN KEY(followed_id) REFERENCES "User"(id) ON DELETE CASCADE,
   FOREIGN KEY(follower_id) REFERENCES "User"(id) ON DELETE CASCADE
);
CREATE TABLE connection (
    connected_1_id INT NOT NULL,
    connected_2_id INT NOT NULL,
    accepted BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (connected_1_id, connected_2_id),
    FOREIGN KEY (connected_1_id) REFERENCES Regular_User (id) ON DELETE CASCADE,
    FOREIGN KEY (connected_2_id) REFERENCES Regular_User (id) ON DELETE CASCADE
);

CREATE TABLE Admin (
    id INT NOT NULL UNIQUE,
    admin_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES "User" (id) ON DELETE CASCADE
);

CREATE TABLE Company(
    id INT NOT NULL UNIQUE,
    companyName VARCHAR(255) NOT NULL,
    type VARCHAR(255),
    economicScale INT,
    PRIMARY KEY (id),
    FOREIGN KEY(id) REFERENCES "User"(id) ON DELETE CASCADE
 );

CREATE TABLE Regular_User (
    id INT NOT NULL UNIQUE,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    gender varchar(255),
    phone_number varchar(255),
    birthdate timestamp,
    address varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES "User" (id) ON DELETE CASCADE
);

CREATE TABLE Recruiter (
    id INT NOT NULL UNIQUE,
    recruiting_start_date DATE NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES Regular_User (id) ON DELETE CASCADE
);

CREATE TABLE Career_Expert (
    id INT NOT NULL UNIQUE,
    rank INT,
    last_year_score INT,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES Regular_User (id) ON DELETE CASCADE
);


CREATE TABLE Certificate_Skill (
   certificate_name VARCHAR(255),
   content VARCHAR(255),
   user_id INT,
   FOREIGN KEY(user_id) REFERENCES Regular_User(id) ON DELETE CASCADE
);

CREATE TABLE Connection (
    connected_1_id int NOT NULL,
    connected_2_id int NOT NULL,
    accepted BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (connected_1_id, connected_2_id),
    FOREIGN KEY (connected_1_id) REFERENCES Regular_User(id) ON DELETE CASCADE,
    FOREIGN KEY (connected_2_id) REFERENCES Regular_User(id) ON DELETE CASCADE
);
CREATE TABLE Comment (
     comment_id SERIAL PRIMARY KEY,
     post_id INT NOT NULL,
     user_id INT NOT NULL,
     context VARCHAR(255) NOT NULL,
     date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (post_id) REFERENCES Post (post_id),
     FOREIGN KEY (user_id) REFERENCES "User" (id)
);


CREATE VIEW post_owner_detail AS
SELECT
    "User".id AS userId,
    "User".avatar,
    COALESCE(CONCAT_WS(' ', Regular_User.first_name, Regular_User.last_name), Company.companyName) AS fullName
FROM
    "User"
        LEFT JOIN
    Regular_User ON "User".id = Regular_User.id
        LEFT JOIN
    Company ON "User".id = Company.id;

