// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

export default class AddBox extends Page {
	constructor(){
		super('Ajouter une box');
	}

	render():string {
        return `<h1>Acheter une box</h1>
        <img src="box.jpg" alt="box">
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
	}

    /*
    // TODO --> verifier l'authentification
    const abonne:Element = document.querySelector(.abonnerbox);
    abonne.click( (event:Event) => {
        event.preventDefault();
        PageRenderer.renderPage(HomePage);
    });
    
    */
}
