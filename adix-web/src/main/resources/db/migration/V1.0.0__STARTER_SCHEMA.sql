CREATE TABLE IF NOT EXISTS ADIX_USER (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	CODE char(2) UNIQUE NOT NULL,
	EMAIL_ADDRESS varchar(256) UNIQUE NOT NULL,
	FIRST_NAME varchar(256) NOT NULL,
	LAST_NAME varchar(256) NOT NULL,
	IS_ADMIN bit NOT NULL DEFAULT 0,
	ENABLED bit NOT NULL DEFAULT 0,
	NOTE text DEFAULT NULL,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp
);

CREATE TABLE IF NOT EXISTS ADIX_USER_CREDENTIAL (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	ID_USER bigint UNIQUE NOT NULL,
	PASSWORD varchar(256) NOT NULL,
	TO_CHANGE bit NOT NULL DEFAULT 0,
	LAST_CHANGE timestamp DEFAULT NULL,
	EXPIRES bit NOT NULL DEFAULT 0,
	EXPIRES_EVERY_DAYS int DEFAULT NULL,
	EXPIRES_ON date DEFAULT NULL,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp,
	FOREIGN KEY (ID_USER) REFERENCES ADIX_USER (ID)
);

INSERT INTO ADIX_USER (CODE, EMAIL_ADDRESS, FIRST_NAME, LAST_NAME, IS_ADMIN, ENABLED, NOTE)
VALUES ('AA', 'marco.ambrosi2000@gmail.com', 'Marco', 'Ambrosi', 1, 1, 'Utente generato');
INSERT INTO ADIX_USER_CREDENTIAL (ID_USER, PASSWORD, TO_CHANGE, EXPIRES)
VALUES (1, '$2a$12$hX8qugnCU768WJcOZBzD2ufA78iBw7jcpanryIj4zuUKnNHSIixhi', 0, 0);

CREATE TABLE IF NOT EXISTS ADIX_ROLE (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	CODE varchar(100) UNIQUE NOT NULL,
	NAME varchar(100) UNIQUE NOT NULL,
	DESCRIPTION text NOT NULL,
	ENABLED bit NOT NULL DEFAULT 0,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp
);
INSERT INTO ADIX_ROLE(CODE, NAME, DESCRIPTION, ENABLED)
VALUES
('PARTNER', 'Socio', 'Socio dell\'azienda', 1),
('INSPECTOR', 'Controllore', 'Controllore dell\'ufficio', 1),
('RESPONSIBLE', 'Responsible', 'Responsabile dell\'ufficio', 1),
('ACCOUNTANT', 'Contabile', 'Contabile dell\'ufficio', 1);

CREATE TABLE IF NOT EXISTS ADIX_USER_ROLE (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	ID_USER bigint NOT NULL,
	ID_ROLE bigint NOT NULL,
	ENABLED bit NOT NULL DEFAULT 0,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp,
	FOREIGN KEY (ID_USER) REFERENCES ADIX_USER (ID),
	FOREIGN KEY (ID_ROLE) REFERENCES ADIX_ROLE (ID),
	CONSTRAINT UNIQUE_USER_ROLE UNIQUE (ID_USER, ID_ROLE)
);

CREATE TABLE IF NOT EXISTS ADIX_PERMIT (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	CODE varchar(100) UNIQUE NOT NULL,
	NAME varchar(100) UNIQUE NOT NULL,
	DESCRIPTION text NOT NULL,
	PARENT_ROLE bigint DEFAULT NULL,
	PARENT_PERMIT bigint DEFAULT NULL,
	ENABLED bit NOT NULL DEFAULT 0,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp,
	FOREIGN KEY (PARENT_ROLE) REFERENCES ADIX_ROLE (ID),
	FOREIGN KEY (PARENT_PERMIT) REFERENCES ADIX_PERMIT (ID)
);
INSERT INTO ADIX_PERMIT(CODE, NAME, DESCRIPTION, ENABLED)
VALUES
('ADMIN_VIEW_ALL_USERS', 'Amministrazione utenti', 'Può visualizzare tutti gli utenti registrati', 1),
('ADMIN_ADD_USER', 'Aggiungi utente', 'Può aggiungere nuovi utenti e permettere loro di accedere al pannello', 1),
('ADMIN_EDIT_USER', 'Modifica utente', 'Può modificare i dati di utenti già esistenti', 1),
('ADMIN_EDIT_USER_PERMITS', 'Modifica permessi', 'Può modificare i permessi di un utente, abilitare o disabilitare la possibilità di vedere o modificare i dati, bloccare l\'accesso al pannello', 1),
('ADMIN_EDIT_USER_CREDENTIALS', 'Modifica credenziali', 'Può modificari i criteri di modifica delle password, stabilire le tempistiche di scadenza e inviare email di recupero', 1),
('ADMIN_DELETE_USER', 'Elimina utente', 'Può cancellare gli utenti dalla piattaforma in via definitiva', 1),
('ANY_ROLE_VIEW_ALL_CUSTOMERS', 'Amministrazione clienti', 'Può accedere e visualizzare tutti i clienti', 1),
('ANY_ROLE_ADD_CUSTOMER', 'Aggiungi cliente', 'Può aggiungere nuovi clienti', 1),
('ANY_ROLE_EDIT_CUSTOMER', 'Modifica cliente', 'Può modificare i dati di clienti già esistenti', 1),
('ANY_ROLE_DELETE_CUSTOMER', 'Elimina cliente', 'Può cancellare i clienti e i loro dati in via definitiva', 1),
('ANY_ROLE_VIEW_ALL_FULFILLMENTS', 'Amministrazione adempimenti', 'Può accedere e visualizzare tutti gli adempimenti disponibili', 1),
('ANY_ROLE_ADD_FULFILLMENT', 'Aggiungi adempimento', 'Può aggiungere nuovi adempimenti e stabilire valori come la ricorrenza degli stessi', 1),
('ANY_ROLE_EDIT_FULFILLMENT', 'Modifica adempimento', 'Può modificare i dati di adempimenti già esistenti', 1),
('ANY_ROLE_DELETE_FULFILLMENT', 'Elimina adempimento', 'Può cancellare gli adempimenti, i loro dati e lo storico completo in via definitiva', 1),
('ROLE_PARTNER_VIEW_OWN_CUSTOMERS', 'Pannello socio', 'Può visualizzare i clienti assegnati come socio', 1),
('ROLE_INSPECTOR_VIEW_OWN_CUSTOMERS', 'Pannello controllore', 'Può visualizzare i clienti assegnati come controllore', 1),
('ROLE_RESPONSIBLE_VIEW_OWN_CUSTOMERS', 'Pannello responsabile', 'Può visualizzare i clienti assegnati come responsabile', 1),
('ROLE_ACCOUNTANT_VIEW_OWN_CUSTOMERS', 'Pannello contabile', 'Può visualizzare i clienti assegnati come contabile', 1),
('ANY_ROLE_VIEW_OWN_FULFILLMENTS_RECORDS', 'Adempimenti personali', 'Può visualizzare gli adiempimenti periodici dei clienti a cui è assegnato', 1),
('ANY_ROLE_EDIT_OWN_FULFILLMENTS_RECORDS', 'Modifica adempimenti personali', 'Può modificare gli adiempimenti periodici dei clienti a cui è assegnato', 1),
('ANY_ROLE_VIEW_ALL_FULFILLMENTS_RECORDS', 'Adempimenti globali', 'Può visualizzare gli adiempimenti periodici di tutti i clienti', 1),
('ANY_ROLE_EDIT_ALL_FULFILLMENTS_RECORDS', 'Modifica adempimenti globali', 'Può modificare gli adiempimenti periodici di tutti i clienti', 1);
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_ROLE ar WHERE CODE = 'PARTNER') r
SET PARENT_ROLE = r.ID
WHERE CODE IN ('ROLE_PARTNER_VIEW_OWN_CUSTOMERS');
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_ROLE ar WHERE CODE = 'INSPECTOR') r
SET PARENT_ROLE = r.ID
WHERE CODE IN ('ROLE_INSPECTOR_VIEW_OWN_CUSTOMERS');
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_ROLE ar WHERE CODE = 'RESPONSIBLE') r
SET PARENT_ROLE = r.ID
WHERE CODE IN ('ROLE_RESPONSIBLE_VIEW_OWN_CUSTOMERS');
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_ROLE ar WHERE CODE = 'ACCOUNTANT') r
SET PARENT_ROLE = r.ID
WHERE CODE IN ('ROLE_ACCOUNTANT_VIEW_OWN_CUSTOMERS');
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_PERMIT ap WHERE CODE = 'ADMIN_VIEW_ALL_USERS') p
SET PARENT_PERMIT = p.ID
WHERE CODE IN ('ADMIN_ADD_USER', 'ADMIN_EDIT_USER', 'ADMIN_DELETE_USER');
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_PERMIT ap WHERE CODE = 'ADMIN_EDIT_USER') p
SET PARENT_PERMIT = p.ID
WHERE CODE IN ('ADMIN_EDIT_USER_PERMITS', 'ADMIN_EDIT_USER_CREDENTIALS');
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_PERMIT ap WHERE CODE = 'ANY_ROLE_VIEW_ALL_CUSTOMERS') p
SET PARENT_PERMIT = p.ID
WHERE CODE IN ('ANY_ROLE_ADD_CUSTOMER', 'ANY_ROLE_EDIT_CUSTOMER', 'ANY_ROLE_DELETE_CUSTOMER');
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_PERMIT ap WHERE CODE = 'ANY_ROLE_VIEW_ALL_FULFILLMENTS') p
SET PARENT_PERMIT = p.ID
WHERE CODE IN ('ANY_ROLE_ADD_FULFILLMENT', 'ANY_ROLE_EDIT_FULFILLMENT', 'ANY_ROLE_DELETE_FULFILLMENT');
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_PERMIT ap WHERE CODE = 'ANY_ROLE_VIEW_OWN_FULFILLMENTS_RECORDS') p
SET PARENT_PERMIT = p.ID
WHERE CODE IN ('ANY_ROLE_EDIT_OWN_FULFILLMENTS_RECORDS');
UPDATE ADIX_PERMIT, (SELECT ID FROM ADIX_PERMIT ap WHERE CODE = 'ANY_ROLE_VIEW_ALL_FULFILLMENTS_RECORDS') p
SET PARENT_PERMIT = p.ID
WHERE CODE IN ('ANY_ROLE_EDIT_ALL_FULFILLMENTS_RECORDS');


CREATE TABLE IF NOT EXISTS ADIX_USER_PERMIT (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	ID_USER bigint NOT NULL,
	ID_PERMIT bigint NOT NULL,
	ENABLED bit NOT NULL DEFAULT 0,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp,
	FOREIGN KEY (ID_USER) REFERENCES ADIX_USER (ID),
	FOREIGN KEY (ID_PERMIT) REFERENCES ADIX_PERMIT (ID),
	CONSTRAINT UNIQUE_USER_PERMIT UNIQUE (ID_USER, ID_PERMIT)
);

CREATE TABLE IF NOT EXISTS ADIX_CUSTOMER (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	ID_USER_PARTNER bigint DEFAULT NULL,
	ID_USER_INSPECTOR bigint DEFAULT NULL,
	ID_USER_RESPONSIBLE bigint DEFAULT NULL,
	ID_USER_ACCOUNTANT bigint DEFAULT NULL,
	CODE char(2) NOT NULL,
	BUSINESS_NAME varchar(256) UNIQUE NOT NULL,
	VAT_NUMBER char(11) UNIQUE NOT NULL,
	ACCOUNTING_TYPE varchar(20) NOT NULL,
	DELEGATION bit NOT NULL DEFAULT 0,
	ENABLED bit NOT NULL DEFAULT 0,
	NOTE text DEFAULT NULL,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp,
	FOREIGN KEY (ID_USER_PARTNER) REFERENCES ADIX_USER (ID),
	FOREIGN KEY (ID_USER_INSPECTOR) REFERENCES ADIX_USER (ID),
	FOREIGN KEY (ID_USER_MANAGER) REFERENCES ADIX_USER (ID),
	FOREIGN KEY (ID_USER_ACCOUNTANT) REFERENCES ADIX_USER (ID)
);

CREATE TABLE IF NOT EXISTS ADIX_FULFILLMENT (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	NAME varchar(256) UNIQUE NOT NULL,
	DESCRIPTION text NOT NULL,
	MONTH_RECURRENCE int NOT NULL,
	ENABLED bit NOT NULL DEFAULT 0,
	NOTE text DEFAULT NULL,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp
);

CREATE TABLE IF NOT EXISTS ADIX_FULFILLMENT_RECORD (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	ID_CUSTOMER bigint NOT NULL,
	ID_FULFILLMENT bigint NOT NULL,
	PERIOD int NOT NULL DEFAULT 0,
	STATUS varchar(20) NOT NULL,
	DISPATCH_DATE timestamp DEFAULT NULL,
	NOTE text DEFAULT NULL,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp,
	FOREIGN KEY (ID_CUSTOMER) REFERENCES ADIX_CUSTOMER (ID),
	FOREIGN KEY (ID_FULFILLMENT) REFERENCES ADIX_FULFILLMENT (ID),
	CONSTRAINT UNIQUE_FULFILLMENT_RECORD UNIQUE (ID_CUSTOMER, ID_FULFILLMENT, PERIOD)
);

/* CREATE TABLE IF NOT EXISTS ADIX_LOG (
	ID bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
	CODE char(4) NOT NULL,
	NOTE text DEFAULT NULL,
	NOTE text DEFAULT NULL,
	INSERT_DATE timestamp NOT NULL DEFAULT current_timestamp,
	UPDATE_DATE timestamp ON UPDATE current_timestamp
); */