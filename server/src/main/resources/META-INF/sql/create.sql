CREATE TABLE articles(idarticle serial NOT NULL PRIMARY KEY, prix integer, description CHAR(250));
CREATE TABLE notes(idnote serial NOT NULL PRIMARY KEY , idarticle integer, note integer, nbnote integer, PRIMARY KEY (idarticle), FOREIGN KEY (idarticle) REFERENCES articles(idarticle) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE utilisateurs(idutilisateur serial NOT NULL PRIMARY KEY ,pseudo char(60),mdp char(60),sel char(5), prenom char(20), nom char(30), adresse char(60), adressemail char(70));
CREATE TABLE commandes(idcommande serial NOT NULL PRIMARY KEY, idutilisateur integer, prix integer, date DATE, datearrive DATE, FOREIGN KEY  (idutilisateur) REFERENCES utilisateurs(idutilisateur) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE abonnements(idabonnement serial NOT NULL PRIMARY, idutilisateur integer, datefin DATE,type varchar, FOREIGN  KEY (idutilisateur) REFERENCES utilisateurs(idutilisateur) ON DELETE CASCADE ON UPDATE CASCADE);
