/*insert into ingredient(id, nom) values(1, 'tomate');
insert into ingredient(id, nom) values(2, 'lardons');
insert into ingredient(id, nom) values(3, 'fromage');
insert into ingredient(id, nom) values(4, 'oeuf');
insert into ingredient(id, nom) values(5, 'jambon');
insert into ingredient(id, nom) values(6, 'merguez');
insert into ingredient(id, nom) values(7, 'champignon');
insert into ingredient(id, nom) values(8, 'ananas');

insert into  pizza(id, nom, base, prix_petite, prix_grande) values (1, 'oranaise', 'tomate', 5.0, 8.0);
insert into  pizza(id, nom, base, prix_petite, prix_grande) values (2, 'margarita', 'tomate', 4, 7.5);
insert into  pizza(id, nom, base, prix_petite, prix_grande) values (3, 'carbonara', 'creme', 5.5, 9);
insert into  pizza(id, nom, base, prix_petite, prix_grande) values (4, '4 saisons', 'tomate', 10.0, 15.0);
insert into  pizza(id, nom, base, prix_petite, prix_grande) values (5, 'hawai', 'creme', 11.0, 11.5);

insert into ingredientpizza(idingredient, idpizza) values(1, 1);
insert into ingredientpizza(idingredient, idpizza) values(1, 2);
insert into ingredientpizza(idingredient, idpizza) values(3, 2);
insert into ingredientpizza(idingredient, idpizza) values(2, 3);
insert into ingredientpizza(idingredient, idpizza) values(3, 3);
insert into ingredientpizza(idingredient, idpizza) values(5, 5);
insert into ingredientpizza(idingredient, idpizza) values(8, 5);

select * from produits;*/

insert into utilisateur(pseudo,mdp) values ('sacha','sacha');
insert into utilisateur(pseudo,mdp) values ('sully','sully');
insert into article(nom,prix,description) values ('toto','42','c bleu mdr');/*'2019-03-22 00:00:00.000'*/
insert into commande(idutilisateur,prix,datecommande,adressemail,adresse,nom,prenom) values ('1','20',' 	2008-10-29','mail@mail.com','77 rue du du','toto','toto');