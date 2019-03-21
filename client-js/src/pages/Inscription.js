// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

export default class Inscription extends Page {
	constructor(){
		super(`Page d'Inscription`);
	}

	render():string {
        return `
        <h1>Inscrivez vous en remplissant ce formulaire</h1>
            <form class="Inscription">
		<label>
			Mail/Pseudo :
			<input type="text" name="mail" class="form-control">
        </label>
        <label>
			Mot de passe :
			<input type="text" name="mdp" class="form-control">
        </label>
        <button type="submit" class="btn btn-default">Soumettre<img id="sendimg" src="images/send.jpg" /></button>
    </form>`;
    }
    
    mount(container:HTMLElement):void {
        $('form.Inscription').submit( this.submit );
	}
}