DROP TABLE IF EXISTS article CASCADE;
DROP TABLE IF EXISTS note CASCADE;
DROP TABLE IF EXISTS utilisateur CASCADE;
DROP TABLE IF EXISTS commande CASCADE;
DROP TABLE IF EXISTS abonnement CASCADE;
CREATE TABLE article(idarticle serial NOT NULL PRIMARY KEY,nom char(20), prix integer, description CHAR(250), image char(100), categorie char(20));
CREATE TABLE utilisateur(idutilisateur serial NOT NULL PRIMARY KEY ,pseudo char(60)  ,mdp char(60),sel char(5), prenom char(20), nom char(30), adresse char(60), adressemail char(70), abonne boolean, admin boolean);
CREATE TABLE commande(idcommande serial NOT NULL PRIMARY KEY, idutilisateur integer,idarticle integer, datecommande DATE , adresseMail char(70), adresse char(60), nom char(20), prenom char(20), FOREIGN KEY  (idutilisateur) REFERENCES utilisateur(idutilisateur),FOREIGN  KEY (idarticle) REFERENCES article(idarticle) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE abonnement(idabonnement serial NOT NULL PRIMARY KEY, idutilisateur integer, idarticle integer, age integer, poil char(20), sterilise boolean, poids integer, nom char(20), choix integer, FOREIGN  KEY (idutilisateur) REFERENCES utilisateur(idutilisateur), FOREIGN KEY (idarticle) REFERENCES article(idArticle) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE note(idnote serial NOT NULL PRIMARY KEY , idarticle integer NOT NULL, notetotale integer, nbnote integer, FOREIGN KEY (idarticle) REFERENCES article(idarticle) ON DELETE CASCADE ON UPDATE CASCADE);
insert into utilisateur(pseudo,mdp, admin,adresse,adressemail,nom,prenom) values ('sacha','sacha',true,'56 rue du moulineau','hotmail@gmail.com','sacha','sacha');
insert into utilisateur(pseudo,mdp,admin,adresse,adressemail,nom,prenom) values ('sully','sully',false,'56 rue du sable','gmail@gmail.com','sully','sully');
insert into article(nom,prix,description,categorie,image) values ('brosse metalique','45','petite brosse pour chat en bois naturel','accessoire','/images/articles/brosse.jpg');
insert into article(nom,prix,description,categorie,image) values ('croquette purizon','20','croquette pour chat','croquette','/images/articles/croquette.jpg');
insert into article(nom,prix,description,categorie,image) values ('litiere cat best','10','litiere de qualite cat best original','croquette','/images/articles/litiere.jpg');
insert into article(nom,prix,description,categorie,image) values ('patee purina gourmet','17','patee purina emicee en sauce','croquette','/images/articles/patee.jpg');
insert into commande(idutilisateur,idarticle,datecommande,adressemail,adresse,nom,prenom) values ('1','1',' 	2008-10-29' ,'mail@mail.com','77 rue du du','toto','toto');
insert into abonnement(idutilisateur, idarticle, age, nom) values ('1', '1', '5', 'mistigri');
insert into note(idarticle,nbnote,notetotale) values (1,10,50);