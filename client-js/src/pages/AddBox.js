// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';
import PageRenderer from '../PageRenderer.js';
import {authentPage} from '../main.js';
import {achatpage} from '../main.js';
import Achat from './Achat.js';

export let box:boolean=false;

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

        const compte:{login:string,password:string}=authentPage.getCompte();
        
        $('.abonnerbox').click( (event:Event) => {
            event.preventDefault();
            if(compte.login != null){
                box=true;
                this.achatpage = new Achat();
                PageRenderer.renderPage(achatpage);
                box=false;
            }else{
                PageRenderer.renderPage(authentPage);
            }

        });
    }
    
}
