CREATE TABLE article(idArticle serial NOT NULL PRIMARY KEY,nom char(20), prix integer, description CHAR(250));
CREATE TABLE note(idnote serial NOT NULL PRIMARY KEY , idArticle integer, note integer, nbnote integer, PRIMARY KEY (idArticle), FOREIGN KEY (idArticle) REFERENCES article(idArticle) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE utilisateur(idutilisateur serial NOT NULL PRIMARY KEY ,pseudo char(60) ,mdp char(60),sel char(5), prenom char(20), nom char(30), adresse char(60), adressemail char(70), abonne boolean);
CREATE TABLE commande(idcommande serial NOT NULL PRIMARY KEY, idutilisateur integer, prix integer, date DATE, datearrive DATE, FOREIGN KEY  (idutilisateur) REFERENCES utilisateur(idutilisateur) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE abonnement(idabonnement serial NOT NULL PRIMARY KEY, idutilisateur integer, datefin DATE, FOREIGN  KEY (idutilisateur) REFERENCES utilisateur(idutilisateur) ON DELETE CASCADE ON UPDATE CASCADE);
/*CREATE TABLE boxe();*/