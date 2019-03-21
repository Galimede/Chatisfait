// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import PageRenderer from '../PageRenderer.js';
import {authentPage} from '../main.js';

	clet users:Array<{id:number, pseudo:string, mdp:string,sel:string,prenom:string,nom:string,adresse:string,mail:string,aboonne:boolean}>;
    let compte: {login:string,password:string};
    export default class Profil extends Page {
        
        constructor() {
            super('Se connecter');
            // $FlowFixMe
            this.submit = this.submit.bind(this);
            fetch('http://localhost:8080/api/v1/utilisateurs')
            .then( (response:Response) => response.text() )
            .then( MAJ );
        }
	}

	render():string {
        return `<h1>Votre profil : </h1>
            <ul>
                <li>Votre pseudo : ${users.pseudo}</li>
                <li>Votre nom : ${users.nom}</li>
                <li>Votre prenom : ${users.prenom}</li>
                <li>Votre adresse : ${users.adresse}</li>
                <li>Votre mail : ${users.mail}</li>
            </ul>
        `;
    }

    mount(container:HTMLElement):void {
        container.innerHTML = this.render();
    }
}