CREATE ROLE TARINAMITTAUS_ROLE;
-- Object privileges granted to TARINAMITTAUS_ROLE
CREATE OR REPLACE DIRECTORY COMMON_DATA_PUMP_DIR AS '/opt/oracle/oradata/pump';
GRANT READ, WRITE ON DIRECTORY COMMON_DATA_PUMP_DIR TO TARINAMITTAUS_ROLE;
-- System privileges granted to TARINAMITTAUS_ROLE
GRANT CREATE PROCEDURE TO TARINAMITTAUS_ROLE;
GRANT CREATE SEQUENCE TO TARINAMITTAUS_ROLE;
GRANT CREATE SESSION TO TARINAMITTAUS_ROLE;
GRANT CREATE TABLE TO TARINAMITTAUS_ROLE;
GRANT CREATE TRIGGER TO TARINAMITTAUS_ROLE;
GRANT CREATE TYPE TO TARINAMITTAUS_ROLE;
GRANT CREATE VIEW TO TARINAMITTAUS_ROLE;
CREATE TABLESPACE TARINAMITTAUS_DATA DATAFILE 'tarinamittaus_data1.dbf' SIZE 20M REUSE AUTOEXTEND ON maxsize 5G;
create user tarinam identified by livirules default tablespace TARINAMITTAUS_DATA quota 4G on TARINAMITTAUS_DATA;
GRANT TARINAMITTAUS_ROLE to tarinam;

ALTER SYSTEM SET DEFERRED_SEGMENT_CREATION=FALSE;

CREATE TABLE "TARINAM".MITTAUS(
    "KOHDE_ID" NUMBER PRIMARY KEY,
    "ALKUAIKA" TIMESTAMP (6),
    "LOPPUAIKA" TIMESTAMP (6),
    "MITTAUS_ASIANHALLINTA_ID" VARCHAR2(255),
    "PDF_RAPORTIN_LINKKI" VARCHAR2(255),
    "RAKENNUKSEN_PINTA_ALA" NUMBER,
    "PERUSTAMISTAPA" VARCHAR2(255),
    "JULKISIVUMATERIAALI" VARCHAR2(255),
    "RUNKOMATERIAALI" VARCHAR2(255),
    "RAKENNUSVUOSI" SMALLINT,
    "KATUOSOITE" VARCHAR2(255),
    "POSTINUMERO" VARCHAR2(255),
    "CREATED_BY_LX" VARCHAR2(255)
);

CREATE TABLE "TARINAM".ASENNUSPAIKANTYYPPI(
    "PAIKKATYYPPI_ID" NUMBER PRIMARY KEY,
    "SELITE" VARCHAR2(10) CHECK( SELITE IN ('maa', 'sokkeli', 'lattia', 'seina', 'alapohja', 'katto', 'muu') ),
    "LISATIEDOT" VARCHAR2(255)
);


CREATE TABLE "TARINAM".ASENNETTUANTURI(
    "ASENNUSKOHTAINEN_ID" NUMBER PRIMARY KEY,
    "MALLI" VARCHAR2(255),
    "GPS_LAT" NUMBER, --???
    "GPS_LONG" NUMBER, --???
    "ETAISYYS_RADASTA_JOS_ERI" NUMBER,
    "KERROS" SMALLINT,
    "SIJOITUSPAIKAN_LISASELITE" VARCHAR2(255),
    "MITTAUS" NUMBER NOT NULL,
    "ASENNUSPAIKKA" NUMBER NOT NULL,
    CONSTRAINT fk_mittaus FOREIGN KEY (MITTAUS) REFERENCES TARINAM.MITTAUS(KOHDE_ID),
    CONSTRAINT fk_asennuspaikka FOREIGN KEY (ASENNUSPAIKKA) REFERENCES TARINAM.ASENNUSPAIKANTYYPPI(PAIKKATYYPPI_ID)
);

CREATE TABLE "TARINAM".ANTURIKOHTAISETTUNNUSARVOT(
    "TUNNUSARVO_ID" NUMBER PRIMARY KEY,
    "MITTAUSSUUNTA_XYZ" CHAR CHECK ( MITTAUSSUUNTA_XYZ IN('X', 'Y', 'Z') ),
    "TARINAN_MAKSIMIARVO" NUMBER,
    "HALLITSEVA_TAAJUUS" NUMBER,
    "TARINAN_TUNNUSLUKU_VW95_RMS" NUMBER,
    "ASENNETTUANTURI" NUMBER NOT NULL,
    CONSTRAINT fk_asennettuanturi FOREIGN KEY (ASENNETTUANTURI) REFERENCES TARINAM.ASENNETTUANTURI(ASENNUSKOHTAINEN_ID)
);

CREATE SEQUENCE mittaus_id_seq START WITH 1;
CREATE SEQUENCE paikka_id_seq START WITH 1;
CREATE SEQUENCE asennettuanturi_id_seq START WITH 1;
CREATE SEQUENCE tunnusarvo_id_seq START WITH 1;
