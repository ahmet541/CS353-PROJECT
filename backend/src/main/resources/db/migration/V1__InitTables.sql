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

CREATE TABLE connection (
    connected_1_id INT NOT NULL,
    connected_2_id INT NOT NULL,
    accepted BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (connected_1_id, connected_2_id),
    FOREIGN KEY (connected_1_id) REFERENCES Regular_User (id) ON DELETE CASCADE,
    FOREIGN KEY (connected_2_id) REFERENCES Regular_User (id) ON DELETE CASCADE
);

CREATE TABLE message (
     sender_id   INT NOT NULL,
     receiver_id INT NOT NULL,
     content     VARCHAR(255) NOT NULL,
     post_date   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (sender_id, receiver_id, post_date),
     FOREIGN KEY (sender_id) REFERENCES account (id) ON DELETE CASCADE,
     FOREIGN KEY (receiver_id) REFERENCES account (id) ON DELETE CASCADE
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


CREATE TABLE Comment (
     comment_id SERIAL PRIMARY KEY,
     post_id INT NOT NULL,
     user_id INT NOT NULL,
     context VARCHAR(255) NOT NULL,
     date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (post_id) REFERENCES Post (post_id),
     FOREIGN KEY (user_id) REFERENCES "User" (id)
);

CREATE TABLE Post_Like (
       user_id INT NOT NULL,
       post_id INT NOT NULL,
       PRIMARY KEY (user_id, post_id),
       FOREIGN KEY (user_id) REFERENCES "User"(id) ON DELETE CASCADE,
       FOREIGN KEY (post_id) REFERENCES Post(post_id) ON DELETE CASCADE
);

CREATE TABLE JobOpening(
    job_opening_id SERIAL NOT NULL ,
    employment_status INT NOT NULL,
    explanation VARCHAR(255) NOT NULL,
    due_date timestamp,
    role_pro VARCHAR(255),
    location VARCHAR(255),
    work_type VARCHAR(255),
    PRIMARY KEY(job_opening_id)
);

CREATE TABLE open_position(
  company_id int NOT NULL,
  recruiter_id int NOT NULL,
  job_opening_id int NOT NULL,
  PRIMARY KEY(company_id, recruiter_id, job_opening_id),
  FOREIGN KEY(company_id) REFERENCES Company(id) ON DELETE CASCADE,
  FOREIGN KEY(recruiter_id) REFERENCES Recruiter(id),
  FOREIGN KEY(job_opening_id) REFERENCES JobOpening(job_opening_id) ON DELETE CASCADE
);

CREATE TABLE application(
user_id int NOT NULL,
job_opening_id int NOT NULL,
application_date timestamp NOT NULL,
application_status int NOT NULL,
experience VARCHAR(255) NOT NULL ,
skills VARCHAR(255),
education_lvl int,
cv VARCHAR(255),
PRIMARY KEY(user_id, job_opening_id),
FOREIGN KEY(user_id) REFERENCES Regular_User(id) ON DELETE CASCADE,
FOREIGN KEY(job_opening_id) REFERENCES JobOpening(job_opening_id) ON DELETE CASCADE
);

CREATE TABLE verifies(
     company_id int NOT NULL,
     recruiter_id int NOT NULL,
     PRIMARY KEY(company_id, recruiter_id),
     FOREIGN KEY(company_id) REFERENCES Company(id) ON DELETE CASCADE,
     FOREIGN KEY(recruiter_id) REFERENCES Recruiter(id) ON DELETE CASCADE
);

CREATE TABLE employs(
    company_id int NOT NULL,
    regular_user_id int NOT NULL,
    recruiter_id int,
    emp_role int NOT NULL,
    emp_start_date datetime NOT NULL, --DEFAULT GETDATE(),
    emp_end_date datetime,
    PRIMARY KEY(company_id, regular_user_id),
    FOREIGN KEY(company_id) REFERENCES Company(id) ON DELETE CASCADE,
    FOREIGN KEY(recruiter_id) REFERENCES Recruiter(id) ON DELETE SET NULL,
    FOREIGN KEY(regular_user_id) REFERENCES Regular_User(id) ON DELETE CASCADE
);
CREATE TABLE Field(
    field_name varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY(field_name));

CREATE TABLE job_field(
      field_name varchar(255) NOT NULL,
      job_opening_id int NOT NULL,
      PRIMARY KEY(field_name, job_opening_id),
      FOREIGN KEY(field_name) REFERENCES Field(field_name) ON UPDATE CASCADE ON DELETE CASCADE,
      FOREIGN KEY(job_opening_id) REFERENCES Job Opening(job_opening_id) ON DELETE CASCADE);

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

