// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import PageRenderer from '../PageRenderer.js';
import {authentPage} from '../main.js';

export default class AddBox extends Page {
	constructor(){
		super('Ajouter une box');
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
                fetch('http://localhost:8080/api/v1/utilisateurs/'+compte.login)
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
    <li>Votre mail : ${user.adressemail}</li>`);
}





/*import $ from 'jquery';
import Page from './Page.js';
import { authentPage } from '../main.js';

let user: { id: number, pseudo: string, mdp: string, sel: string, prenom: string, nom: string, adresse: string, mail: string, abonne: boolean};
let compte : {login:string,password:string};
export default class Profil extends Page {

    constructor() {
        super('Mon Profil');
        /*compte = authentPage.getCompte();
        console.log(compte);
        fetch('http://localhost:8080/api/v1/utilisateurs/'+compte.login)
        .then( (response:Response) => response.json() )
        .then ( (data:any) => {
            if(data) user = data;
        });
    }

    render(): string {
        let html = '';
        console.log(user);
        if (user.abonne) {
            html = `
            <h2> Vous êtes abonné à la box </h2> 
            <ul>
                <li>Votre pseudo : ${user.pseudo}</li>
                <li>Votre nom : ${user.nom}</li>
                <li>Votre prenom : ${user.prenom}</li>
                <li>Votre adresse : ${user.adresse}</li>
                <li>Votre mail : ${user.mail}</li>
            </ul>`;
        } else {
            html = `
            <h2> Vous n'êtes pas abonné à la box </h2> 
            <ul>
                <li>Votre pseudo : ${user.pseudo}</li>
                <li>Votre nom : ${user.nom}</li>
                <li>Votre prenom : ${user.prenom}</li>
                <li>Votre adresse : ${user.adresse}</li>
                <li>Votre mail : ${user.mail}</li>
            </ul>`;
        }
        return html;
    }  
}*/