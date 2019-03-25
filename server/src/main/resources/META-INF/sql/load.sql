insert into utilisateur(pseudo,mdp, admin) values ('sacha','sacha',true);
insert into utilisateur(pseudo,mdp,admin) values ('sully','sully',false);
insert into article(nom,prix,description,categorie,image) values ('toto','42','c bleu mdr','croquette','/images/Adventure_Time_with_Finn_Jake.png');
insert into article(nom,prix,description,categorie,image) values ('cinamonebun','720','c rouge mdr','litière','/images/Adventure_Time_with_Finn_Jake.png');
insert into commande(idutilisateur,prix,datecommande,adressemail,adresse,nom,prenom) values ('1','20','2008-10-29','mail@mail.com','77 rue du du','toto','toto');
insert into abonnement(idutilisateur, idarticle, typeabonne) values ('1', '1', 'sdfsdfsaag');
insert into note(idarticle,nbnote,notetotale) values (1,10,50);