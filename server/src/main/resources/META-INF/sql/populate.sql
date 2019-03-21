-- ALTER TABLE ingredientpizza DROP CONSTRAINT FK_ingredientpizza_idpizza;
-- ALTER TABLE ingredientpizza DROP CONSTRAINT FK_ingredientpizza_idingredient;
/*DROP TABLE IF EXISTS ingredientpizza CASCADE;
DROP TABLE IF EXISTS ingredient CASCADE;
DROP TABLE IF EXISTS pizza CASCADE;
CREATE TABLE ingredient (id BIGINT IDENTITY NOT NULL, nom VARCHAR UNIQUE NOT NULL, PRIMARY KEY (id));
CREATE TABLE pizza (id BIGINT IDENTITY NOT NULL, base VARCHAR, nom VARCHAR UNIQUE NOT NULL, prix_grande DOUBLE, prix_petite DOUBLE, PRIMARY KEY (id));
CREATE TABLE ingredientpizza (idpizza BIGINT NOT NULL, idingredient BIGINT NOT NULL, PRIMARY KEY (idpizza, idingredient));
ALTER TABLE ingredientpizza ADD CONSTRAINT FK_ingredientpizza_idpizza FOREIGN KEY (idpizza) REFERENCES pizza (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE ingredientpizza ADD CONSTRAINT FK_ingredientpizza_idingredient FOREIGN KEY (idingredient) REFERENCES ingredient (id) ON DELETE CASCADE ON UPDATE CASCADE;

insert into ingredient(id, nom) values(1, 'mozzarella');
insert into ingredient(id, nom) values(2, 'jambon');
insert into ingredient(id, nom) values(3, 'champignons');
insert into ingredient(id, nom) values(4, 'olives');
insert into ingredient(id, nom) values(5, 'tomate');
insert into ingredient(id, nom) values(6, 'merguez');
insert into ingredient(id, nom) values(7, 'lardons');
insert into ingredient(id, nom) values(8, 'fromage');
insert into ingredient(id, nom) values(9, 'oeuf');
insert into ingredient(id, nom) values(10, 'poivrons');
insert into ingredient(id, nom) values(11, 'ananas');
insert into ingredient(id, nom) values(12, 'reblochon');

insert into pizza(id, nom, base, prix_petite, prix_grande) values (1, 'Regina', 'tomate', 5.5, 7.5);
insert into pizza(id, nom, base, prix_petite, prix_grande) values (2, 'Napolitaine', 'tomate', 6.2, 8);
insert into pizza(id, nom, base, prix_petite, prix_grande) values (3, 'Spicy', 'crème', 6.5, 9.95);
insert into pizza(id, nom, base, prix_petite, prix_grande) values (4, 'Oranaise', 'tomate', 5.0, 8.0);
insert into pizza(id, nom, base, prix_petite, prix_grande) values (5, 'Carbonara', 'crème', 5.5, 9);
insert into pizza(id, nom, base, prix_petite, prix_grande) values (6, 'Hawaienne', 'crème', 11.0, 11.5);

insert into ingredientpizza(idingredient, idpizza) values(1, 1);
insert into ingredientpizza(idingredient, idpizza) values(2, 1);
insert into ingredientpizza(idingredient, idpizza) values(3, 1);
insert into ingredientpizza(idingredient, idpizza) values(4, 1);
insert into ingredientpizza(idingredient, idpizza) values(1, 2);
insert into ingredientpizza(idingredient, idpizza) values(5, 2);
insert into ingredientpizza(idingredient, idpizza) values(8, 3);
insert into ingredientpizza(idingredient, idpizza) values(6, 3);
insert into ingredientpizza(idingredient, idpizza) values(7, 3);
insert into ingredientpizza(idingredient, idpizza) values(10, 3);
insert into ingredientpizza(idingredient, idpizza) values(1, 4);
insert into ingredientpizza(idingredient, idpizza) values(5, 4);
insert into ingredientpizza(idingredient, idpizza) values(6, 4);
insert into ingredientpizza(idingredient, idpizza) values(7, 5);
insert into ingredientpizza(idingredient, idpizza) values(8, 5);
insert into ingredientpizza(idingredient, idpizza) values(2, 6);
insert into ingredientpizza(idingredient, idpizza) values(8, 6);
insert into ingredientpizza(idingredient, idpizza) values(11, 6);
*/
DROP TABLE IF EXISTS article CASCADE;
DROP TABLE IF EXISTS note CASCADE;
DROP TABLE IF EXISTS utilisateur CASCADE;
DROP TABLE IF EXISTS commande CASCADE;
DROP TABLE IF EXISTS abonnement CASCADE;
CREATE TABLE article(idArticle serial NOT NULL PRIMARY KEY,nom char(20), prix integer, description CHAR(250));
CREATE TABLE note(idnote serial NOT NULL PRIMARY KEY , idArticle integer, note integer, nbnote integer, PRIMARY KEY (idArticle), FOREIGN KEY (idArticle) REFERENCES article(idArticle) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE utilisateur(idutilisateur serial NOT NULL PRIMARY KEY ,pseudo char(60), mdp char(60),sel char(5), prenom char(20), nom char(30), adresse char(60), adressemail char(70), abonne boolean);
CREATE TABLE commande(idcommande serial NOT NULL PRIMARY KEY, idutilisateur integer, prix integer, date DATE, adresseMail char(70), adresse char(60), nom char(20), prenom char(20), FOREIGN KEY  (idutilisateur) REFERENCES utilisateur(idutilisateur) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE abonnement(idabonnement serial NOT NULL PRIMARY, idutilisateur integer, datefin DATE,type varchar, FOREIGN  KEY (idutilisateur) REFERENCES utilisateur(idutilisateur) ON DELETE CASCADE ON UPDATE CASCADE);
insert into utilisateur(pseudo,mdp) values ('sacha','sacha');
insert into utilisateur(pseudo,mdp) values ('sully','sully');
insert into article(nom,prix) values ('toto','42');
