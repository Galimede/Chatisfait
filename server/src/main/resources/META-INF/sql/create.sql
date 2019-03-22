CREATE TABLE article(idarticle serial NOT NULL PRIMARY KEY,nom char(20), prix integer, description CHAR(250), image char(100), categorie char(20));
CREATE TABLE note(idnote serial NOT NULL PRIMARY KEY , idarticle integer, note integer, nbnote integer, PRIMARY KEY (idarticle), FOREIGN KEY (idarticle) REFERENCES article(idarticle) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE utilisateur(idutilisateur serial NOT NULL PRIMARY KEY ,pseudo char(60)  ,mdp char(60),sel char(5), prenom char(20), nom char(30), adresse char(60), adressemail char(70), abonne boolean);
CREATE TABLE commande(idcommande serial NOT NULL PRIMARY KEY, idutilisateur integer, prix integer, datecommande DATE , adresseMail char(70), adresse char(60), nom char(20), prenom char(20), FOREIGN KEY  (idutilisateur) REFERENCES utilisateur(idutilisateur) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE abonnement(idabonnement serial NOT NULL PRIMARY KEY, idutilisateur integer, idarticle integer, typeabonne integer , datefin DATE, FOREIGN  KEY (idutilisateur) REFERENCES utilisateur(idutilisateur), FOREIGN KEY (idarticle) REFERENCES article(idArticle) ON DELETE CASCADE ON UPDATE CASCADE);
