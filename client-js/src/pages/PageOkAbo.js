// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

export default class PageOk extends Page {
	constructor(){
		super('');
		// $FlowFixMe
		this.submit = this.submit.bind(this);
	}

	render():string {
        return ` <h1> Votre commande a été prise en compte </h1>
                <h1>Votre login : blabla</h1>
                <h1>Votre adresse : blabla</h1>
                <h1>Votre panier : blabla</h1>
                <button type="submit" class="btn btn-default">Revnir à la page d'accueil</button>
       `;

       const abonne:Element = document.querySelector(.submit);
    submit.click( (event:Event) => {
        event.preventDefault();
        PageRenderer.renderPage(HomePage);
    });

    mount(container:HTMLElement):void {
        container.innerHTML = this.render();
	}