create database Clinica;
use Clinica;

CREATE TABLE IF NOT EXISTS Angajat
( idAn integer PRIMARY KEY,
  salariu DECIMAL(8, 2),
  numarOre integer
);

CREATE TABLE IF NOT EXISTS InspectorHR
(
  idResUm integer PRIMARY KEY,
  idAn int,
  FOREIGN KEY (idAn) REFERENCES Angajat(idAn)
);

CREATE TABLE IF NOT EXISTS Expert
(
  idFin_Cont integer PRIMARY KEY,
  idAn int,
  FOREIGN KEY (idAn) REFERENCES Angajat(idAn)
);

CREATE TABLE IF NOT EXISTS Receptioner
(
  idRec integer PRIMARY KEY,
  idAn int,
  FOREIGN KEY (idAn) REFERENCES Angajat(idAn)
);

CREATE TABLE IF NOT EXISTS Asistent
( idAsist integer PRIMARY KEY,
  idAn int,
  FOREIGN KEY (idAn) REFERENCES Angajat(idAn),
  tipul varchar(15),
  gradul varchar(15)
);

CREATE TABLE IF NOT EXISTS SpecializareMedici(
idSpec integer PRIMARY KEY ,
specializare varchar(50)
);

CREATE TABLE IF NOT EXISTS Medic
( idMedic integer PRIMARY KEY,
  idSpec int,
  FOREIGN KEY (idSpec) REFERENCES SpecializareMedici(idSpec),
  idAn int,
  FOREIGN KEY (idAn) REFERENCES Angajat(idAn),
  gradul varchar(15), #specialist, primar
  parafa integer, 
  competente varchar(100),
  titlu varchar(30), #doctor, doctorand
  post varchar(20),  #preparator, asistent, sef_lucrari, conferentiar, profesor
  procent integer  #salariu si numarOre are deja pentru ca e un angajat
);

CREATE TABLE IF NOT EXISTS Administrator
( idAdmin integer PRIMARY KEY,
  SUPERadmin boolean
);    

CREATE TABLE IF NOT EXISTS Servicii
( idSer integer PRIMARY KEY,
  disponibil boolean,
  speciliatatea varchar(30),
  competenta_medic varchar(30),
  pret DECIMAL(5,2),
  durata TINYINT
);

CREATE TABLE IF NOT EXISTS Utilizator
( idUtil integer PRIMARY KEY, 
idAn int,
idAdmin int,
FOREIGN KEY (idAdmin) REFERENCES Administrator(idAdmin),
FOREIGN KEY (idAn) REFERENCES Angajat(idAn),
CNP varchar(13),
nume varchar(15),
prenume varchar(15),
adresa varchar(40),
nrTel BIGINT(10),
email varchar(50),
cont_iban varchar(30),
nr_contract BIGINT(30),
data_anganj DATE,
parola varchar(30)
);

CREATE TABLE IF NOT EXISTS Unitate_Medicala
(nr_unitate_med integer PRIMARY KEY,
idUtil int,
FOREIGN KEY (idUtil) REFERENCES Utilizator(idUtil),
denum varchar(20) NOT NULL,
adresa varchar(60) NOT NULL,
descriere varchar(100) NOT NULL,
program_zi varchar(50) NOT NULL,
ziua varchar(10) NOT NULL);

CREATE TABLE IF NOT EXISTS Pacient
(  idPac integer PRIMARY KEY,
   idMedic int,
   idRec int,
   nr_unitate_med int,
   idSer int,
   FOREIGN KEY (idMedic) REFERENCES Medic(idMedic),
   FOREIGN KEY (idRec) REFERENCES Receptioner(idRec),
   FOREIGN KEY (nr_unitate_med) REFERENCES Unitate_Medicala(nr_unitate_med), 
   FOREIGN KEY (idSer) REFERENCES Servicii(idSer), 
   numele_pacient varchar(60),
   program_zi varchar(50) NOT NULL,
   ziua varchar(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Rezultat
(  idResult integer PRIMARY KEY,
   idMedic int, 
   idAsist int,
   FOREIGN KEY (idMedic) REFERENCES Medic(idMedic),
   FOREIGN KEY (idAsist) REFERENCES Asistent(idAsist),
   raport varchar(200)
);

CREATE TABLE IF NOT EXISTS Orar
(  id_program integer PRIMARY KEY,
   idAn int,
   FOREIGN KEY (idAn) REFERENCES Angajat(idAn),
   nr_unitate_med int,
   FOREIGN KEY (nr_unitate_med) REFERENCES Unitate_Medicala(nr_unitate_med), 
   dataa DATE
);

INSERT INTO Unitate_Medicala(nr_unitate_med, denum, adresa, descriere, program_zi, ziua) values
(1, 'Clinica 1', 'Independentei nr.1', 'capacitate de 1000 de pacienti', 'Ora 8:00 - 20:00', 'luni'),
(2, 'Clinica 2', 'Independentei nr.1', 'capacitate de 3000 de pacienti', 'Ora 8:00 - 20:00', 'marti'),
(3, 'Clinica 3', 'Avram Iancu nr.2', 'capacitate de 3000 de pacienti', 'Ora 7:00 - 17:00', 'duminica'),
(4, 'Clinica 4', 'Trandafirilor nr.3', 'capacitate de 3000 de pacienti', 'Ora 10:00 - 21:00', 'vineri');

INSERT INTO Utilizator(idUtil, CNP, nume, prenume, adresa, nrTel, email, cont_iban, nr_contract,parola) values
(1, 789605472, 'Szollosi', 'Szilard', 'Independentei nr 5', 0742433424, 'szolszil@gmail.com', 12345, 725,abcd),
(2, 098765543, 'Solomonean', 'Daniel', 'Florilor nr 7', 0762352432, 'solodani@gmail.com', 12345, 725,bcd);

DROP PROCEDURE IF EXISTS Insert_SpecializareMedici;
delimiter //
CREATE procedure Insert_SpecializareMedici (specializare varchar(50))
BEGIN
INSERT INTO SpecializareMedici(specializare)
VALUES (specializare );
END //
delimiter ;
