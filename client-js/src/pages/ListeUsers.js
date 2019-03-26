// @flow
import $ from 'jquery';
import Page from './Page.js';

let users:Array<{idutilisateur:number, pseudo:string, mdp:string,sel:string,prenom:string,nom:string,adresse:string,adressemail:string,abonne:boolean}>;
export default class ListeUsers extends Page {

    constructor()  {
    super('Liste des Utilisateurs');
    fetch('/v1/utilisateurs')
    .then((response:Response) => response.json())
    .then( MAJ );
    }

    render():string {
        let html = '';
        let isAbonne = false;
        users.forEach( user => {
            if(user.abonne) isAbonne = 'Abonne';
            else isAbonne = 'Non Abonne'; 
            html+= `
            <ul> 
            <li> ID Utilisateur: ${user.idUtilisateur} </li>
            <li> Pseudo : ${user.pseudo} </li>
            <LI> Prenom : ${user.prenom} </li> 
            <li> Nom : ${user.nom} </li> 
            <li> Mail : ${user.adresseMail} </li>
            <li> Adresse : ${user.adresse} </li>
            <li> Abonne : ${isAbonne} </li>
            </ul> <br>`;
        });
        return html;
    } 
}


function MAJ(data2: any) {
    const data: Array<{idutilisateur:number, pseudo:string, mdp:string,sel:string,prenom:string,nom:string,adresse:string,adressemail:string,abonne:boolean}> = data2;
    if (data) {
        users = data;
    }
}
