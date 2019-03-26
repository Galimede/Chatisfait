// @flow
import $ from 'jquery';
import Page from './Page.js';

let commandes:Array<{nomArticle:string,adresse:string,adresseMail:string,dateCommande:string,idArticle:number,idCommande:number,nom:string,prenom:string}>;
export default class Commandes extends Page {

    constructor()  {
    super('Liste des commandes');
    fetch('/v1/commandes')
    .then((response:Response) => response.json())
    .then( MAJ );
    }

    render():string {
        let html = '';
        commandes.forEach( commande => {
            html+= `
            <ul> 
            <li> Numero de Commande : ${commande.idCommande} </li>
            <li> Date Commande : ${commande.dateCommande} </li>
            <LI> Numero d'article : ${commande.idArticle} </li> 
            <li> Nom : ${commande.nom} </li> 
            <li> Prenom : ${commande.prenom} </li>
            <li> Mail : ${commande.adresseMail} </li>
            <li> Adresse : ${commande.adresse} </li>
            </ul> <br>`;
        });
        return html;
    } 
}


function MAJ(data2: any) {
    const data: Array<{nomArticle:string, adresse:string,adresseMail:string,dateCommande:string,idArticle:number,idCommande:number,nom:string,prenom:string }> = data2;
    if (data) {
        commandes = data;
    }
}
