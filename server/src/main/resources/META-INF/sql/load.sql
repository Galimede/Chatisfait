insert into utilisateur(pseudo,mdp, admin) values ('sacha','sacha',true);
insert into utilisateur(pseudo,mdp,admin) values ('sully','sully',false);
insert into article(nom,prix,description,categorie,image) values ('brosse metalique','45','petite brosse pour chat en bois naturel','accessoire','/images/brosse');
insert into article(nom,prix,description,categorie,image) values ('croquette purizon','20','croquette pour chat','croquette','/images/croquette.png');
insert into article(nom,prix,description,categorie,image) values ('litiere cat best','10','litiere de qualite cat best original','croquette','/images/litiere');
insert into article(nom,prix,description,categorie,image) values ('patee purina gourmet','17','patee purina emicee en sauce','croquette','/images/litiere');
insert into commande(idutilisateur,prix,datecommande,adressemail,adresse,nom,prenom) values ('1','20','2008-10-29','mail@mail.com','77 rue du du','toto','toto');
insert into abonnement(idutilisateur, idarticle, age, nom) values ('1', '1', '5', 'mistigri');
insert into note(idarticle,nbnote,notetotale) values (1,10,50);
insert into article()