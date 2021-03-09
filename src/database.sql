-- SCRIPT SQL ORACLE
-- CREATION DU SCHEMA DE LA BASE ET INSERTION D'UN JEU DE DONNEE

-- CREATION
CREATE TABLE UTILISATEURS
(
    identifiant VARCHAR2(30) NOT NULL,
    motdepasse  VARCHAR2(50) NOT NULL,
    PRIMARY KEY (identifiant)
);

CREATE TABLE DOCUMENTS
(
    id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    type NUMBER NOT NULL,
    titre VARCHAR(50) NOT NULL,
    auteur VARCHAR(50) NOT NULL,
    emprunt NUMBER(1,0),
    PRIMARY KEY (id)
);

-- INSERTION DONNEES
INSERT INTO UTILISATEURS (identifiant, motdepasse)
VALUES ('admin','admin');
INSERT INTO UTILISATEURS (identifiant, motdepasse)
VALUES ('user','user');

INSERT INTO DOCUMENTS (type, titre, auteur, emprunt)
VALUES (1,'Stars Wars','BLOB', 1);
INSERT INTO DOCUMENTS (type, titre, auteur, emprunt)
VALUES (2,'...','BLOB', 1);
INSERT INTO DOCUMENTS (type, titre, auteur, emprunt)
VALUES (3,'....','BLOB', 0);

COMMIT;