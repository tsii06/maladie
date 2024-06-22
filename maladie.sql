create database maladie;

\c maladie;

create sequence seq_patient;

create table patient(
    patient_id VARCHAR(50) PRIMARY KEY DEFAULT 'PATIENT' || nextval('seq_patient') ,
    nom VARCHAR(100),
    age int ,
    date_consultation date    
);

create sequence seq_parametre;

create table parametre (
    parametre_id VARCHAR(50) PRIMARY KEY DEFAULT 'PARAM'  || nextval('seq_parametre'),
    designation VARCHAR(100)  
);

create table param_patient (
    param_patient_id serial PRIMARY KEY,
    parametre_id VARCHAR(50) ,
    patient_id VARCHAR(50),
    valeur int ,
    foreign key (parametre_id) references parametre(parametre_id),
    foreign key (patient_id) references patient(patient_id)
);

-- insert into param_patient (parametre_id,patient_id,valeur) VALUES ('PARAM7','PATIENT43',4);
-- insert into param_patient (parametre_id,patient_id,valeur) VALUES ('PARAM2','PATIENT43',5);


create SEQUENCE seq_maladie;

create table maladie(
    maladie_id VARCHAR(50) PRIMARY KEY DEFAULT 'MAL' || nextval('seq_maladie'),
    nom varchar(50)
);



create table param_maladie(
    parametre_maladie_id serial PRIMARY KEY ,
    parametre_id VARCHAR(50) ,
    maladie_id VARCHAR(50),
    age_min int ,
    age_max int ,
    valeur_min int , 
    valeur_max int ,
    foreign key (parametre_id) references parametre(parametre_id),
    foreign key (maladie_id) references maladie(maladie_id)
);
-- parametre_maladie_id | parametre_id | maladie_id | age_min | age_max | valeur_min | valeur_max | maladie_id | nom | parametre_id | designation
 
 create view get_parametre_maladie as select 
pm.parametre_maladie_id,
p.parametre_id,
p.designation,
m.maladie_id,
m.nom,
age_min,
age_max,
valeur_min,
valeur_max

 from param_maladie  as pm join maladie as m on m.maladie_id=pm.maladie_id join parametre as p on p.parametre_id=pm.parametre_id;


insert into maladie (nom) values('grippe');
insert into maladie (nom) values('vavony');
insert into maladie (nom) values('indigestion');
-- insert into parametre values (default,'kibo');
-- insert into parametre values (default,'kaka');
-- insert into parametre values (default,'andoha');
-- insert into parametre values (default,'temperature');
-- insert into parametre values (default,'fatigue');
-- insert into parametre values (default,'lelo');

insert into parametre (designation) values ('kibo');
insert into parametre (designation) values ('kaka');
insert into parametre (designation) values ('andoha');
insert into parametre (designation) values ('temperature');
insert into parametre (designation) values ('fatigue');
insert into parametre (designation) values ('lelo');
 -- PARAM1       | kibo
 -- PARAM2       | kaka
 -- PARAM3       | andoha
 -- PARAM4       | temperature
 -- PARAM5       | fatigue
 -- PARAM6       | lelo



insert into param_maladie (parametre_id,maladie_id,age_min,age_max,valeur_min,valeur_max) values ('PARAM4','MAL1',1,100,3,5);
insert into param_maladie (parametre_id,maladie_id,age_min,age_max,valeur_min,valeur_max) values ('PARAM6','MAL1',1,100,5,8);
insert into param_maladie (parametre_id,maladie_id,age_min,age_max,valeur_min,valeur_max) values ('PARAM3','MAL1',1,100,5,7);

insert into param_maladie (parametre_id,maladie_id,age_min,age_max,valeur_min,valeur_max) values ('PARAM1','MAL2',1,100,5,7);
insert into param_maladie (parametre_id,maladie_id,age_min,age_max,valeur_min,valeur_max) values ('PARAM4','MAL2',12,60,3,6);

insert into param_maladie (parametre_id,maladie_id,age_min,age_max,valeur_min,valeur_max) values ('PARAM1','MAL3',1,100,5,8);
insert into param_maladie (parametre_id,maladie_id,age_min,age_max,valeur_min,valeur_max) values ('PARAM2','MAL3',1,100,6,8);
insert into param_maladie (parametre_id,maladie_id,age_min,age_max,valeur_min,valeur_max) values ('PARAM5','MAL3',1,100,3,7);


create sequence seq_medicament ; 

create table medicament (
    medicament_id varchar(50) PRIMARY KEY DEFAULT 'MED' ||  nextval('seq_medicament'),  
    nom varchar(50),
    prix double precision
);


create table param_medicament(
    param_medicament_id serial primary key ,
    parametre_id varchar(50),
    medicament_id varchar(50),
    effet int,
    foreign key (parametre_id) REFERENCES parametre(parametre_id),
    foreign key (medicament_id) REFERENCES medicament(medicament_id) 
);

-- insert into medicament (nom,prix) VALUES ('amox',2000);
-- insert into medicament (nom,prix) VALUES ('paracetamol',1500);
-- insert into medicament (nom,prix) VALUES ('malox',1500);
-- insert into medicament (nom,prix) VALUES ('efferalgan',1500);
-- insert into medicament (nom,prix) VALUES ('MED5',2000);



-- insert into param_medicament (parametre_id,medicament_id,effet) values ('PARAM6','MED1',3);
-- insert into param_medicament (parametre_id,medicament_id,effet) values ('PARAM1','MED1',4);
-- insert into param_medicament (parametre_id,medicament_id,effet) values ('PARAM6','MED2',2);

-- insert into param_medicament (parametre_id,medicament_id,effet) values ('PARAM7','MED3',2);
-- insert into param_medicament (parametre_id,medicament_id,effet) values ('PARAM2','MED4',2);
-- insert into param_medicament (parametre_id,medicament_id,effet) values ('PARAM1','MED5',2);


create or replace view v_medicament_param as 
select 
pm.parametre_id,
m.medicament_id,
m.nom,
m.prix,
pm.effet
 from param_medicament as pm join 
medicament as m on m.medicament_id=pm.medicament_id;
-- parametre_id | medicament_id | effet | medicament_id |     nom     | prix


create or replace view v_medicament as
select 
 pmed.param_medicament_id,
 m.medicament_id,
 effet,
 m.nom,
 m.prix,
 p.parametre_id,
 p.designation,
 pp.param_patient_id,
 pp.patient_id,
 pp.valeur,
 CEIL(CAST(valeur AS DECIMAL)/effet) as qte_necessaire ,
 CEIL(CAST(valeur AS DECIMAL)/effet)*m.prix as prix_total
 from 
param_medicament as pmed join
medicament as m on m.medicament_id=pmed.medicament_id join
param_patient as pp on pp.parametre_id=pmed.parametre_id JOIN
parametre as p on p.parametre_id=pp.parametre_id;


--  param_medicament_id | parametre_id | medicament_id | effet | medicament_id | nom | prix | param_patient_id | parametre_id | patient_id | valeur






    

