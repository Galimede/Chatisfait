// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import PageRenderer from '../PageRenderer.js';
import {authentPage} from '../main.js';

	let user:{id:number, pseudo:string, mdp:string,sel:string,prenom:string,nom:string,adresse:string,mail:string,aboonne:boolean};
    let compte: {login:string,password:string};
    export default class Profil extends Page {
        
        constructor() {
            super('Se connecter');
            // $FlowFixMe
            this.submit = this.submit.bind(this);
            fetch('http://localhost:8080/api/v1/utilisateurs/'+compte.login)
            .then( (response:Response) => response.json() )
            .then( (data:any) => {
                if(data) {
                    user = data;
                }
            });
        }
	

	render():string {
        return `<h1>Votre profil : </h1>
            <ul>
                <li>Votre pseudo : ${user.pseudo}</li>
                <li>Votre nom : ${user.nom}</li>
                <li>Votre prenom : ${user.prenom}</li>
                <li>Votre adresse : ${user.adresse}</li>
                <li>Votre mail : ${user.mail}</li>
            </ul>`;
    }

    mount(container:HTMLElement):void {
        container.innerHTML = this.render();
    }
}