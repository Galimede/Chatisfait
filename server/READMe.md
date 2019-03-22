#TABLE

##NE PAS OUBLIER DE COMPILER SI TOUT ROUGE CAR LES MAVENS DEPENDENCIES SONT STOCKE DANS LE TMP

##NE PAS OUBLIER DE METTRE LA NOUVELLE ENTITY DANS LE PERSISTANCE XML

les tables :
- article(nom, prix, description)


##Commande

###Exemple

`curl -i -H "Content-type: application/json" -X PUT --data "{\"abonne\":\"false\"}" http://172.18.49.71:8080/api/v1/utilisateurs/sacha`
