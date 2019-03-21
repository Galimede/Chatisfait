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
                <button type="submit" class="btn btn-default">Payer<img id="sendimg" src="images/send.jpg" />Confirmer</button>
       `;
    }