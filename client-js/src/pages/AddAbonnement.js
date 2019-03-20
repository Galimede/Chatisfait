// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

export default class AddBox extends Page {
	constructor(){
		super('Souscrire à un abonnement');
		// $FlowFixMe
		this.submit = this.submit.bind(this);
	}

	render():string {
        return `
        <h1>Bienvenue sur la page d'abonnement, inscrivez vous avec le formulaire ci-dessus</h1>
            <form class="addAbonnement">
		<label>
			Nom :
			<input type="text" name="nom" class="form-control">
		</label>
		<label>
			Selection abonnement :
			<select name="selection" class="form-control">
				<option>Croquettes</option>
                <option>Litières</option>
                <option>Les deux</option>
			</select>
		</label>
        <button type="submit" class="btn btn-default">Soumettre<img src="images/send.png" /></button>
	</form>`;

    // TODO --> verifier l'authentification
    const abonne:Element = document.querySelector(.submit);
    submit.click( (event:Event) => {
        event.preventDefault();
        PageRenderer.renderPage(HomePage);
    });

    mount(container:HTMLElement):void {
        container.innerHTML = this.render();
	}