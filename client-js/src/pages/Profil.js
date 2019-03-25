// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import PageRenderer from '../PageRenderer.js';
import {authentPage} from '../main.js';

export default class Profil extends Page {
	constructor(){
		super('Mon Profil');
	}

	render(): string {
        return`
            <h2 class="abonnement"> Vous êtes abonné à la box </h2> 
            <ul class="informations">
            </ul>`;
    } 

    mount(container:HTMLElement):void {
        container.innerHTML = this.render();

        const compte:{login:string,password:string}=authentPage.getCompte();

        if(compte.login!=null){
                fetch('http://localhost:8080/v1/utilisateurs/'+compte.login)
                .then( (response:Response) => response.json() )
                .then( MAJ );
        }
  
    }

    
}
function MAJ (data2: string){
    let user:{idutilisateur:number, pseudo:string, mdp:string,sel:string,prenom:string,nom:string,adresse:string,adressemail:string,abonne:boolean};
    if(data2) user = data2;
    if(user.abonne==false){
        $('.abonnement').text(`Vous n'êtes pas abonné à la box`);
    }
    $('.informations').html(`<li>Votre pseudo : ${user.pseudo}</li>
    <li>Votre nom : ${user.nom}</li>
    <li>Votre prenom : ${user.prenom}</li>
    <li>Votre adresse : ${user.adresse}</li>
    <li>Votre mail : ${user.adresseMail}</li>`);
}


