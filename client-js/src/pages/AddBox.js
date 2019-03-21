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

	render():string {
        return `<h1>Acheter une box</h1>
        <img id="boximg" src="images/box.jpg" alt="box">
        <h1>Titre de la box</h1>
        <h2>Description de la box blabla</h2>
        <h1>S'abonner à notre programme de box</h1>
        <button class="abonnerbox"
            type="button">
            S'abonner
        </button>
        `;
    }

    mount(container:HTMLElement):void {
        container.innerHTML = this.render();




        console.log(authentPage.getCompte());
        const compte:{login:string,password:string}=authentPage.getCompte();
        console.log(compte);
        
        
        $('.abonnerbox').click( (event:Event) => {
            event.preventDefault();
            
            if(typeof compte.login == undefined){
                alert('Vous êtes abonné à la box');
                /*let user:Array<id:number, login:string, password:string,sel:string,prenom:string,nom:string,adresse:string,mail:string,aboonne:boolean>;
                user.abonne=true; //faire un fetch sur un user par id ET ajouter dans la var compte d'authen l'id (ajouté quand l'authent a réussie)
                fetch( '/api/v1/utilisateurs', {
					method:'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify(user)
                })
                */
            }else{
                PageRenderer.renderPage(authentPage);
            }
        });
	}

    
}
