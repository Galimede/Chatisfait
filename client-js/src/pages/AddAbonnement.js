// @flow
import Page from './Page.js';
import $ from 'jquery';
import HomePage from './HomePage.js';

export default class AddAbonnement extends Page {
	constructor(){
		super('Souscrire à un abonnement');
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
    }
    
	mount(container:HTMLElement):void {
        $('form.addAbonnement').submit( this.submit );
	}
}

// TODO --> verifier l'authentification
/*let submit:HTMLElement = document.querySelector(".submit");
submit.click( (event:Event) => {
    event.preventDefault();
    PageRenderer.renderPage(HomePage);
});*/