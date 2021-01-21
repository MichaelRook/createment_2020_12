-- Database: Bank

-- DROP DATABASE "Bank";


--Drop table "bankrekening";
--Drop table "bankrekeningsoort";
--Drop table "bank";

CREATE DATABASE "Bank"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United Kingdom.1252'
    LC_CTYPE = 'English_United Kingdom.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE Bank
	(OID Serial,
	NAAM VARCHAR(70) UNIQUE NOT NULL, 
PRIMARY KEY     (OID)   );



CREATE  TABLE BankRekeningSoort
	(OID	DECIMAL(2,0) NOT NULL,
	 NAAM	VARCHAR(20)  UNIQUE NOT NULL,
PRIMARY KEY	(OID)	);

-- From Enumeration class RekeningenType
-- package nl.prolector.cursus.java.io.streams.opdrachten.bank;
INSERT INTO BankRekeningSoort (OID, NAAM)
Values	(0, 'Spaar');

INSERT INTO BankRekeningSoort (OID, NAAM)
Values	(1, 'Courant');


CREATE TABLE BankRekening
	(OID 		SERIAL,
	 BANK_OID 	SERIAL NOT NULL,
	 SOORT_OID	DECIMAL	(10,0) NOT NULL,
	 NUMMER		DECIMAL	(10,0) NOT NULL,
	 SALDO		DECIMAL (17,2) NOT NULL,
	 HOUDER		VARCHAR (50)   NOT NULL,
	 
UNIQUE(BANK_OID,NUMMER),
PRIMARY KEY     (OID)   ,
FOREIGN KEY 	(BANK_OID)  REFERENCES 	Bank,
FOREIGN KEY 	(SOORT_OID) REFERENCES	BankRekeningSoort
);


--Adding Values
--Bank

--INSERT INTO Bank (NAAM)
--Values ('Rabobank');

--Rekeningen

--INSERT INTO BankRekening (BANK_OID, SOORT_OID,NUMMER,SALDO,HOUDER)
--Values ((SELECT OID
--FROM Bank
--Where NAAM = 'Rabobank')
--,(SELECT OID
--FROM BankRekeningSoort
--Where NAAM = 'Courant')
--,1002,1000000,'Maarten')











