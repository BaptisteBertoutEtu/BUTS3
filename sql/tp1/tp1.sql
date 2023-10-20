drop table Reserver;
drop table Terrain;
drop table Creneau;
drop table Abonne;

create table Terrain (
    tno Serial,
    libelle text,
    type char(1),
    constraint pk_terrain primary key (tno)
);

create table Creneau (
    jour Date,
    heure int check ( heure between 10 and 20),
    constraint pk_creneau primary key (jour,heure)
);

create table Abonne (
    ano Serial,
    nom text,
    prenom text,
    birth Date,
    adresse text,
    dateFinAbo Date default CURRENT_DATE+365,
    parrain int default null,
    constraint pk_abonne primary key (ano),
    constraint fk_abonne_parrain foreign key (parrain)
        references Abonne(ano)
);

create table Reserver (
    tno int,
    reserveur int,
    partenaire int check (reserveur <> partenaire),
    jour Date,
    heure int,
    utilise boolean,
    constraint pk_reserver primary key (tno,reserveur,partenaire,jour,heure),
    constraint fk_terrain foreign key (tno)
        references Terrain(tno)
        on update cascade,
    constraint fk_abonne foreign key (reserveur)
        references Abonne(ano)
        on update cascade,
    constraint fk_abonne2 foreign key (partenaire)
        references Abonne(ano)
        on update cascade,
    constraint fk_creaneau foreign key (jour,heure)
        references Creneau(jour,heure)
        on update cascade
);

insert into Terrain(libelle, type) values('terrain 1', 'B');
insert into Terrain(libelle, type) values('terrain 2', 'S');
insert into Terrain(libelle, type) values('terrain 3', 'T');
insert into Terrain(libelle, type) values('terrain 4', 'T');
insert into Terrain(libelle, type) values('terrain 5', 'S');
insert into Terrain(libelle, type) values('terrain 6', 'B');

insert into Abonne(nom,prenom,birth,adresse) values('nom 1', 'prenom 1', '2004-12-1', 'adresse 1');
insert into Abonne(nom,prenom,birth,adresse,parrain) values('nom 2', 'prenom 2', '2004-12-2', 'adresse 2', 1);
insert into Abonne(nom,prenom,birth,adresse,parrain) values('nom 3', 'prenom 3', '2004-12-3', 'adresse 3',2);
insert into Abonne(nom,prenom,birth,adresse) values('nom 4', 'prenom 4', '2004-12-4', 'adresse 4');
insert into Abonne(nom,prenom,birth,adresse) values('nom 5', 'prenom 5', '2004-12-5', 'adresse 5');

insert into Creneau values('2023-9-12',10);
insert into Creneau values('2023-9-12',11);
insert into Creneau values('2023-9-12',12);
insert into Creneau values('2023-9-12',13);
insert into Creneau values('2023-9-12',14);

insert into Reserver values(1,1,2,'2023-9-12',10,true);
insert into Reserver values(2,3,4,'2023-9-12',11,false);



