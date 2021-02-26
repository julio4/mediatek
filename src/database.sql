-- Create
CREATE TABLE USERS
(
    username VARCHAR2(30) UNIQUE NOT NULL,
    password  VARCHAR2(30) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE DOCUMENTS
(
    id INT,
    type INT NOT NULL,
    title VARCHAR(50) NOT NULL,
    emrpunt BOOLEAN,
    PRIMARY KEY (id)
);

-- Insert

insert into USERS (username, password)
values ('admin','admin');

insert into DOCUMENTS(id, type, title)
values (1,'DVD','Stars Wars');
insert into DOCUMENTS(id, type, title)
values (2,'CD','...');
insert into DOCUMENTS(id, type, title)
values (3,'Livre','....');

-- Commit
Commit;