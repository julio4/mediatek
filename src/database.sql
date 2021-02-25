-- Create
create table USERS
(
    username VARCHAR2(30) UNIQUE NOT NULL,
    password  VARCHAR2(30) NOT NULL,
    primary key (username)
);

-- Insert

insert into USERS (username, password)
values ('admin','admin');

-- Commit
Commit;