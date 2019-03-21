// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

export default class Inscription extends Page {
	constructor(){
		super(``);
	}

	render():string {
        return `<div class="formcomplet">
        <label>
        <h1> id ="titleinscriptionjs" Formulaire d'inscription</h1>
        <label>
        <span class="image">
            <img id="img" src="images/chatisfait.png" />
        </span>
        <div class="formulaireinscription">
            <form class="Inscription">
		<label>
			<input id="block" type="text" name="nom" placeholder="Entrez un pseudo" class="form-control">
        </label>
        <label>
			<input id="block"  type="password" name="mdp" placeholder="Entrez votre mot de passe" class="form-control">
        </label>
			<input id="block"  type="password" name="mdp" placeholder="Veuillez confirmer votre mot de passe" class="form-control">
        </label>
        <label>
			<select id="block"  name="selection" placeholder="Veuillez sélectionner votre pays" class="form-control">
				<option>France</option>
                <option>Belgique</option>
                <option>Suisse</option>
        </label>
			<input id="block"  type="password" name="mdp" placeholder="Entrez votre adresse" class="form-control">
        </label>
        <section class="codepostal">
        </label>
			<input id="block"  type="password" name="mdp" placeholder="Entrez votre code postal" class="form-control">
        </label>
        </label>
			<input id="block" type="password" name="mdp" placeholder="Entrez votre ville" class="form-control">
        </label>
        </section>
        <button id="boutonsubmit" type="submit" class="btn btn-default">Soumettre<img id="sendimg" src="images/send.jpg" /></button>
    </form>
    </div>
    </div>`;
    }
    
    mount(container:HTMLElement):void {
        $('form.Inscription').submit( this.submit );
	}
}

/*const envoi:Element = document.querySelector(.submit);
    envoi.click( (event:Event) => {
        event.preventDefault();
        PageRenderer.renderPage(HomePage);
    });*/